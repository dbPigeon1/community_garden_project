// This Java class implements the Donation interface to represent an item donation. 
// It includes the item name and quantity attributes to store the details of the donation, 
// and the method makeDonation() is implemented to specify the action for making an item donation.

public class ItemDonation implements Donation {
  
  // Instance variables to store the details of the item donation
  private String itemName; // Name of the item being donated
  private int quantity;     // Quantity of the item being donated

  // Constructor to initialize the item name and quantity for the donation
  public ItemDonation(String itemName, int quantity) {
    this.itemName = itemName; // Set the item name to the provided value
    this.quantity = quantity; // Set the quantity to the provided value
  }

  // Implementation of the makeDonation() method from the Donation interface, defines the behavior for making an item donation
  public void makeDonation() {
    // Output a message indicating the item and quantity being donated
    System.out.println("Donated " + quantity + " " + itemName + "(s).");
  }
}