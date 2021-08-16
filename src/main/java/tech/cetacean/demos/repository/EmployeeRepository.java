package tech.cetacean.demos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.cetacean.demos.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByPositionId(Integer id);

	List<Employee> findByName(String name);
	

}
