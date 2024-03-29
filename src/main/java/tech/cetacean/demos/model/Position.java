/**
 * 
 */
package tech.cetacean.demos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Position {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@JsonManagedReference
	@OneToMany(
			mappedBy = "position",
			cascade = {CascadeType.MERGE, CascadeType.REFRESH},
			orphanRemoval = true
	)
	private List<Employee> employees = new ArrayList<>();
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param id
	 * @param name
	 */
	public Position(String name) {
		this.name = name;
	}
	
	public Position() {
		
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public void update(Position position) {
		this.name = position.getName();
		this.employees.clear();
		this.employees.addAll(position.getEmployees());
	}
	/**
	 * @param id
	 * @param name
	 * @param employees
	 */
	public Position(Integer id, String name, List<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.employees = employees;
	}
	
	public boolean Equals(Object o) {
		if (this == o)
		      return true;
		    if (!(o instanceof Position position))
		      return false;
		return Objects.equals(this.id , position.id) && Objects.equals(this.name, position.name);
	}
	
	@Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.name);
	 }
	public void updateMaintainingEmployees(Position position) {
		this.name = position.getName();
	}
	

}
