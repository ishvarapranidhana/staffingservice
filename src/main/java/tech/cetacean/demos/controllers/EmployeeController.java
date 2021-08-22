package tech.cetacean.demos.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.cetacean.demos.model.Employee;
import tech.cetacean.demos.repository.EmployeeRepository;
import tech.cetacean.demos.service.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/employee")
    public ResponseEntity<List<Employee>> read() {
        List<Employee> employees = repository.findAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> read(@PathVariable("id") Integer id) {
    	
		Optional<Employee> foundEmployee = repository.findById(id);
		
        if (foundEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundEmployee.get());
        }
    }
    
    @GetMapping("/employee/position/{id}")
    public ResponseEntity<List<Employee>> readByPositionId(@PathVariable("id") Integer id) {
    
    	 List<Employee> employees = repository.findByPositionId(id);
    	 return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    	
    }
    
    @GetMapping("/employee/name/{name}")
    public ResponseEntity<List<Employee>> readByName(@PathVariable("name") String name) {
    
    	 List<Employee> employees = repository.findByName(name);
    	 return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    	
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) throws URISyntaxException {
        
    	/*Employee createdEmployee =*/ service.create(employee);
    	repository.save(employee); 

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(employee.getId())
            .toUri();

        return ResponseEntity.created(uri)
            .body(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Integer id) {
        
    	Optional<Employee> toUpdateEmployeeObj  = repository.findById(id);
    	
        if (toUpdateEmployeeObj.isEmpty()) {
        	return ResponseEntity.notFound().build();
        } else {
        	Employee toUpdateEmployee = toUpdateEmployeeObj.get();
        	toUpdateEmployee.update(employee);
        	return ResponseEntity.ok(repository.save(toUpdateEmployee));
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {
    	
    	Boolean existsEmployee = repository.existsById(id);
    	if (!existsEmployee) {
            return ResponseEntity.notFound().build();
        } else {
    	
        	repository.deleteById(id);
        	return ResponseEntity.noContent().build();
        }
    }
}
