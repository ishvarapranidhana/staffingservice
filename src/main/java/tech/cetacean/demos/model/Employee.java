package tech.cetacean.demos.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person implements Comparable<Employee>{
	private Integer salary;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;

	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @param name
	 * @param lastName
	 * @param address
	 * @param cellphone
	 * @param cityName
	 */
	public Employee(String name, String lastName, String address, String cellphone, String cityName, Position position, Integer salary) {
		super(name, lastName, address, cellphone, cityName);
		this.position = position;
		this.salary = salary;
	}
	
	public Employee() {
		
	}
	
	public void update(Employee updatedEmployee) {
		
		this.setPosition(updatedEmployee.getPosition());
		this.setSalary(updatedEmployee.getSalary());
		super.setAddress(getAddress());
		super.setCellphone(updatedEmployee.getCellphone());
		super.setCityName(updatedEmployee.getCityName());
		super.setName(updatedEmployee.getName());
		super.setLastName(updatedEmployee.getLastName());
		
	}

	@Override
	public int compareTo(Employee o) {
		return Integer.compare(o.getSalary(), getSalary());
	}

	

	

}
