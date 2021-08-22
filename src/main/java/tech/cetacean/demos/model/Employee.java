package tech.cetacean.demos.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person implements Comparable<Employee>{
	private Integer salary;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "position_id", nullable = false)
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
		super();
	}
	
	public void update(Employee updatedEmployee) {
		
		if(updatedEmployee.getPosition()!=null) {
			this.setPosition(updatedEmployee.position);
		}
		this.setSalary(updatedEmployee.salary);
		super.setAddress(updatedEmployee.address);
		super.setCellphone(updatedEmployee.cellphone);
		super.setCityName(updatedEmployee.cityName);
		super.setName(updatedEmployee.name);
		super.setLastName(updatedEmployee.lastName);
		
	}

	@Override
	public int compareTo(Employee o) {
		return Integer.compare(o.getSalary(), getSalary());
	}
	
	@Override
	public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Employee))
	      return false;
	    Employee employee = (Employee) o;
	    return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
	        && Objects.equals(this.salary, employee.salary) && Objects.equals(this.lastName, employee.lastName)
	        && Objects.equals(this.cityName, employee.cityName) && Objects.equals(this.cellphone, employee.cellphone)
	        && Objects.equals(this.position, employee.position) && Objects.equals(this.address, employee.address);
	  }

}
