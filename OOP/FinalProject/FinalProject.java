import java.util.*;
import java.util.regex.*;
import java.io.*;

public class FinalProject {
	public static void main(String[] args) {
		ArrayList<Person> persons = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			int selection = menu();
			switch(selection) {
			default:
				System.out.println("\nInvalid selection, please try again.\n");
				break;
			case 8:
				// ask if a report needs to be generated and handles input accordingly
				System.out.print("Would you like to create the report? (Y/N): ");
				String reportOption = scan.nextLine();
				if(reportOption.equalsIgnoreCase("N")) {
					System.out.println("Report not created. Goodbye!");
					scan.close();
					return;
				}
				
				// asks for sorting and sorts
				System.out.print("Would you like to sort your students by descending gpa or name (gpa = 1, name = 2): ");
				int sortChoice = (new Scanner(System.in)).nextInt();
				while (sortChoice != 1 && sortChoice != 2) {
					System.out.print("Would you like to sort your students by descending gpa or name (gpa = 1, name = 2): ");
					
					if(scan.hasNextInt())
						sortChoice = scan.nextInt();
					else
						scan.next();
				}
				if(sortChoice == 1)
					Collections.sort(persons, new SortByGPA());
				if(sortChoice == 2)
					Collections.sort(persons, new SortByName());
				
				//printing to file
				writeToFile(persons);
				
				System.out.println("Report created and saved!");
				System.out.println("Goodbye!");
				scan.close();
				return;
			case 2:
				Pattern idPatt = Pattern.compile("[A-Za-z]{2}\\d{4}");
				String user_id, user_name;
				double user_gpa;
				int user_hours;
				System.out.println("\nEnter the student info:");
				System.out.print("\tName of the student: ");
				user_name = scan.nextLine();
				do {
					System.out.print("\tID: ");
					user_id = scan.nextLine();
					if (!idPatt.matcher(user_id).matches()) {
						System.out.println("\t\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit.");
		            }
				} while(!idPatt.matcher(user_id).matches());
				System.out.print("\tGPA: ");
				user_gpa = scan.nextDouble();
				System.out.print("\tCredit hours: ");
				user_hours = scan.nextInt();
				scan.nextLine(); // consume extra newline character
				persons.add(new Student(user_name, user_id, user_gpa, user_hours));
				System.out.println("\nStudent added!");
				break;
			case 1:
				idPatt = Pattern.compile("[A-Za-z]{2}\\d{4}");
				String user_rank, user_dep;
				System.out.println("\nEnter the faculty info:");
				System.out.print("\tName of the faculty: ");
				user_name = scan.nextLine();
				do {
					System.out.print("\tID: ");
					user_id = scan.nextLine();
					if (!idPatt.matcher(user_id).matches())
		                System.out.println("\t\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit.");
				} while(!idPatt.matcher(user_id).matches());
				do {
					System.out.print("\tRank: ");
					user_rank = scan.nextLine();
					if(user_rank.compareToIgnoreCase("Professor") != 0 && user_rank.compareToIgnoreCase("Adjunct") != 0)
						System.out.println("\t\t" + "\"" + user_rank + "\"" + " " + "is invalid.");
					user_rank = capitalizationFix(user_rank);
				} while(user_rank.compareToIgnoreCase("Professor") != 0 && user_rank.compareToIgnoreCase("Adjunct") != 0);
				do {
					System.out.print("\tDepartment: ");
					user_dep = scan.nextLine();
					if(user_dep.compareToIgnoreCase("English") != 0 && user_dep.compareToIgnoreCase("Engineering") != 0 && user_dep.compareToIgnoreCase("Mathematics") != 0)
						System.out.println("\t\t" + "\"" + user_dep + "\"" + "  is invalid.");
					user_dep = capitalizationFix(user_dep);
				} while(user_dep.compareToIgnoreCase("English") != 0 && user_dep.compareToIgnoreCase("Engineering") != 0 && user_dep.compareToIgnoreCase("Mathematics") != 0);
				persons.add(new Faculty(user_name, user_id, user_rank, user_dep));
				System.out.println("\nFaculty added!");
				break;
			case 3:
				System.out.print("Enter the Student's ID: ");
				String studentId = scan.nextLine();
				Student foundStu = searchStudentId(persons, studentId);
				if(foundStu != null) {
					System.out.println("\nHere is the tuition invoice for " + foundStu.getFullName() + ":\n");
			        foundStu.print();
				} else {
					System.out.println("\nStudent with ID " + studentId + " not found.\n");
				}
				break;
			case 4:
				System.out.print("Enter the Faculty's ID: ");
				String facId = scan.nextLine();
				Faculty foundFac = searchFacultyId(persons, facId);
				if(foundFac != null)
					foundFac.print();
				else
					System.out.println("\nFaculty with ID " + facId + " not found.\n");
				break;
			case 5:
				idPatt = Pattern.compile("[A-Za-z]{2}\\d{4}");
				String user_status;
				System.out.println("\nEnter the staff info:");
				System.out.print("\tName of the staff member: ");
				user_name = scan.nextLine();
				do {
					System.out.print("\tID: ");
					user_id = scan.nextLine();
					if (!idPatt.matcher(user_id).matches())
		                System.out.println("\t\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit.");
				} while(!idPatt.matcher(user_id).matches());
				do {
					System.out.print("\tDepartment: ");
					user_dep = scan.nextLine();
					if(user_dep.compareToIgnoreCase("English") != 0 && user_dep.compareToIgnoreCase("Engineering") != 0 && user_dep.compareToIgnoreCase("Mathematics") != 0)
						System.out.println("\t\t" + "\"" + user_dep + "\"" + "  is invalid.");
					user_dep = capitalizationFix(user_dep);
				} while(user_dep.compareToIgnoreCase("English") != 0 && user_dep.compareToIgnoreCase("Engineering") != 0 && user_dep.compareToIgnoreCase("Mathematics") != 0);
				do {
					System.out.print("Status, Enter P for Part Time or F for Full Time: ");
					user_status = scan.nextLine();
					if(user_status.compareToIgnoreCase("P") != 0 && user_status.compareToIgnoreCase("F") != 0)
						System.out.println("\t\t" + "\"" + user_status + "\"" + " is invalid.");
					user_status = capitalizationFix(user_status);
				} while(user_status.compareToIgnoreCase("P") != 0 && user_status.compareToIgnoreCase("F") != 0);
				persons.add(new Staff(user_name, user_id, user_dep, user_status));
				System.out.println("\nStaff added!");
				break;
			case 6:
				System.out.print("Enter the Staff's ID: ");
				String staffId = scan.nextLine();
				Staff foundStaff = searchStaffId(persons, staffId);
				if(foundStaff != null)
					foundStaff.print();
				else
					System.out.println("\nStaff with ID " + staffId + " not found.\n");
				break;
			case 7:
				System.out.print("Enter the ID of the person you want to remove: ");
			    String idToRemove = scan.nextLine();
			    boolean removed = removePersonById(persons, idToRemove);
			    if (removed)
			        System.out.println("\tPerson with ID " + idToRemove + " removed successfully.\n");
			    else
			        System.out.println("No such person exists.\n");
			    break;
			}
		}
	}
	
	// menu for user selection
	public static int menu() {
		int option;
		System.out.println("1 - Enter the information of a faculty");
		System.out.println("2 - Enter the information of a student");
		System.out.println("3 - Print tuition invoice for a student");
		System.out.println("4 - Print faculty information");
		System.out.println("5 - Enter the information of a staff member");
		System.out.println("6 - Print the information of a staff member");
		System.out.println("7 - Delete a person");
		System.out.println("8 - Exit Program");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("\tEnter your selection: ");
		String input = scanner.nextLine();
	    try {
	        option = Integer.parseInt(input);
	    } catch (NumberFormatException e) {
	        option = -1;
	    }
		scanner.close();
		return option;
	}
	
	// for proper capitalization
	public static String capitalizationFix(String input) {
		if(input == null || input.isEmpty())
			return input;
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}
	
	// method for student searching
	public static Student searchStudentId(ArrayList<Person> persons, String targetId) {
		for(Person p: persons) {
			if (p instanceof Student) {
				Student student = (Student) p;
				if(student.getId().equals(targetId))
					return student;
			}
		}
		
		return null;
	}
	
	// method for faculty searching
	public static Faculty searchFacultyId(ArrayList<Person> persons, String targetId) {
		for(Person p: persons) {
			if (p instanceof Faculty) {
				Faculty faculty = (Faculty) p;
				if(faculty.getId().equals(targetId))
					return faculty;
			}
		}
			
		return null;
	}
	
	// method for staff searching
	public static Staff searchStaffId(ArrayList<Person> persons, String targetId) {
		for(Person p: persons) {
			if (p instanceof Staff) {
				Staff staff = (Staff) p;
				if(staff.getId().equals(targetId))
					return staff;
			}
		}
			
		return null;
	}
	
	// method for removing person via Id
	public static boolean removePersonById(ArrayList<Person> persons, String id) {
	    for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
	        Person person = iterator.next();
	        if (person!= null && person.getId().equals(id)) {
	            iterator.remove();
	            return true;
	        }
	    }
	    return false;
	}
	
	// method for printing to file
	public static void writeToFile(ArrayList<Person> persons) {
		File file = new File("report.txt");
		try (PrintStream fileTxt = new PrintStream(file)) {
			ArrayList<Person> facultys = new ArrayList<>();
			ArrayList<Person> staffs = new ArrayList<>();
			ArrayList<Person> students = new ArrayList<>();
			
			// group by type
			for (Person p : persons) {
				if(p instanceof Faculty)
					facultys.add(p);
				else if(p instanceof Staff)
					staffs.add(p);
				else if(p instanceof Student)
					students.add(p);
			}
			
			// print faculty members
			fileTxt.println("Faculty Members");
			fileTxt.println("-------------------------");
			int facultyIndex = 1;
			for(Person f: facultys) {
				Faculty facultyMem = (Faculty) f;
				fileTxt.println("\t" + facultyIndex + ". " + facultyMem.getFullName());
				fileTxt.println("\tID: " + facultyMem.getId());
				fileTxt.println("\t" + facultyMem.getRank() + ", " + facultyMem.getDepartment());
				fileTxt.println();
				facultyIndex++;
			}
			
			// print staff members
			fileTxt.println("Staff Members");
			fileTxt.println("-------------------");
			int staffIndex = 1;
			for(Person s: staffs) {
				Staff staffMem = (Staff) s;
				if(staffMem.getStatus().compareToIgnoreCase("F") == 0) {
					fileTxt.println("\t" + staffIndex + ". " + staffMem.getFullName());
					fileTxt.println("\tID: " + staffMem.getId());
					fileTxt.println("\t" + staffMem.getDepartment() + ", Full Time");
				} else if(staffMem.getStatus().compareToIgnoreCase("P") == 0) {
					fileTxt.println("\t" + staffIndex + ". " + staffMem.getFullName());
					fileTxt.println("\tID: " + staffMem.getId());
					fileTxt.println("\t" + staffMem.getDepartment() + ", Part Time");
				}
				fileTxt.println();
				staffIndex++;
			}
			
			// print students
			fileTxt.println("Students");
	        fileTxt.println("-----------");
	        int stuIndex = 1;
	        for(Person st: students) {
	        	Student student = (Student) st;
	        	fileTxt.println("\t" + stuIndex + ". " + student.getFullName());
	        	fileTxt.println("\tID: " + student.getId());
	        	fileTxt.println("\tGPA: " + String.format("%.2f", student.getGpa()));
	        	fileTxt.println("\tCredit hours: " + student.getCreditHours());
	        	fileTxt.println();
	        	stuIndex++;
	        }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

// method for sorting students by GPA
class SortByGPA implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		if(p1 instanceof Student && p2 instanceof Student) {
			Student s1 = (Student) p1, s2 = (Student) p2;
			return Double.compare(s2.getGpa(), s1.getGpa());
		} else { return 0; }
	}
}

// method for sorting students by Name
class SortByName implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		if(p1 instanceof Student && p2 instanceof Student) {
			Student s1 = (Student) p1, s2 = (Student) p2;
			return s2.getFullName().compareTo(s1.getFullName());
		} else { return 0; }
	}
}

abstract class Person {
	public abstract void print();
	private String fullName;
	private String id;
	
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
	
	public Person(String fullName, String id) {
		this.fullName = fullName;
		this.id = id;
	}
}

abstract class Employee extends Person{
	private String department;
	
	// setters and getters
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	// constructors w/ fields and default
	public Employee(String fullName, String id, String department) {
		super(fullName, id);
		this.department = department;
	}
	public Employee() {
		super("N/A", "N/A");
		this.department = "N/A";
	}
}

class Student extends Person {
	private double gpa;
	private int creditHours;
	
	// setters and getters
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public int getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	// constructors w/ default and fields
	public Student(String fullName, String id, double gpa, int creditHours) {
		super(fullName, id);
		this.gpa = gpa;
		this.creditHours = creditHours;
	}
	public Student() {
		super("N/A", "N/A");
		this.gpa = 0.0;
		this.creditHours = 0;
	}
	
	@Override
	public void print() {
		double discount;
		double total;
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println(this.getFullName() + "\t\t" + this.getId());
		System.out.println("Credit Hours:" + this.getCreditHours() + " " + "($236.45/credit hour)");
		System.out.println("Fees:" + " " + "$52");
		if(!(this.gpa >= 3.85)) {
			total = (this.getCreditHours() * 236.45) + 52;
			System.out.println("Total payment (after discount): $" + String.format("%.2f", total) + "\t" + "($0 discount applied)");
		} else {
			total = (this.getCreditHours() * 236.45) + 52;
			discount = total * 0.25;
			total -= discount;
			System.out.println("Total payment (after discount): $" + String.format("%.2f", total) + "\t" + "($" + String.format("%.2f", discount) + " discount applied)");
		}
		System.out.println("---------------------------------------------------------------------------\n");
	}
}

class Faculty extends Employee {
	private String rank;
	
	// setters and getters
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

	// constructors w/ fields and default
	public Faculty(String fullName, String id, String department, String rank) {
		super(fullName, id, department);
		this.rank = rank;
	}
	public Faculty() {
		super("N/A", "N/A", "N/A");
		this.rank = "N/A";
	}
	
	@Override
	public void print() {
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println(this.getFullName() + "\t\t" + this.getId());
		System.out.println(this.getDepartment() + " Department, " + this.getRank());
		System.out.println("---------------------------------------------------------------------------\n");
	}
}

class Staff extends Employee {
	private String status;
	
	// setters and getters
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	// constructors w/ fields and default
	public Staff(String fullName, String id, String department, String status) {
		super(fullName, id, department);
		this.status = status;
	}
	public Staff() {
		super("N/A", "N/A", "N/A");
		this.status = "N/A";
	}
	
	@Override
	public void print() {
		if(this.getStatus().compareToIgnoreCase("F") == 0) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println(this.getFullName() + "\t\t" + this.getId());
			System.out.println(this.getDepartment() + " Department, " + "Full Time");
			System.out.println("---------------------------------------------------------------------------\n");
		} else if(this.getStatus().compareToIgnoreCase("P") == 0) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println(this.getFullName() + "\t\t" + this.getId());
			System.out.println(this.getDepartment() + " Department, " + "Part Time");
			System.out.println("---------------------------------------------------------------------------\n");
		}
	}
}
