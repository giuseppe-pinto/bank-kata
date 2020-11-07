import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import printer.Console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountAcceptanceTestTest
{

  @Mock
  private Console console;

  private final Account account = new Account();

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

    verify(console).print("Date | Amount | Balance");
    verify(console).print("24.12.2015 | +500 | 500");
    verify(console).print("23.08.2016 | -100 | 400");
    verify(console).print("23.08.2016 | -100 | 300");
    verify(console).print("27.08.2016 | +50 | 350");

    assertEquals(expectedStatement, actualStatement);

  }
}