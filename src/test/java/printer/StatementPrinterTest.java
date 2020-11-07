package printer;

import domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatementPrinterTest
{

  @Mock
  private Console console;

  private StatementPrinter statementPrinter;

  @BeforeEach
  void setUp()
  {
    statementPrinter = new StatementPrinter(console);
  }

  @Test
  void alwaysPrintTheHeader()
  {
    String print = statementPrinter.print(new ArrayList<>());
    verify(console).printLine("Date | Amount | Balance");
    assertEquals(print, "Date | Amount | Balance");
  }

  @Test
  void printBalance()
  {

    String expectedPrinter =
        "Date | Amount | Balance" + "\n"+
            "24.12.2015 | 500 | 500" + "\n"+
            "23.08.2016 | -100 | 400" + "\n"+
            "27.08.2016 | 50 | 450";

    List<Transaction> transactions = createTransaction(
        deposit(LocalDate.of(2015, 12, 24), 500),
        withdrawal(LocalDate.of(2016, 8, 23), 100),
        deposit(LocalDate.of(2016, 8, 27), 50)
    );

    String actualPrinter = statementPrinter.print(transactions);
    
    InOrder inOrder = Mockito.inOrder(console);
    inOrder.verify(console).printLine("Date | Amount | Balance");
    inOrder.verify(console).printLine("24.12.2015 | 500 | 500");
    inOrder.verify(console).printLine("23.08.2016 | -100 | 400");
    inOrder.verify(console).printLine("27.08.2016 | 50 | 450");

    assertEquals(expectedPrinter, actualPrinter);
  }

  private List<Transaction> createTransaction(Transaction... transaction)
  {
    return Arrays.asList(transaction);
  }

  private Transaction withdrawal(LocalDate localDate, int amount)
  {
    return new Transaction(localDate, -amount);
  }

  private Transaction deposit(LocalDate localDate, int amount)
  {
    return new Transaction(localDate, amount);
  }
}