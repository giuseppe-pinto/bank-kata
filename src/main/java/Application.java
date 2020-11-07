import domain.Account;
import printer.Console;
import printer.StatementPrinter;
import repositories.InMemoryTransactionRepository;

import java.time.LocalDate;

public class Application
{

  public static void main(String[] args)
  {

    InMemoryTransactionRepository transactionRepository = 
        new InMemoryTransactionRepository(LocalDate::now);

    Account account = new Account(transactionRepository, new StatementPrinter(new Console()));
    account.deposit(500);
    account.withdraw(100);
    
    String result = account.printStatement();
  }
  
}
