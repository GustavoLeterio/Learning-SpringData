package br.com.gustavoleterio.learningspringdata.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.gustavoleterio.learningspringdata.orm.Employee;
import br.com.gustavoleterio.learningspringdata.orm.EmployeeProjection;
import br.com.gustavoleterio.learningspringdata.repository.EmployeeRepository;
import br.com.gustavoleterio.learningspringdata.specification.SpecificationEmployee;

@Service
public class ReportService {
	private boolean system;
	private final EmployeeRepository employeeRepository;

	public ReportService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void start(Scanner scan) throws ParseException {
		system = true;
		while (system) {
			System.out.println("------- Report Window -------");
			System.out.println("Select an Action");
			System.out.println("0 - Exit");
			System.out.println("1 - Find Employee By Name");
			System.out.println("2 - Find Employee By Name (dynamic)");
			System.out.println("3 - Find Employee By Name and HiringDate With a Salary Greater Than a Value");
			System.out.println("4 - Find Employee By Hiring Date Greater Than Another Date");
			System.out.println("5 - Project Employees and Salary");

			switch (scan.nextInt()) {
			case 1:
				findEmployeeByName(scan);
				break;
			case 2:
				findByNameDynamic(scan);
				break;
			case 3:
				findEmployeeByNameHiringDateSalaryGreaterThan(scan);
				break;
			case 4:
				findEmployeeByHiringDateGreaterThan(scan);
				break;
			case 5:
				findEmployeesSalary();
				break;
			default:
				system = false;
				break;
			}

		}

	}

	private void findEmployeeByName(Scanner scan) {
		System.out.println("\nFind Employee By Name");

		List<Employee> employees = employeeRepository.findByName(scan.next());

		System.out.println("Showing Employees...");
		if (employees.isEmpty())
			System.out.println("Couldn't find any employee.");
		else
			employees.forEach(System.out::println);
	}

	private void findByNameDynamic(Scanner scan) {
		System.out.println("\nFind Employee By Name");
		System.out.println("Showing Employees...");
		List<Employee> employees = employeeRepository
				.findAll(Specification.where(SpecificationEmployee.name(scan.next())));
		if (employees.isEmpty())
			System.out.println("Couldn't find any employee.");
		else
			employees.forEach(System.out::println);
	}

	private void findEmployeeByNameHiringDateSalaryGreaterThan(Scanner scan) throws ParseException {
		System.out.println("\nFind Employee By Name and HiringDate With a Salary Greater Than a Value");

		System.out.println("\nEmployee Name:");
		String name = scan.next();

		System.out.println("Employee Hiring Date (dd/MM/yyyy):");
		Date hiringDate = new SimpleDateFormat("dd/MM/yyyy").parse(scan.next());

		System.out.println("\nEmployee Salary:");
		Double salary = scan.nextDouble();

		List<Employee> employees = employeeRepository.findByNameAndHiringDateAndSalaryGreaterThan(name, hiringDate,
				salary);

		System.out.println("Showing Employees...");
		if (employees.isEmpty())
			System.out.println("Couldn't find any employee.");
		else
			employees.forEach(System.out::println);
	}

	private void findEmployeeByHiringDateGreaterThan(Scanner scan) throws ParseException {
		System.out.println("\nFind Employee By Hiring Date Greater Than Another Date");

		List<Employee> employees = employeeRepository
				.findByHiringDateGreaterThan(new SimpleDateFormat("dd/MM/yyyy").parse(scan.next()));

		System.out.println("Showing Employees...");
		if (employees.isEmpty())
			System.out.println("Couldn't find any employee.");
		else
			employees.forEach(System.out::println);
	}

	private void findEmployeesSalary() {
		List<EmployeeProjection> employees = employeeRepository.findEmployeeSalary();
		employees
				.forEach(employee -> System.out.println("Employee " + employee.getId() + " {\n\tid: " + employee.getId()
						+ ",\n\tName: " + employee.getName() + ",\n\tSalary: " + employee.getSalary() + "\n}"));
	}
}
