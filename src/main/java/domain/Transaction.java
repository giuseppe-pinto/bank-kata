package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction
{
  private LocalDate localDate;
  private int amount;

  public Transaction(LocalDate localDate, int amount)
  {
    this.localDate = localDate;
    this.amount = amount;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Transaction that = (Transaction) o;
    return amount == that.amount &&
        Objects.equals(localDate, that.localDate);
  }

  @Override public int hashCode()
  {
    return Objects.hash(localDate, amount);
  }

  @Override public String toString()
  {
    return "Transaction{" +
        "localDate=" + localDate +
        ", amount=" + amount +
        '}';
  }
}
