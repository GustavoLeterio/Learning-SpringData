package br.com.gustavoleterio.learningspringdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gustavoleterio.learningspringdata.orm.Office;
import br.com.gustavoleterio.learningspringdata.repository.OfficeRepository;

@Service
public class CrudOfficeService {

	private Boolean system;
	private final OfficeRepository officeRepository;

	public CrudOfficeService(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}

	public void start(Scanner scan) {
		system = true;
		while (system) {
			System.out.println("------- Office Editing -------");
			System.out.println("Select an Action");
			System.out.println("0 - Exit");
			System.out.println("1 - Create");
			System.out.println("2 - Upload");
			System.out.println("3 - Show");
			System.out.println("4 - Delete");

			int action = scan.nextInt();

			switch (action) {
			case 1:
				save(scan);
				break;
			case 2:
				update(scan);
				break;
			case 3:
				show();
				break;
			case 4:
				delete(scan);
				break;
			default:
				system = false;
				break;
			}

		}

	}

	private void save(Scanner scanner) {
		System.out.println("\nCreate Office");

		Office office = new Office();

		System.out.println("Office Description:");
		office.setDescription(scanner.next());

		System.out.println("Office Adress:");
		office.setAdress(scanner.next());

		officeRepository.save(office);
		System.out.println("Office Created!");
	}

	private void update(Scanner scanner) {
		Office office = new Office();

		System.out.println("Office ID:");
		office.setId(scanner.nextInt());

		System.out.println("Office Description:");
		office.setDescription(scanner.next());

		System.out.println("Office Adress:");
		office.setAdress(scanner.next());

		officeRepository.save(office);
		System.out.println("Office Updated!");
	}

	private void show() {
		System.out.println("\nShowing Roles");
		Iterable<Office> unidades = officeRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}

	private void delete(Scanner scanner) {
		System.out.println("Id");
		officeRepository.deleteById(scanner.nextInt());
		System.out.println("Office Deleted!");
	}

}
