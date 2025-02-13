// This Java class implements the Donation interface to represent a monetary donation. 
// It includes an amount attribute to store the donation amount, and the method makeDonation() is implemented 
// to provide the specific behavior of making a monetary donation.

public class MonetaryDonation implements Donation {
  
  // Instance variable to store the donation amount
  private double amount; // Amount of the monetary donation

  // Constructor to initialize the donation amount when creating an instance of MonetaryDonation
  public MonetaryDonation(double amount) {
    this.amount = amount; // Set the donation amount to the provided value
  }

  // Implementation of the makeDonation() method from the Donation interface, defines what happens when a monetary donation is made
  public void makeDonation() {
    // Format the donation amount to 2 decimal places for proper currency representation
    String formattedAmount = String.format("%.2f", amount); 
    // Output a message indicating the donation amount has been made
    System.out.println("Monetary Donation of $" + formattedAmount + " has been made.");
  }
}