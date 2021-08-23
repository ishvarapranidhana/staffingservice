package tech.cetacean.demos.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
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

import tech.cetacean.demos.exceptions.ConflictingNameException;
import tech.cetacean.demos.model.Position;
import tech.cetacean.demos.repository.PositionRepository;
import tech.cetacean.demos.service.PositionService;

@RestController
public class PositionController {
	@Autowired
	private PositionRepository repository;
	@GetMapping("/position/name/{name}")
	public ResponseEntity<Position> getPositionByName(@PathVariable String name) {
		Position position = repository.findByName(name);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}
	@Autowired
	PositionService service;
	
	@GetMapping("/position/id/{id}")
	public ResponseEntity<Position> getPositionById(@PathVariable String id) {
		
		List<Integer> idsToLookup = new ArrayList<Integer>();
		idsToLookup.add(Integer.valueOf(id));
		
		Position position = repository.findAllById(idsToLookup).get(0);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}
	
	@GetMapping("/position")
	public ResponseEntity<List<Position>> getPositions() {
		List<Position> positions = repository.findAll();
		
		//ordering by salary
		//Employee implements Comparable<Employee> 
		for(Position position: positions) {
			Collections.sort(position.getEmployees());
		}
		
		return new ResponseEntity<List<Position>>(positions, HttpStatus.OK);
	}
	
	@PostMapping("/position")
	public ResponseEntity<Position> create(@RequestBody Position position) throws URISyntaxException {
		
		service.create(position);
		repository.save(position);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(position.getId())
            .toUri();

        return ResponseEntity.created(uri)
            .body(position);
	}
	
	@PutMapping("/position/{id}")
	public ResponseEntity<Position> update(@RequestBody Position position, @PathVariable Integer id) {
        
    	Optional<Position> foundPositionObj =  repository.findById(id);
    	
    	if (foundPositionObj.isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}else{
    		
    		Position toUpdatePosition = foundPositionObj.get();
    		
    		if(position.getEmployees().isEmpty()) {
    			toUpdatePosition.updateMaintainingEmployees(position);
    		}else {
    			
    			toUpdatePosition.update(position);
    		}
    		repository.save(toUpdatePosition);
    		
        	return ResponseEntity.ok(toUpdatePosition);
    	}
    }
	
	@DeleteMapping("/position/{id}")
    public ResponseEntity<Object> deletePosition(@PathVariable Integer id) {
    	
    	Boolean existsPosition = repository.existsById(id);
    	if (!existsPosition) {
            return ResponseEntity.notFound().build();
        } else {
        	repository.deleteById(id);
        	return ResponseEntity.noContent().build();
        }
    }
	
	
}
