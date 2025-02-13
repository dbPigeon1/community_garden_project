// This Java class represents an Appointment, which includes details like the user's name, email, phone number, 
// the date of the appointment, and any donation information associated with the appointment.
// It includes a constructor to initialize the values, methods to retrieve the details, and a method to display the full appointment information.

import java.util.Date;

public class Appointment {
  
  // Instance variables to store appointment details
  private String name;  // Name of the person who made the appointment
  private String email; // Email address of the person
  private String phone; // Phone number of the person
  private Date appointmentDate; // Date and time of the appointment
  private String donationDetails; // To store any donation-related information associated with the appointment

  // Constructor to initialize the Appointment object with values, called when creating a new Appointment instance
  public Appointment(String name, String email, String phone, Date appointmentDate, String donationDetails) {
    this.name = name; // Set the person's name
    this.email = email; // Set the person's email
    this.phone = phone; // Set the person's phone number
    this.appointmentDate = appointmentDate; // Set the appointment date
    this.donationDetails = donationDetails; // Set the donation details (if any)
  }

  // Method to return a formatted string with all the appointment details, used to get a full view of the appointment information
  public String getAppointmentDetails() {
    return "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nAppointment Date: " + appointmentDate + "\nDonation Details: " + donationDetails;
  }

  // Getter method to retrieve the appointment date
  public Date getAppointmentDate() {
    return appointmentDate; // Returns the date of the appointment
  }

  // Getter method to retrieve donation details
  public String getDonationDetails() {
    return donationDetails; // Returns the donation details if provided
  }
}