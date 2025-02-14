# community_garden_project

## Description
This project was created as a final project for my CIS-18A course. The purpose of this project is to create an application to handle an appointment scheduling system for a community garden at MVC. This includes registering users by having them input their name, email, and phone number, scheduling a valid appointment date and time, and option for the user to make a monetary or item donation. All the appointment information will be stored in a .txt file named "appointments".

## Technology Used
- Java
- JCalendar Library (for date selection, .jar file included in repo)
- Swing Library (for GUI)

## Installation
1. Clone or download the repository:
2. Navigate to the project folder and ensure that the "jcalendar-1.4.jar" is in the same folder as the program will not compile without it.
3. Open up the command prompt and navigate to the directory where the project's files are stored. ("cd \...")
4. Compile the Java files with the following command:
   javac -cp ".;jcalendar-1.4.jar" *.java
5. Run the application with the following command:
   java -cp ".;jcalendar-1.4.jar" CommunityGardenApp

## Usage
Once the application is running, you can enter a name, email, and phone number to register for an appointment.<br>
Then to select a date, click on the calendar icon on the right side. To select a time, click within the hour or minute section (HH:mm) and use the arrows on the right side to edit the time. Make sure to select a date and time that has not already passed and is within the garden's hours (Monday-Friday: 8 AM - 8 PM, Saturday: 8 AM - 5 PM).<br>
Then once you have entered valid information, use the submit button to move onto the donation screen. You may choose whether or not to make a donation, and if so, whether you would like to donate money or items with an input of either "1" or "2".<br>
To make a monetary donation, enter in a valid amount (not zero) and select "OK".<br>
To make an item donation, input the name of the item you want to donate, select "OK", input a valid amount (not zero) of those items you want to donate, and select "OK" again.<br>
After going through this, the application can be used again to input more appointments, or you may close out of it and run it again to input more later. Either way, all the appointment information will be stored in the "appointments.txt" file. You may edit the file directly and even delete it as the program will just create another one, but be careful as the program will not restore the previous entries.
