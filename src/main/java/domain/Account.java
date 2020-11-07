package domain;

import printer.StatementPrinter;
import repositories.TransactionRepository;

public class Account
{

  private TransactionRepository transactionRepository;
  private StatementPrinter statementPrinter;

  public Account(TransactionRepository transactionRepository,
                 StatementPrinter statementPrinter)
  {
    this.transactionRepository = transactionRepository;
    this.statementPrinter = statementPrinter;
  }

  public void deposit(int amount){
    transactionRepository.addDeposit(amount);
  }

  public void withdraw(int amount){
    transactionRepository.withdrawal(amount);
  }
  
  public String printStatement(){
    return statementPrinter.print(transactionRepository.allTransactions());
  }
  
}