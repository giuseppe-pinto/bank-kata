package domain;

import java.time.LocalDate;

public class Transaction
{
  private int amount;

  public Transaction(LocalDate localDate, int amount)
  {

    this.amount = amount;
  }
}
