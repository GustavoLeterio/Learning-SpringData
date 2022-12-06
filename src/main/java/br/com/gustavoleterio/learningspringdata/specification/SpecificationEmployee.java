package br.com.gustavoleterio.learningspringdata.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.gustavoleterio.learningspringdata.orm.Employee;

public class SpecificationEmployee {

	public static Specification<Employee> name(String name) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
	}

}
