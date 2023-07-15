package br.com.banco.service;

import br.com.banco.dto.StatementDTO;
import br.com.banco.model.Account;
import br.com.banco.model.Transfer;
import br.com.banco.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementService {
    private final AccountService accountService;
    private final TransferRepository transferRepository;

    public StatementService(AccountService accountService, TransferRepository transferRepository) {
        this.accountService = accountService;
        this.transferRepository = transferRepository;
    }

    public StatementDTO getStatement(Long accountId,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     String operatorName) {
        Account account = accountService.getAccountById(accountId);

        List<Transfer> transfers = transferRepository.findByAccountOrOperatorName(account, account.getOwner());
        List<Transfer> filteredTransfers = filterTransfers(transfers, startDate, endDate, operatorName);

        BigDecimal totalBalance = calculateBalance(transfers);
        BigDecimal balanceInPeriod = calculateBalance(filteredTransfers);

        return StatementDTO.builder()
                .accountOwner(account.getOwner())
                .transfers(filteredTransfers)
                .totalBalance(totalBalance.setScale(2, RoundingMode.HALF_EVEN))
                .balanceInPeriod(balanceInPeriod.setScale(2, RoundingMode.HALF_EVEN))
                .build();
    }


    private List<Transfer> filterTransfers(List<Transfer> transfers,
                                           LocalDate startDate,
                                           LocalDate endDate,
                                           String operatorName) {
        return transfers.stream()
                .filter(transfer -> isWithinDateRange(transfer, startDate, endDate))
                .filter(transfer -> hasMatchingOperatorName(transfer, operatorName))
                .collect(Collectors.toList());
    }

    private BigDecimal calculateBalance(List<Transfer> transfers) {
        return transfers.stream()
                .map(Transfer::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isWithinDateRange(Transfer transfer, LocalDate startDate, LocalDate endDate) {
        LocalDateTime transferDateTime = transfer.getTransferDate();

        if (startDate != null && transferDateTime.isBefore(startDate.atStartOfDay())) {
            return false;
        }

        if (endDate != null && transferDateTime.isAfter(endDate.atTime(LocalTime.MAX))) {
            return false;
        }

        return true;
    }

    private boolean hasMatchingOperatorName(Transfer transfer, String transferOperatorName) {
        if (transferOperatorName != null && !transferOperatorName.isBlank()) {
            String operatorName = transfer.getOperatorName();
            return operatorName != null && operatorName.equals(transferOperatorName);
        }
        return true;
    }
}
