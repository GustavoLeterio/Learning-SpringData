package br.com.gustavoleterio.learningspringdata.orm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String document;
	private BigDecimal salary;
	private Date hiringDate;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employees_offices", joinColumns = { @JoinColumn(name = "fk_employee") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_office") })
	private List<Office> offices;

	public Employee() {
	}

	public Employee(String name, String document, BigDecimal salary, Date hiringDate, Role role) {
		this.name = name;
		this.document = document;
		this.salary = salary;
		this.hiringDate = hiringDate;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Date getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate) {
		this.hiringDate = hiringDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	@Override
	public String toString() {
		StringBuilder stringOffices = new StringBuilder();

		this.offices.forEach(office -> stringOffices.append("\t\t" + office.getDescription() + ",\n"));

		return "Employee " + id + " {\n\tid: " + id + ",\n\tname: " + name + ",\n\tdocument: " + document
				+ ",\n\tsalary: " + salary + ",\n\thiringDate: " + hiringDate + ",\n\tRole: " + role.getDescription()
				+ ",\n\tOffices: {\n" + stringOffices + "\t}\n}";

	}

}
