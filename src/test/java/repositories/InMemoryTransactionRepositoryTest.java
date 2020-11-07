package repositories;

import domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepositoryTest
{

  private final Supplier<LocalDate> dateSupplier = () -> LocalDate.of(2020, Month.AUGUST, 20);
  private InMemoryTransactionRepository transactionRepository;

  @BeforeEach
  void setUp()
  {
    transactionRepository = new InMemoryTransactionRepository(dateSupplier);
  }

  @Test
  void deposit()
  {
    transactionRepository.addDeposit(100);

    List<Transaction> transactions = transactionRepository.allTransactions();
    
    assertEquals(1, transactions.size());
    assertEquals(transaction(dateSupplier.get(), 100), transactions.get(0));
  }

  @Test
  void withdrawal()
  {
    transactionRepository.addDeposit(100);
    transactionRepository.withdrawal(50);

    List<Transaction> transactions = transactionRepository.allTransactions();
    
    assertEquals(2, transactions.size());
    assertEquals(transaction(dateSupplier.get(), 100), transactions.get(0));
    assertEquals(transaction(dateSupplier.get(), -50), transactions.get(1));
  }

  private Transaction transaction(LocalDate localDate, int amount)
  {
    return new Transaction(localDate, amount);
  }
}