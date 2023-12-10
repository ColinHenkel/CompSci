/* 
*   Final Project
*   Jonathan Matos
*/

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class JonFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personList = new ArrayList<Person>();
        int choice = 0;

        do {
            // Welcome message and display of choices
            System.out.println("\n\tWelcome to my Personal Management Program\n");
            System.out.println("Choose one of the options:");
            System.out.println("1- Enter the information a faculty");
            System.out.println("2- Enter the information of a student");
            System.out.println("3- Print tuition invoice for a student");
            System.out.println("4- Print faculty information");
            System.out.println("5- Enter the information of a staff member");
            System.out.println("6- Print the information of a staff member");
            System.out.println("7- Delete a person");
            System.out.println("8- Exit Program");

            // Get user choice and catch exceptions
            System.out.print("\tEnter your selection: ");
            choice = sc.nextInt();

            Pattern idPattern = Pattern.compile("[A-Za-z]{2}\\d{4}");

            switch (choice) {
                case 1:
                    Faculty faculty = new Faculty(null, null, null, null);
                    String tempName, tempID, tempDept, tempRank;

                    System.out.println("Enter the Faculty info:");

                    System.out.print("\tName of the faculty member: ");
                    sc.nextLine();
                    tempName = sc.nextLine();
                    faculty.setFullName(tempName);
                    System.out.println();

                    do {
                        System.out.print("\tID: ");
                        tempID = sc.nextLine();

                        if (idPattern.matcher(tempID).matches()) {
                            faculty.setId(tempID);
                        } else {
                            System.out.println("\tSorry, the ID (" + tempID + ") is invalid");
                        }
                    } while (!idPattern.matcher(tempID).matches());

                    do {
                        System.out.print("\tRank: ");
                        tempRank = sc.nextLine();
                        System.out.println();

                        if (tempRank.equalsIgnoreCase("professor") || tempRank.equalsIgnoreCase("adjunct")) {
                            faculty.setRank(capitalizationFix(tempRank));
                        } else {
                            System.out.println("Sorry, the rank (" + tempRank + ") is invalid");
                        }
                    } while (!tempRank.equalsIgnoreCase("professor") && !tempRank.equalsIgnoreCase("adjunct"));

                    do {
                        System.out.print("\tDepartment: ");
                        tempDept = sc.nextLine();
                        System.out.println();

                        if (tempDept.equalsIgnoreCase("mathematics") || tempDept.equalsIgnoreCase("engineering")
                                || tempDept.equalsIgnoreCase("english")) {
                            faculty.setDept(capitalizationFix(tempDept));
                        } else {
                            System.out.println("\tSorry, the department (" + tempDept + ") is invalid");
                        }
                    } while (!tempDept.equalsIgnoreCase("mathematics") && !tempDept.equalsIgnoreCase("engineering") && !tempDept.equalsIgnoreCase("english"));

                    personList.add(faculty);
                    System.out.printf("Thank you. Faculty added.");
                    System.out.println();

                    break;

                case 2:
                    Double tempGPA = null;
                    int tempCredits = 0;
                    Student student = new Student(null, null, 0.00, 0);

                    System.out.println("Enter the student's information:");
                    System.out.print("\tName of Student: ");
                    sc.nextLine();
                    String tempFullname = sc.nextLine();
                    student.setFullName(tempFullname);

                    do {
                        System.out.print("\tID: ");
                        tempID = sc.nextLine();

                        if (idPattern.matcher(tempID).matches()) {
                            boolean duplicate = false;
                            for (Person p : personList) {
                                if (p.getId().equalsIgnoreCase(tempID)) {
                                    duplicate = true;
                                    break;
                                }
                            }
                            if (!duplicate) {
                                student.setId(tempID);
                                break;
                            } else {
                                System.out.println("\tID already exists. Please enter a unique ID.");
                            }
                        } else {
                            System.out.println("\tSorry, the ID (" + tempID + ") is invalid");
                        }
                    } while (true);

                    do {
                        System.out.print("\tGPA: ");
                        tempGPA = sc.nextDouble();
                        if (tempGPA > 0.0 && tempGPA <= 4.0) {
                            student.setGpa(tempGPA);
                            break;
                        } else {
                            System.out.println("\tSorry, the GPA (" + tempGPA + ") is invalid");
                        }
                    } while (tempGPA < 0.0 || tempGPA > 4.0);

                    do {
                        System.out.print("\tCredit Hours: ");
                        tempCredits = sc.nextInt();
                        System.out.println();
                        if (tempCredits > 0) {
                            student.setCurrentCreditHours(tempCredits);
                        } else {
                            System.out.println("\tSorry, the amount of credit hours (" + tempCredits + ") is invalid");
                        }
                    } while (tempCredits <= 0);
                    
                    personList.add(student);
                    System.out.printf("Thank you. Student added.");
                    System.out.println();

                    break;

                case 3:
                    String targetID;
                    sc.nextLine();
                    System.out.print("\tEnter the student's ID: ");
                    targetID = sc.nextLine();

                    boolean found = false;

                    for (Person p : personList) {
                        if (p instanceof Student && p.getId().equalsIgnoreCase(targetID)) {
                            Student tuitionStudent = (Student) p;
                            tuitionStudent.print();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("\tNo student matched");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("\tEnter the Faculty's ID: ");
                    targetID = sc.nextLine();

                    found = false;

                    for (Person p : personList) {
                        if (p instanceof Faculty && p.getId().equalsIgnoreCase(targetID)) {
                            Faculty infoFaculty = (Faculty) p;
                            infoFaculty.print();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("\tNo faculty matched");
                    }
                    break;

                case 5:
                    Staff staff = new Staff(null, null, null, null);
                    String tempStatus;

                    System.out.println("Enter the Staff info:");
                    System.out.print("\tName of the Staff member: ");
                    sc.nextLine();
                    tempFullname = sc.nextLine();
                    staff.setFullName(tempFullname);
                    System.out.println();

                    do {
                        System.out.print("\tID: ");
                        tempID = sc.nextLine();

                        if (idPattern.matcher(tempID).matches()) {
                            staff.setId(tempID);
                        } else {
                            System.out.println("\tSorry, the ID (" + tempID + ") is invalid");
                        }
                    } while (!idPattern.matcher(tempID).matches());

                    do {
                        System.out.print("\tDepartment: ");
                        tempDept = sc.nextLine();
                        System.out.println();

                        if (tempDept.equalsIgnoreCase("mathematics") || tempDept.equalsIgnoreCase("engineering") || tempDept.equalsIgnoreCase("english")) {

                            staff.setDept(capitalizationFix(tempDept));
                        } else {
                            System.out.println("Sorry, the department (" + tempDept + ") is invalid");
                        }
                    } while (!tempDept.equalsIgnoreCase("mathematics") && !tempDept.equalsIgnoreCase("engineering") && !tempDept.equalsIgnoreCase("english"));

                    do {
                        System.out.print("Status (Enter F for full-time and P for part-time): ");
                        tempStatus = sc.nextLine();

                        if (tempStatus.equalsIgnoreCase("p") || tempStatus.equalsIgnoreCase("f")) {
                            staff.setStatus(tempStatus);
                            System.out.println();
                        } else {
                            System.out.println("Sorry, the status (" + tempStatus + ") is invalid");
                        }

                    } while (!tempStatus.equalsIgnoreCase("p") && !tempStatus.equalsIgnoreCase("f"));

                    personList.add(staff);
                    System.out.println("Staff member added!");

                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("Enter the staff's ID: ");
                    targetID = sc.nextLine();

                    found = false;
                    for (Person p : personList) {
                        if (p instanceof Staff && p.getId().equalsIgnoreCase(targetID)) {
                            Staff infoStaff = (Staff) p;
                            infoStaff.print();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("No staff matched");
                    }
                    break;

                case 7:
                    sc.nextLine();
                    System.out.print("Enter the ID of the person to delete: ");
                    targetID = sc.nextLine();

                    found = false;
                    for (Person p : personList) {
                        if (p != null && p.getId().equals(targetID)) {
                            personList.remove(p);
                            System.out.println("\tPerson deleted successfully!");

                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.print("Sorry, no such person exits.");
                    }

                    break;

                case 8:
                    sc.nextLine();
                    System.out.print("Would you like to create the report? (Y/N): ");
                    String choiceReport = sc.nextLine();

                    if (choiceReport.equalsIgnoreCase("y")) {
                        System.out.print("Would like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
                        int choiceSort = sc.nextInt();
                        generateReport(personList, choiceSort);
                        System.out.println("Report created and saved on your hard drive!");
                    }

                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid entry - please try again");
                    break;

            }
        } while (choice != 8);
        sc.close();

    }

    private static void generateReport(ArrayList<Person> personList, int choiceSort) {
        Collections.sort(personList, new Comparator<Person>() {

            @Override
            public int compare(Person p1, Person p2) {
                if (choiceSort == 1) {
                    if (p1 instanceof Student && p2 instanceof Student) {
                        Student s1 = (Student) p1;
                        Student s2 = (Student) p2;
                        int result = Double.compare(s2.getGpa(), s1.getGpa());
                        if (result == 0) {
                            return s1.getFullName().compareToIgnoreCase(s2.getFullName());
                        }
                        return result;
                    } else {
                        return p1.getFullName().compareToIgnoreCase(p2.getFullName());
                    }
                } else if (choiceSort == 2) {
                    return p1.getFullName().compareToIgnoreCase(p2.getFullName());
                }
                return 0;
            }
        });

        try {
            FileWriter fileWriter = new FileWriter("report.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Report created on " + java.time.LocalDate.now());
            // Print faculty members
            printWriter.println("Faculty Members");
            int facultyCount = 0;
            for (Person person : personList) {
                if (person instanceof Faculty) {
                    Faculty faculty = (Faculty) person;
                    printWriter.println(++facultyCount + ". " + faculty.getFullName() + " ID: " + faculty.getId() + " " + faculty.getRank() + ", " + faculty.getDept());
                }
            }
            // Print staff members
            printWriter.println("\nStaff Members");
            int staffCount = 0;
            for (Person person : personList) {
                if (person instanceof Staff) {
                    Staff staff = (Staff) person;
                    printWriter.println(++staffCount + ". " + staff.getFullName() + " ID: " + staff.getId() + " " + staff.getDept() + ", " + staff.getStatus());
                }
            }
            // Print students
            printWriter.println("\nStudents (Sorted by GPA in descending order)");
            int studentCount = 0;
            for (Person person : personList) {
                if (person instanceof Student) {
                    Student student = (Student) person;
                    printWriter.println(++studentCount + ". " + student.getFullName() + " ID: " + student.getId() + " GPA: " + student.getGpa() + " Credit hours: " + student.getCurrentCreditHours());
                }
            }
            printWriter.close();
            System.out.println("Report created and saved in report.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating the report!");
        }
    }

    // for proper capitalization
	public static String capitalizationFix(String input) {
		if(input == null || input.isEmpty())
			return input;
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}
}

// ----------------------
abstract class Person {
    private String fullName, id;

    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // To be overidden by Student and Employee
    public abstract void print();
}

// ----------------------------------------------------
class Student extends Person {
    // Variables
    private double gpa;
    private int currentCreditHours;

    public Student(String fullName, String id, double gpa, int credit) {
        super(fullName, id);
        this.gpa = gpa;
    }

    // Getters and Setters
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getCurrentCreditHours() {
        return currentCreditHours;
    }

    public void setCurrentCreditHours(int currentCreditHours) {
        this.currentCreditHours = currentCreditHours;
    }

    // Method to search for student
    public static Student searchStudentID(ArrayList<Person> personList, String targetID) {
        for (Person p : personList) {
            if (p instanceof Student) {
                Student student = (Student) p;
                if (student.getId().equals(targetID)) {
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public void print() {
        System.out.println("Here is the tuition invoice for " + getFullName() + ":");

        System.out.println("---------------------------------------------------------------------------");
        System.out.println(getFullName() + "\t\t" + getId());
        System.out.println("Credit Hours:" + getCurrentCreditHours() + "+($236.45/credit hour)");
        System.out.println("Fees: $52");
        if (gpa >= 3.85) {
            double discount = ((getCurrentCreditHours() * 236.45) * .25);
            double totalPayment = (getCurrentCreditHours() * 236.45) - discount;

            System.out.printf("Total Payment (after discount): $%.2f\t\t($%.2f discount applied)", totalPayment, discount);
        } else {
            double totalPayment = (currentCreditHours * 236.45);
            System.out.printf("Total Payment (after discount): $%.2f\t\t($0 discount applied)", totalPayment);
        }
        System.out.println("---------------------------------------------------------------------------");

    }
}

// ---------------------------------------------------------------
abstract class Employee extends Person {
    // Variables
    private String dept;

    // Getters and Setters
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public abstract void print();

    // Constructor
    public Employee(String fullName, String id, String department) {
        super(fullName, id);
    }
}

// -------------------------------------
class Faculty extends Employee {
    private String rank;

    // Constructor
    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        this.rank = rank;
    }

    // Getters and Setters
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // Method to search for faculty
    public Faculty searchFacultyID(ArrayList<Person> personList, String targetID) {
        for (Person p : personList) {
            if (p instanceof Faculty) {
                Faculty faculty = (Faculty) p;
                if (faculty.getId().equals(targetID)) {
                    return faculty;
                }
            }
        }
        return null;
    }

    @Override
    public void print() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(getFullName() + "\t\t\t" + getId());
        System.out.println(getDept() + " Department, " + getRank());
        System.out.println("---------------------------------------------------------------------------");
    }
}

// ------------------------------------
class Staff extends Employee {
    private String status;

    // Constructor
    public Staff(String fullName, String id, String dept, String status) {
        super(fullName, id, dept);
        this.status = status;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to search for Staff
    public static Staff searchStaffID(ArrayList<Person> personList, String targetID) {
        for (Person p : personList) {
            if (p instanceof Staff) {
                Staff staff = (Staff) p;
                if (staff.getId().equals(targetID)) {
                    return staff;
                }
            }
        }
        return null;
    }

    @Override
    public void print() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(getFullName() + "\t\t\t" + getId());
        if(getStatus().equalsIgnoreCase("f")) {
            System.out.println(getDept() + " Department, Full Time");
            System.out.println("---------------------------------------------------------------------------");
        } else {
            System.out.println(getDept() + " Department, Part Time");
            System.out.println("---------------------------------------------------------------------------");
        }
    }
}