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
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountAcceptanceTestTest
{

  @Mock
  private Supplier<LocalDate> dateSupplier;
  
  @Mock
  private Console console;
  
  private Account account;

  @BeforeEach
  void setUp()
  {
    account = new Account(new InMemoryTransactionRepository(dateSupplier), 
                          new StatementPrinter(console));
  }
  
  @Test
  void featureForBankKata()
  {
    
    given(dateSupplier.get()).willReturn(
        LocalDate.of(2015, 12, 24),
        LocalDate.of(2016, 8, 23),
        LocalDate.of(2016, 8, 23),
        LocalDate.of(2016, 8, 27)
    );
    
    String expectedStatement =
            "Date | Amount | Balance" + "\n" +
            "24.12.2015 | 500 | 500" + "\n" +
            "23.08.2016 | -100 | 400" + "\n" +
            "23.08.2016 | -100 | 300" + "\n" +
            "27.08.2016 | 50 | 350";

    account.deposit(500);
    account.withdraw(100);
    account.withdraw(100);
    account.deposit(50);

    String actualStatement = account.printStatement();

    InOrder inOrder = Mockito.inOrder(console);
    inOrder.verify(console).printLine("Date | Amount | Balance");
    inOrder.verify(console).printLine("24.12.2015 | 500 | 500");
    inOrder.verify(console).printLine("23.08.2016 | -100 | 400");
    inOrder.verify(console).printLine("23.08.2016 | -100 | 300");
    inOrder.verify(console).printLine("27.08.2016 | 50 | 350");

    assertEquals(expectedStatement, actualStatement);

  }
}