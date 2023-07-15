package br.com.banco.service;

import br.com.banco.dto.StatementDTO;
import br.com.banco.model.Account;
import br.com.banco.model.Transfer;
import br.com.banco.repository.TransferRepository;
import br.com.banco.utils.TransferType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementServiceTest {
    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private StatementService statementService;

    @Mock
    private AccountService accountService;


    private static Account mockAccount;
    private static List<Transfer> mockTransfers;

    @BeforeAll
    static void setup() {
        mockAccount = Account.builder().owner("Fulano").id(1L).build();
        Transfer transfer1 = Transfer.builder()
                .id(1L)
                .transferDate(LocalDateTime.of(2023, 5, 1, 10, 0))
                .value(BigDecimal.valueOf(500))
                .operatorName(null)
                .type(TransferType.DEPOSITO)
                .account(mockAccount)
                .build();
        Transfer transfer2 = Transfer.builder()
                .id(2L)
                .transferDate(LocalDateTime.of(2023, 6, 1, 15, 30))
                .value(BigDecimal.valueOf(-100))
                .operatorName("Sicrano")
                .type(TransferType.TRANSFERENCIA)
                .account(mockAccount)
                .build();
        Transfer transfer3 = Transfer.builder()
                .id(3L)
                .transferDate(LocalDateTime.of(2023, 7, 1, 8, 0))
                .value(BigDecimal.valueOf(-200))
                .operatorName(null)
                .type(TransferType.SAQUE)
                .account(mockAccount)
                .build();
        mockTransfers = List.of(transfer1, transfer2, transfer3);
    }

    private void setupMocks(List<Transfer> transfers) {
        when(accountService.getAccountById(mockAccount.getId())).thenReturn(mockAccount);
        when(transferRepository.findByAccountOrOperatorName(mockAccount, mockAccount.getOwner())).thenReturn(transfers);
    }

    @Test
    void getStatement_WhenNoParametersProvided_ReturnsStatementWithAllTransfersInAccount() {
        // Arrange
        setupMocks(mockTransfers);

        // Act
        StatementDTO statement = statementService.getStatement(mockAccount.getId(), null, null, null);

        // Assert
        assertStatementEquals(mockAccount.getOwner(), 3, 200D, 200D, statement);
        verifyMethodsCalled();
    }

    @Test
    void getStatement_WhenStartDateEndDateAndOperatorNameProvided_ReturnsStatementWithFilteredTransfers() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 6, 1);
        LocalDate endDate = LocalDate.of(2023, 6, 30);
        String operatorName = "Sicrano";
        setupMocks(mockTransfers);

        // Act
        StatementDTO statement = statementService.getStatement(mockAccount.getId(), startDate, endDate, operatorName);

        // Assert
        assertStatementEquals(mockAccount.getOwner(), 1, 200D, -100D, statement);
        verifyMethodsCalled();
    }

    @Test
    void getStatement_WhenNoMatchingTransfers_ReturnsEmptyStatement() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        LocalDate endDate = LocalDate.of(2023, 4, 30);
        String operatorName = "Sicrano";
        setupMocks(mockTransfers);

        // Act
        StatementDTO statement = statementService.getStatement(mockAccount.getId(), startDate, endDate, operatorName);

        // Assert
        assertStatementEquals(mockAccount.getOwner(), 0, 200D, 0D, statement);
        verifyMethodsCalled();
    }

    @Test
    void getStatement_WhenNoTransfers_ReturnsStatementWithZeroBalance() {
        // Arrange
        List<Transfer> emptyTransfers = new ArrayList<>();
        setupMocks(emptyTransfers);

        // Act
        StatementDTO statement = statementService.getStatement(mockAccount.getId(), null, null, null);

        // Assert
        assertStatementEquals(mockAccount.getOwner(), 0, 0D, 0D, statement);
        verifyMethodsCalled();
    }

    private void verifyMethodsCalled() {
        verify(accountService).getAccountById(ArgumentMatchers.anyLong());
        verify(transferRepository).findByAccountOrOperatorName(ArgumentMatchers.any(Account.class), ArgumentMatchers.anyString());
    }

    private void assertStatementEquals(String expectedOwner, Integer expectedTransferCount, Double expectedTotalBalance,
                                       Double expectedBalanceInPeriod, StatementDTO statement) {
        assertEquals(expectedOwner, statement.getAccountOwner());
        assertEquals(expectedTransferCount, statement.getTransfers().size());
        assertEquals(BigDecimal.valueOf(expectedTotalBalance).setScale(2, RoundingMode.HALF_EVEN), statement.getTotalBalance());
        assertEquals(BigDecimal.valueOf(expectedBalanceInPeriod).setScale(2, RoundingMode.HALF_EVEN), statement.getBalanceInPeriod());
    }
}