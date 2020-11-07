package repositories;

import domain.Transaction;

import java.util.List;

public interface TransactionRepository
{
  void addDeposit(int amount);

  void withdrawal(int amount);

  List<Transaction> allTransactions();
}
