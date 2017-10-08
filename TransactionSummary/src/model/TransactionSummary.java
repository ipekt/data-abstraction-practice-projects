package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionSummary {

  private String name;
  private List<Transaction> transactions;

  public TransactionSummary(String name) {
    this.name = name;
    transactions = new ArrayList<>();
  }

  // getters
  public String getName() {
    return name;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public int getNumTransactions() {
    return transactions.size();
  }

  // REQUIRES: t is not already within transactions
  // MODIFIES: this
  // EFFECTS: adds the given transaction t to the list of transactions
  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  // REQUIRES: transactions is non-empty
  // EFFECTS: computes the average amount of money spent on a transaction
  public double averageTransactionCost() {
    int total = transactions
        .stream()
        .map(Transaction::getAmount)
        .reduce(0, (t1, t2) -> t1 + t2);

    return total / transactions.size();
  }

  // REQUIRES: transactions is non-empty
  // EFFECTS:  returns the average cost of all transactions of type specificType in this
  //           TransactionSummary
  public double specificTypeAverage(TransactionType specificType) {
    List<Transaction> filteredList = transactions
        .stream()
        .filter(t -> t.getType().equals(specificType))
        .collect(Collectors.toList());

    int total = filteredList
        .stream()
        .map(Transaction::getAmount)
        .reduce(0, (t1, t2) -> t1 + t2);
    return total / filteredList.size();
  }

  // REQUIRES: transactions is non-empty
  // EFFECTS: returns the largest transaction (in terms of cost) in this TransactionSummary
  public Transaction largestTransaction() {
    return transactions
        .stream()
        .max(Comparator.comparingInt(Transaction::getAmount))
        .orElse(null);
  }

  // EFFECTS: returns true if the given transaction is contained within the list of transactions
  public boolean contains(Transaction t) {
    return transactions.contains(t);
  }
}
