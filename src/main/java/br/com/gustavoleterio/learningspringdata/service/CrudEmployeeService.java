package br.com.gustavoleterio.learningspringdata.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.gustavoleterio.learningspringdata.orm.Employee;
import br.com.gustavoleterio.learningspringdata.orm.Office;
import br.com.gustavoleterio.learningspringdata.repository.EmployeeRepository;
import br.com.gustavoleterio.learningspringdata.repository.OfficeRepository;
import br.com.gustavoleterio.learningspringdata.repository.RoleRepository;

@Service
public class CrudEmployeeService {
	private final EmployeeRepository employeeRepository;
	private final RoleRepository roleRepository;
	private final OfficeRepository officeRepository;
	private boolean system;

	public CrudEmployeeService(OfficeRepository officeRepository, EmployeeRepository employeeRepository,
			RoleRepository roleRepository) {
		this.employeeRepository = employeeRepository;
		this.officeRepository = officeRepository;
		this.roleRepository = roleRepository;
	}

	public void start(Scanner scan) throws ParseException {
		system = true;
		while (system) {
			System.out.println("------- Employee Editing -------");
			System.out.println("Select an Action");
			System.out.println("0 - Exit");
			System.out.println("1 - Create");
			System.out.println("2 - Upload");
			System.out.println("3 - Show");
			System.out.println("4 - Delete");

			int action = scan.nextInt();
			switch (action) {
			case 1: {
				save(scan);
				break;
			}
			case 2: {
				update(scan);
				break;
			}
			case 3: {
				show(scan);
				break;
			}
			case 4: {
				delete(scan);
				break;
			}
			default:
				system = false;
				break;
			}
		}
	}

	private Employee generateEmployee(Scanner scan) throws ParseException {
		Employee employee = new Employee();

		System.out.println("Employee Name:");
		employee.setName(scan.next());

		System.out.println("Employee Document:");
		employee.setDocument(scan.next());

		System.out.println("Employee Salary:");
		employee.setSalary(scan.nextBigDecimal());

		System.out.println("Employee HiringDate (dd/MM/yyyy):");
		employee.setHiringDate(new SimpleDateFormat("dd/MM/yyyy").parse(scan.next()));

		System.out.println("Employee Role Id:");
		employee.setRole(roleRepository.findById(scan.nextInt()).get());

		employee.setOffices(generateOffices(scan));
		return employee;
	}

	private List<Office> generateOffices(Scanner scan) {
		Boolean isTrue = true;
		List<Office> offices = new ArrayList<>();

		while (isTrue) {
			System.out.println("Write the Offices IDs - 0 to Exit");
			Integer officeId = scan.nextInt();
			if (officeId != 0) {
				Optional<Office> office = officeRepository.findById(officeId);
				offices.add(office.get());
			} else {
				isTrue = false;
			}
		}

		return offices;
	}

	private void save(Scanner scan) throws ParseException {
		System.out.println("\nCreate Employee");
		employeeRepository.save(generateEmployee(scan));
		System.out.println("Employee Created!");
	}

	private void update(Scanner scan) throws ParseException {
		System.out.println("\nUpdate Employee");
		System.out.println("Id:");
		int id = scan.nextInt();
		Employee employee = generateEmployee(scan);
		employee.setId(id);
		employeeRepository.save(employee);
		System.out.println("Employee Updated!");
	}

	private void delete(Scanner scan) {
		System.out.println("\nDelete Employee");
		System.out.println("Id:");
		employeeRepository.deleteById(scan.nextInt());
		System.out.println("Employee Deleted!");
	}

	private void show(Scanner scan) {
		
		//Initializing Pages
		Pageable pageable = PageRequest.of(0, 1, Sort.unsorted());
		Page<Employee> employees = employeeRepository.findAll(pageable);

		System.out.println("\nSelect a page (1-" + employees.getTotalPages() + ")");
		
		//This sorting, is sorting the page, not the entire query
		Sort sorting = Sort.by(Sort.Direction.ASC,"name");
		int maxPage = 5;
		int page = (scan.nextInt()-1);
		
		//Get the real Items
		pageable = PageRequest.of(page, maxPage, sorting);
		employees = employeeRepository.findAll(pageable);
		
		System.out.println("\nShowing Employees");
		employees.forEach(System.out::println);
		System.out.println("Page " + (employees.getNumber()+1) + " Found " + employees.getTotalElements() + " Employees");
	}
}
