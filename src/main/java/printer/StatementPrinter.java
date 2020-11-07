package printer;

import domain.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter
{
  private static final String HEADER = "Date | Amount | Balance";
  private final Console console;

  public StatementPrinter(Console console)
  {
    this.console = console;
  }

  public String print(List<Transaction> transactions)
  {
    List<String> transactionsAsString = new ArrayList<>();
    
    transactionsAsString.add(HEADER);

    AtomicInteger balanceAccumulator = new AtomicInteger(0);
    
    transactionsAsString.addAll(transactions
                                    .stream()
                                    .map(transaction -> statementLine(transaction, balanceAccumulator.addAndGet(transaction.getAmount())))
                                    .collect(Collectors.toList()));
                                    
    
    transactionsAsString
        .forEach(console::printLine);
    
    return String.join("\n", transactionsAsString);
  }

  private String statementLine(Transaction transaction, int balance)
  {
    return transaction.getLocalDateAsString() +
        " | " +
        transaction.getAmount() +
        " | " +
        balance;
  }
}
