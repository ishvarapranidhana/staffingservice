package tech.cetacean.demos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.cetacean.demos.model.Employee;
import tech.cetacean.demos.model.Position;
import tech.cetacean.demos.repository.PositionRepository;

@Service
public class EmployeeService {
	
	@Autowired
	PositionRepository positionRepository;

	public void create(Employee employee) {
		
		Position transientPosition = employee.getPosition();
		Optional<Position> persistedPosition = positionRepository.findById(transientPosition.getId());
		boolean positionExistent = !persistedPosition.isEmpty();
		
		if(positionExistent) {
			Position managedPosition = persistedPosition.get();
			List<Employee> employees = managedPosition.getEmployees();
			if(!employees.contains(employee)){
				employee.setPosition(managedPosition);
				employees.add(employee);
			}
		}
		
	}

}
