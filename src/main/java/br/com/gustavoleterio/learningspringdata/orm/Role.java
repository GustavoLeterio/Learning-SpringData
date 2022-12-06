package br.com.gustavoleterio.learningspringdata.orm;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	@OneToMany(mappedBy = "role")
	private List<Employee> employees;

	public Role() {
	}

	public Role(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role " + id + " {\n\tid: " + id + ",\n\tdescription:" + description + "\n}";
	}

}
