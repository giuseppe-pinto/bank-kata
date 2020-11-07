package repositories;

import domain.Transaction;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

import static java.util.Collections.*;

public class InMemoryTransactionRepository implements TransactionRepository
{
  private Supplier<LocalDate> dateSupplier;
  private final List<Transaction> transactions = new ArrayList<>();

  public InMemoryTransactionRepository(Supplier<LocalDate> dateSupplier)
  {
    this.dateSupplier = dateSupplier;
  }

  @Override 
  public void addDeposit(int amount)
  {
    transactions.add(new Transaction(dateSupplier.get(), amount));
  }

  @Override 
  public void withdrawal(int amount)
  {
    transactions.add(new Transaction(dateSupplier.get(), -amount));
  }

  @Override 
  public List<Transaction> allTransactions()
  {
    return unmodifiableList(transactions);
  }
}