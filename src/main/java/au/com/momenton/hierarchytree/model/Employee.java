package au.com.momenton.hierarchytree.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	long id;
	String name;
	long managerId;
	List<Employee> subordinates;
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getManagerId() {
		return managerId;
	}


	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}


	public List<Employee> getSubordinates() {
		return subordinates;
	}


	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}


	public Employee() {
		
	}
	
	
	public String toString() {
		return "id = " + id + ", name = " + name + ", managerId = " + managerId;
	}

}
