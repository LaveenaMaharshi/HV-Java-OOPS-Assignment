import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Visitor {
    private String name;
    private String phone;
    private String email;

    public Visitor(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter and Setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Appointment {
    private String date;
    private String timeSlot;
    private Visitor visitor;

    public Appointment(String date, String timeSlot, Visitor visitor) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.visitor = visitor;
    }

    // Getter and Setter methods

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}

class Clinic {
    private List<Visitor> visitorList;
    private List<Appointment> appointmentList;

    public Clinic() {
        visitorList = new ArrayList<>();
        appointmentList = new ArrayList<>();
    }

    public void addVisitor(Visitor visitor) {
        visitorList.add(visitor);
        System.out.println("Visitor added successfully!");
    }

    public void editVisitorDetails(Visitor visitor) {
        // Find the visitor in the list and update their details
        for (Visitor v : visitorList) {
            if (v.getName().equalsIgnoreCase(visitor.getName())) {
                v.setPhone(visitor.getPhone());
                v.setEmail(visitor.getEmail());
                System.out.println("Visitor details updated successfully!");
                return;
            }
        }
        System.out.println("Visitor not found!");
    }

    public void viewVisitors() {
        System.out.println("Visitor List:");
        for (Visitor visitor : visitorList) {
            System.out.println("Name: " + visitor.getName());
            System.out.println("Phone: " + visitor.getPhone());
            System.out.println("Email: " + visitor.getEmail());
            System.out.println("-------------------------");
        }
    }

    public void viewAppointmentSchedule(String date) {
        System.out.println("Appointment Schedule for " + date + ":");
        for (Appointment appointment : appointmentList) {
            if (appointment.getDate().equals(date)) {
                System.out.println("Time Slot: " + appointment.getTimeSlot());
                System.out.println("Visitor Name: " + appointment.getVisitor().getName());
                System.out.println("Visitor Phone: " + appointment.getVisitor().getPhone());
                System.out.println("Visitor Email: " + appointment.getVisitor().getEmail());
                System.out.println("-------------------------");
            }
        }
    }

    public void bookAppointment(String date, String timeSlot, Visitor visitor) {
        // Check if the time slot is available
        for (Appointment appointment : appointmentList) {
            if (appointment.getDate().equals(date) && appointment.getTimeSlot().equals(timeSlot)) {
                System.out.println("The time slot is already booked. Please choose a different slot.");
                return;
            }
        }

        // Create a new appointment and add it to the list
        Appointment appointment = new Appointment(date, timeSlot, visitor);
        appointmentList.add(appointment);
        System.out.println("Appointment booked successfully!");
    }

    public void editCancelAppointment(String date, String timeSlot) {
        // Find the appointment in the list and update or cancel it
        for (Appointment appointment : appointmentList) {
            if (appointment.getDate().equals(date) && appointment.getTimeSlot().equals(timeSlot)) {
                appointmentList.remove(appointment);
                System.out.println("Appointment removed successfully!");
                return;
            }
        }
        System.out.println("Appointment not found!");
    }
}

public class AppointmentManagementSystem {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Appointment Management System - Menu:");
            System.out.println("1. View list of all Visitors");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    clinic.viewVisitors();
                    break;
                case 2:
                    System.out.print("Enter Visitor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Visitor Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Visitor Email: ");
                    String email = scanner.nextLine();

                    Visitor visitor = new Visitor(name, phone, email);
                    clinic.addVisitor(visitor);
                    break;
                case 3:
                    System.out.print("Enter Visitor Name to edit details: ");
                    String visitorName = scanner.nextLine();
                    System.out.print("Enter Visitor New Phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Enter Visitor New Email: ");
                    String newEmail = scanner.nextLine();

                    Visitor editedVisitor = new Visitor(visitorName, newPhone, newEmail);
                    clinic.editVisitorDetails(editedVisitor);
                    break;
                case 4:
                    System.out.print("Enter date to view the appointment schedule (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    clinic.viewAppointmentSchedule(date);
                    break;
                case 5:
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    String appDate = scanner.nextLine();
                    System.out.print("Enter Appointment Time Slot: ");
                    String timeSlot = scanner.nextLine();

                    System.out.print("Enter Visitor Name: ");
                    String visitorName2 = scanner.nextLine();
                    System.out.print("Enter Visitor Phone: ");
                    String phone2 = scanner.nextLine();
                    System.out.print("Enter Visitor Email: ");
                    String email2 = scanner.nextLine();

                    Visitor visitor2 = new Visitor(visitorName2, phone2, email2);
                    clinic.bookAppointment(appDate, timeSlot, visitor2);
                    break;
                case 6:
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    String cancelDate = scanner.nextLine();
                    System.out.print("Enter Appointment Time Slot: ");
                    String cancelTimeSlot = scanner.nextLine();

                    clinic.editCancelAppointment(cancelDate, cancelTimeSlot);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println("-------------------------");
        }
    }
}
