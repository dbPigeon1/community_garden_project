// This Java class handles the registration and storage of appointments for a garden.
// It includes methods to register appointments, save them to a file, and clear the appointments list after saving.
// The appointments are stored in a list and written to a file named "appointments.txt".

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GardenRegistration {
  
  // Instance variable to store the list of appointments
  private List<Appointment> appointments = new ArrayList<>(); // List that holds all the appointments

  // Method to register a new appointment, adds an appointment to the list of appointments
  public void registerAppointment(Appointment appointment) {
    appointments.add(appointment); // Adds the given appointment to the list
  }

  // Method to save all appointments to a file, writes the details of each appointment to a file named "appointments.txt"
  public void saveAppointmentsToFile() {
    try (FileWriter writer = new FileWriter("appointments.txt", true)) { // Using true to append to the file
      for (Appointment appointment : appointments) {
        // Write the details of each appointment followed by a newline for separation
        writer.write(appointment.getAppointmentDetails() + "\n\n"); 
      }
    } catch (IOException e) {
      // Handle the case where an error occurs while writing to the file
      System.out.println("An error occurred while saving appointments.");
      e.printStackTrace(); // Print the error stack trace for debugging
    }

    // Clear the list of appointments after saving them to the file
    appointments.clear();
  }
}