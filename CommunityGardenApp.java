// This Java class represents a community garden appointment scheduling application. 
// It provides a graphical user interface (GUI) for users to input their appointment details, select the date and time, 
// and choose if they want to make a donation (monetary or item donation). 
// The class includes logic to validate appointment times, reject invalid inputs, 
// and store appointment details in a file.

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class CommunityGardenApp {

  // Constants for validating operating hours (Monday to Friday: 8 AM - 8 PM, Saturday: 8 AM - 5 PM)
  private static final int MONDAY_FRIDAY_START_HOUR = 8;
  private static final int MONDAY_FRIDAY_END_HOUR = 20;
  private static final int SATURDAY_START_HOUR = 8;
  private static final int SATURDAY_END_HOUR = 17;

  public static void main(String[] args) {
    // Create the main application window (JFrame)
    JFrame frame = new JFrame("Community Garden Registration");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Arrange components vertically
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 500); // Set window size

    // Create the UI components (text fields, date chooser, time spinner)
    JTextField nameField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField phoneField = new JTextField(20);
    JDateChooser dateChooser = new JDateChooser(); // Allows date selection
    SpinnerDateModel model = new SpinnerDateModel(); // Time picker (for hours and minutes)
    JSpinner timeSpinner = new JSpinner(model);
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
    timeSpinner.setEditor(timeEditor);

    // Add the components to the panel
    panel.add(new JLabel("Enter Name:"));
    panel.add(nameField);
    panel.add(new JLabel("Enter Email:"));
    panel.add(emailField);
    panel.add(new JLabel("Enter Phone:"));
    panel.add(phoneField);
    panel.add(new JLabel("Choose Appointment Date:"));
    panel.add(dateChooser);
    panel.add(new JLabel("Choose Appointment Time:"));
    panel.add(timeSpinner);
    JButton submitButton = new JButton("Submit");
    panel.add(submitButton);

    // Add the panel to the frame and set the frame visible
    frame.add(panel);
    frame.setVisible(true);

    // Initialize the GardenRegistration object to store appointments
    GardenRegistration gardenRegistration = new GardenRegistration();

    // Handle the submit button click event
    submitButton.addActionListener(e -> {
      // Retrieve user inputs
      String name = nameField.getText();
      String email = emailField.getText();
      String phone = phoneField.getText();
      Date selectedDate = dateChooser.getDate();
      Date selectedTime = (Date) timeSpinner.getValue();

      // Ensure both date and time are selected
      if (selectedDate == null || selectedTime == null) {
        JOptionPane.showMessageDialog(frame, "Please select both a date and time.");
        return;
      }

      // Combine the selected date and time into a single Date object
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(selectedDate);
      Calendar timeCalendar = Calendar.getInstance();
      timeCalendar.setTime(selectedTime);
      calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
      calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
      Date fullDateTime = calendar.getTime();

      // Get the current date and time for validation
      Calendar currentDate = Calendar.getInstance();
      currentDate.setTime(new Date());

      // Check if the selected date and time are in the past
      if (fullDateTime.before(currentDate.getTime())) {
        JOptionPane.showMessageDialog(frame, "Invalid date and time! You cannot schedule an appointment in the past.");
        return;
      }

      // Get the day of the week and validate appointment day and time
      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek == Calendar.SUNDAY) {
        JOptionPane.showMessageDialog(frame, "The garden is closed on Sundays. Please choose another day.");
        return;
      }

      // Validate operating hours (Monday-Friday: 8 AM - 8 PM, Saturday: 8 AM - 5 PM)
      int hour = calendar.get(Calendar.HOUR_OF_DAY);
      if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) {
        if (hour < MONDAY_FRIDAY_START_HOUR || hour >= MONDAY_FRIDAY_END_HOUR) {
          JOptionPane.showMessageDialog(frame, "Invalid time! Please choose a time between 8:00 AM and 8:00 PM.");
          return;
        }
      } else if (dayOfWeek == Calendar.SATURDAY) {
        if (hour < SATURDAY_START_HOUR || hour >= SATURDAY_END_HOUR) {
          JOptionPane.showMessageDialog(frame, "Invalid time! Please choose a time between 8:00 AM and 5:00 PM.");
          return;
        }
      }

      // Ask the user if they want to make a donation
      String donationOption = JOptionPane.showInputDialog("Would you like to make a donation? (Yes/No)");
      String donationDetails = "No donation"; // Default to "No donation"

      // Handle monetary donation
      if (donationOption.equalsIgnoreCase("Yes")) {
        String donationType = JOptionPane.showInputDialog("Choose donation type: 1. Monetary 2. Item");
        if (donationType.equals("1")) {
          double amount = 0.0;
          boolean validAmount = false;
          
          // Loop to validate and get a valid monetary amount
          while (!validAmount) {
            String amountInput = JOptionPane.showInputDialog("Enter donation amount:");
            try {
              amount = Double.parseDouble(amountInput);
              if (amount > 0) {
                validAmount = true;
              } else {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than zero. Please enter a valid amount.");
              }
            } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid number.");
            }
          }

          // Format the amount and create a MonetaryDonation object
          String formattedAmount = String.format("%.2f", amount);
          MonetaryDonation monetaryDonation = new MonetaryDonation(amount);
          monetaryDonation.makeDonation();
          donationDetails = "Monetary donation of $" + formattedAmount;
        } else if (donationType.equals("2")) { // Handle item donation
          String itemName = JOptionPane.showInputDialog("Enter the item name:");
          int quantity = 0;
          boolean validQuantity = false;

          // Loop to validate and get a valid quantity
          while (!validQuantity) {
            String quantityInput = JOptionPane.showInputDialog("Enter the quantity:");
            try {
              quantity = Integer.parseInt(quantityInput);
              if (quantity > 0) {
                validQuantity = true;
              } else {
                JOptionPane.showMessageDialog(frame, "Quantity must be greater than zero. Please enter a valid quantity.");
              }
            } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid integer for quantity.");
            }
          }

          // Create an ItemDonation object
          ItemDonation itemDonation = new ItemDonation(itemName, quantity);
          itemDonation.makeDonation();
          donationDetails = "Item donation: " + quantity + " " + itemName + "(s)";
        }
      }

      // Create an Appointment object with the donation details and register it
      Appointment appointment = new Appointment(name, email, phone, fullDateTime, donationDetails);
      gardenRegistration.registerAppointment(appointment);

      // Show confirmation message
      JOptionPane.showMessageDialog(frame, "Appointment Scheduled:\n" + appointment.getAppointmentDetails());

      // Save appointments to file
      gardenRegistration.saveAppointmentsToFile();
    });
  }
}