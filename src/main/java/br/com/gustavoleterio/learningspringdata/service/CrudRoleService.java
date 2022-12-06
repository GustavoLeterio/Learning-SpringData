package br.com.gustavoleterio.learningspringdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gustavoleterio.learningspringdata.orm.Role;
import br.com.gustavoleterio.learningspringdata.repository.RoleRepository;

@Service
public class CrudRoleService {
	private final RoleRepository roleRepository;
	private boolean system;

	public CrudRoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public void start(Scanner scan) {
		system = true;
		while (system) {
			System.out.println("------- Role Editing -------");
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

	private void save(Scanner scan) {
		System.out.println("\nCreate Role");
		System.out.println("Role Description:");
		roleRepository.save(new Role(scan.next()));
		System.out.println("Role Created!");
	}

	private void update(Scanner scan) {
		System.out.println("\nUpdate Role");
		System.out.println("Id:");
		int id = scan.nextInt();
		System.out.println("Role Description:");
		Role role = new Role(scan.next());
		role.setId(id);
		roleRepository.save(role);
		System.out.println("Role Updated!");
	}

	private void delete(Scanner scan) {
		System.out.println("\nDelete Role");
		System.out.println("Id:");
		roleRepository.deleteById(scan.nextInt());
		System.out.println("Role Deleted!");
	}

	private void show(Scanner scan) {
		System.out.println("\nShowing Roles");
		roleRepository.findAll().forEach(System.out::println);
	}
}
