package br.com.gustavoleterio.learningspringdata.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.gustavoleterio.learningspringdata.orm.Employee;
import br.com.gustavoleterio.learningspringdata.orm.EmployeeProjection;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

	// Derived Query
	List<Employee> findByName(String name);

	// JPQL (Java Persistence Query Language)
	// They are the same {
	// @Query indicates use of JPQL
	@Query("SELECT E FROM Employee E WHERE E.name = :name AND E.salary >= :salary AND E.hiringDate = :hiringDate")
	List<Employee> findByNameHiringDateSalaryGreaterThan(String name, Date hiringDate, Double salary);

	// This nativeQuery value indicates to Spring Data that this value represents a
	// SQL Query, so I call my value as table represents, not with the Class Names
	@Query(value = "SELECT * FROM Employee AS E WHERE E.name = :name AND E.salary >= :salary AND E.hiring_date = :hiringDate", nativeQuery = true)
	List<Employee> findByNameHiringDateSalaryGreaterThanUsingNativeQuery(String name, Date hiringDate, Double salary);

	// For more info:
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	List<Employee> findByNameAndHiringDateAndSalaryGreaterThan(String name, Date hiringDate, Double salary);
	// }

	List<Employee> findByHiringDateGreaterThan(Date date);

	@Query(value = "SELECT id, name, salary FROM Employees", nativeQuery = true)
	List<EmployeeProjection> findEmployeeSalary();

	void deleteById(int nextInt);

	void save(Employee employee);

}
