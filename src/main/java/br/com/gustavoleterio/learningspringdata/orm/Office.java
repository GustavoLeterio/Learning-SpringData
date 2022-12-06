package br.com.gustavoleterio.learningspringdata.orm;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "offices")
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private String adress;
	@ManyToMany(mappedBy = "offices", fetch = FetchType.EAGER)
	private List<Employee> employees;

	public Office() {
	}

	public Office(String description, String adress) {
		this.description = description;
		this.adress = adress;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String endereco) {
		this.adress = endereco;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Office " + id + "{\n\tid: " + id + ",\n\tdescription: " + description + ",\n\tadress: " + adress
				+ "\n}";
	}

}
