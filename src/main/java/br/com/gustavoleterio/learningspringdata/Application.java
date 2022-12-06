package br.com.gustavoleterio.learningspringdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gustavoleterio.learningspringdata.service.CrudEmployeeService;
import br.com.gustavoleterio.learningspringdata.service.CrudOfficeService;
import br.com.gustavoleterio.learningspringdata.service.CrudRoleService;
import br.com.gustavoleterio.learningspringdata.service.ReportService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final CrudRoleService roleService;
	private final CrudEmployeeService employeeService;
	private final CrudOfficeService officeService;
	private final ReportService reportService;
	private boolean system = true;

	public Application(CrudRoleService roleService, CrudEmployeeService employeeService,
			CrudOfficeService officeService, ReportService reportService) {
		this.roleService = roleService;
		this.employeeService = employeeService;
		this.officeService = officeService;
		this.reportService = reportService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);

		while (system) {
			System.out.println("Select an Action");
			System.out.println("0 - Exit");
			System.out.println("1 - Roles");
			System.out.println("2 - Employees");
			System.out.println("3 - Offices");
			System.out.println("4 - Reports");
			System.out.println("\n");

			switch (scan.nextInt()) {
			case 1:
				roleService.start(scan);
				break;
			case 2:
				employeeService.start(scan);
				break;
			case 3:
				officeService.start(scan);
				break;
			case 4:
				reportService.start(scan);
				break;
			default:
				system = false;
				break;
			}
		}

	}
}
