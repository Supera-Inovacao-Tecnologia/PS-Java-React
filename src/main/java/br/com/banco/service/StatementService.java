package br.com.banco.service;

import br.com.banco.dto.StatementDTO;
import br.com.banco.model.Account;
import br.com.banco.model.Transfer;
import br.com.banco.repository.TransferRepository;
import br.com.banco.utils.TransferType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumSet;
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

        BigDecimal totalBalance = calculateBalance(transfers, account);
        BigDecimal balanceInPeriod = calculateBalance(filteredTransfers, account);

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

    private BigDecimal calculateBalance(List<Transfer> transfers, Account account) {
        EnumSet<TransferType> typesToSubtract = EnumSet.of(TransferType.SAQUE, TransferType.TRANSFERENCIA);
        BigDecimal balance = BigDecimal.ZERO;
        for (Transfer transfer : transfers) {
            if (typesToSubtract.contains(transfer.getType()) && transfer.getAccount().equals(account)) {
                balance = balance.subtract(transfer.getValue());
            } else {
                balance = balance.add(transfer.getValue());
            }
        }
        return balance;
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
