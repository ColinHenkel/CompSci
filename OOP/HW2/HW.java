import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class HW {
	
	public static void main(String[] args) {
		String fullName = "Erika T. Jones";
		String employeeNumber = "ej789";
		double payRate = 100.0, hoursWorked = 1.0;
		// TA will change the payrate and the hours worked to test your code
		
		Employee e;
		e = new Employee(fullName, employeeNumber, payRate, hoursWorked);
		System.out.println(e); // To Test your toString method
		e.printCheck(); // This prints the check of Erika T. Jones
		
		Company company = new Company();
		company.hire(new Employee("Saeed Happy", "sh895", 2, 200));
		company.hire(e);
		
		Company.printCompanyInfo();
		company.hire(new Employee("Enrico Torres", "et897", 3, 150));
		//You may add as many employees to company as you want.
		//The TAs will add their own employees
		//Make sure that each employee of company has a unique employeeNumber
		
		company.printCheck("ab784");
		company.deleteEmployeesBySalary(256.36);
		company.reverseEmployees();
		
		System.out.println(company.SearchByName("WaLiD WiLLiAms"));
		company.printEmployees();
		System.out.println("Bye!");
	}
}

class Employee {
	private String fullName, employeeNumber;
	private double payRate, hoursWorked;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public Employee(String fullName, String employeeNumber, double payRate, double hoursWorked) {
		this.fullName = fullName;
		this.employeeNumber = employeeNumber;
		this.payRate = payRate;
		this.hoursWorked = hoursWorked;
	}
	
	public void printCheck() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		double taxes = (payRate * hoursWorked) * 0.06;
		
		System.out.println("\nEmployee Name:\t\t" + fullName);
		System.out.println("Employee Number:\t" + employeeNumber);
		System.out.println("Hourly Pay Rate:\t" + df.format(payRate));
		System.out.println("Hours Worked:\t\t" + df.format(hoursWorked));
		System.out.println("\nTotal Gross Pay:\t$" + df.format(payRate * hoursWorked));
		System.out.println("\nDeductions\nTax(6%):\t\t$" + df.format(taxes));
		System.out.println("\nNet Pay:\t\t$" + df.format((payRate * hoursWorked) - taxes));
	}
	
	@Override
	public String toString() {
		return "[" + employeeNumber + "/" + fullName + ", " + hoursWorked + " Hours @ " + payRate + " per hour]";
	}
}

class Company {
	private static ArrayList<Employee> employeeList;
	private static String companyName;
	private static String companyTaxId;

	public static String getCompanyName() {
		return companyName;
	}

	public static void setCompanyName(String companyName) {
		Company.companyName = companyName;
	}

	public static String getCompanyTaxId() {
		return companyTaxId;
	}

	public static void setCompanyTaxId(String companyTaxId) {
		Company.companyTaxId = companyTaxId;
	}
	
	public Company() {
		employeeList = new ArrayList<>();
		companyName = "People's Place";
		companyTaxId = "v1rtua7C0mpan1";
	}

	public boolean hire(Employee employee) {
		for(Employee emp : employeeList) {
			if(emp.getEmployeeNumber() == employee.getEmployeeNumber()) {
				System.out.println("ERROR! Provided employee number already exists within the company!");
				return false;
			}
		}
		
		employeeList.add(employee);
		return true;
	}

	public static void printCompanyInfo() {
		System.out.println("\n[" + Company.companyName + "/" + Company.companyTaxId + ", " + Company.employeeList.size() + " employees]\n");
	}

	public void printEmployees() {
		System.out.println("\nList of Employees:");
		for(Employee emp : employeeList) {
			System.out.println(emp);
		}
		
		System.out.println("\n");
	}

	public int countEmployees(double maxSalary) {
		int count = 0;
		
		for(Employee emp : employeeList) {
			double empTax = (emp.getHoursWorked() * emp.getPayRate()) * 0.06;
			double empSalary = (emp.getHoursWorked() * emp.getPayRate()) - empTax;
			
			if(empSalary < maxSalary) {
				count++;
			}
		}
		
		return count;
	}

	public boolean SearchByName(String fullName) {
		fullName = fullName.toLowerCase();
		for(Employee emp : employeeList) {
			int comparison = fullName.compareTo(emp.getFullName().toLowerCase());
			boolean sameName = (comparison == 0);
			if(sameName) {
				System.out.print(emp);
				return true;
			}
		}
		
		System.out.print("NO SUCH EMPLOYEE EXISTS/");
		return false;
	}

	public void reverseEmployees() {
		Collections.reverse(employeeList);
	}

	public void deleteEmployeesBySalary(double targetSalary) {
		Iterator<Employee> iterator = employeeList.iterator();
		while(iterator.hasNext()) {
			Employee emp = iterator.next();
			
			double empTax = (emp.getHoursWorked() * emp.getPayRate()) * 0.06;
			double empSalary = (emp.getHoursWorked() * emp.getPayRate()) - empTax;
			
			if(empSalary == targetSalary)
				iterator.remove();
		}
	}

	public void printCheck(String employeeNumber) {
		for(Employee emp: employeeList) {
			int comparison = employeeNumber.compareTo(emp.getEmployeeNumber());
			boolean foundNumber = (comparison == 0);
			if(foundNumber) {
				emp.printCheck();
				return;
			}
		}
		
		System.out.println("NO SUCH EMPLOYEE EXISTS");
	}
}
