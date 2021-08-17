package tech.cetacean.demos.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tech.cetacean.demos.model.Position;
import tech.cetacean.demos.repository.PositionRepository;

@RestController
public class PositionController {
	@Autowired
	private PositionRepository positionRepository;
	@GetMapping("/position/name/{name}")
	public ResponseEntity<Position> getPositionByName(@PathVariable String name) {
		Position position = positionRepository.findByName(name);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}
	@GetMapping("/position/id/{id}")
	public ResponseEntity<Position> getPositionById(@PathVariable String id) {
		
		List<Integer> idsToLookup = new ArrayList<Integer>();
		idsToLookup.add(Integer.valueOf(id));
		
		Position position = positionRepository.findAllById(idsToLookup).get(0);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}
	
	@GetMapping("/position")
	public ResponseEntity<List<Position>> getPositions() {
		List<Position> positions = positionRepository.findAll();
		//ordering by salary
		for(Position position: positions) {
			Collections.sort(position.getEmployees());
		}
		
		return new ResponseEntity<List<Position>>(positions, HttpStatus.OK);
	}
	
	
}
