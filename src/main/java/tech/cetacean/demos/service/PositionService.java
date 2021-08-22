package tech.cetacean.demos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.cetacean.demos.model.Employee;
import tech.cetacean.demos.model.Position;
import tech.cetacean.demos.repository.EmployeeRepository;
import tech.cetacean.demos.repository.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	PositionRepository positionRepository;

	public void create(Position position) {
		
		List<Employee> employeesToSave = new ArrayList<Employee>();
		
		for(Employee employee : position.getEmployees()) {
			
			Integer employeeId = employee.getId();
			
			
			/*
			 * this happens when employee is persisted
			 */
			if(employeeId!=null) {
				
				Optional<Employee> persistedEmployee =  employeeRepository.findById(employeeId);
				boolean employeeExistent = !persistedEmployee.isEmpty();
				
				if(employeeExistent) {
					
					Employee managedEmployee = persistedEmployee.get();
					Position managedPosition = managedEmployee.getPosition();
					
					managedEmployee.update(employee);
					employeesToSave.add(managedEmployee);
					managedPosition.getEmployees().remove(managedEmployee);
					position.getEmployees().remove(employee);
				}
			}else {
				
				positionRepository.save(employee.getPosition());
			}
		}
		
		
		for(Employee employee : employeesToSave) {
			positionRepository.save(employee.getPosition());
			employeeRepository.save(employee);
		}
		
		position.getEmployees().addAll(employeesToSave);
		
	}
			
			
			
			
			
		
	
	

}
