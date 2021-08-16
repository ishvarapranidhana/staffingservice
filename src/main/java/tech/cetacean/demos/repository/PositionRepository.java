package tech.cetacean.demos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.cetacean.demos.model.Position;
	
public interface PositionRepository extends JpaRepository<Position, Integer> {
		
	Position findByName(String name);
			
}


