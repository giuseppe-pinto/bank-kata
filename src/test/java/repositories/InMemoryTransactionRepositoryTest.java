package repositories;

import domain.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepositoryTest
{

  @Test
  void deposit()
  {
    Supplier<LocalDate> dateSupplier = () -> LocalDate.of(2020, Month.AUGUST, 20);
    
    InMemoryTransactionRepository transactionRepository = new InMemoryTransactionRepository(dateSupplier);
    transactionRepository.addDeposit(100);

    List<Transaction> transactions = transactionRepository.allTransactions();
    
    assertEquals(1, transactions.size());
    assertEquals(transaction(dateSupplier.get(), 100), transactions.get(0));
  }

  private Transaction transaction(LocalDate localDate, int amount)
  {
    return new Transaction(localDate, amount);
  }
}