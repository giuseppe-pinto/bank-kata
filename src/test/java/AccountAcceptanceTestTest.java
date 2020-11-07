import domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import printer.Console;
import printer.StatementPrinter;
import repositories.InMemoryTransactionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountAcceptanceTestTest
{

  @Mock
  private Console console;
  private Account account;

  @BeforeEach
  void setUp()
  {
    account = new Account(new InMemoryTransactionRepository(LocalDate::now), 
                          new StatementPrinter());
  }
  
  @Test
  void featureForBankKata()
  {
    String expectedStatement =
            "Date | Amount | Balance" +
            "24.12.2015 | +500 | 500" +
            "23.08.2016 | -100 | 400" +
            "23.08.2016 | -100 | 300" +
            "27.08.2016 | +50 | 350";

    account.deposit(500);
    account.withdraw(100);
    account.withdraw(100);
    account.deposit(50);

    String actualStatement = account.printStatement();

    InOrder inOrder = Mockito.inOrder(console);
    inOrder.verify(console).print("Date | Amount | Balance");
    inOrder.verify(console).print("24.12.2015 | +500 | 500");
    inOrder.verify(console).print("23.08.2016 | -100 | 400");
    inOrder.verify(console).print("23.08.2016 | -100 | 300");
    inOrder.verify(console).print("27.08.2016 | +50 | 350");

    assertEquals(expectedStatement, actualStatement);

  }
}