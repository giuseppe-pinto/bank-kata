import domain.Account;
import domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import printer.StatementPrinter;
import repositories.TransactionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountShould
{

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private StatementPrinter statementPrinter;
  
  private Account account;

  @BeforeEach
  void setUp()
  {
    account = new Account(transactionRepository, statementPrinter);
  }

  @Test
  void storeDepositATransaction()
  {
    int amount = 100;
    account.deposit(amount);
    verify(transactionRepository).addDeposit(amount);
  }

  @Test
  void storeWithdrawalTransaction()
  {
    int amount = 200;
    account.withdraw(amount);
    verify(transactionRepository).withdrawal(amount);
  }

  @Test
  void printStatement()
  {
    String expectedStringFromPrinter = "A STRING FROM PRINTER";
    
    List<Transaction> transactions = Arrays.asList(new Transaction(LocalDate.now(), 100));

    given(transactionRepository.allTransactions()).willReturn(transactions);
    given(statementPrinter.print(transactions)).willReturn(expectedStringFromPrinter);

    String actualStringFromPrinter = account.printStatement();
    
    verify(statementPrinter).print(transactions);
    assertEquals(actualStringFromPrinter, expectedStringFromPrinter);
  }
  
}