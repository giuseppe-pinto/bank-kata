package repositories;

import domain.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class InMemoryTransactionRepository implements TransactionRepository
{
  private Supplier<LocalDate> dateSupplier;

  public InMemoryTransactionRepository(Supplier<LocalDate> dateSupplier)
  {
    this.dateSupplier = dateSupplier;
  }

  @Override 
  public void addDeposit(int amount)
  {
    throw new UnsupportedOperationException();
  }

  @Override 
  public void withdrawal(int amount)
  {
    throw new UnsupportedOperationException();
  }

  @Override 
  public List<Transaction> allTransactions()
  {
    throw new UnsupportedOperationException();
  }
}