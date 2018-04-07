package com.restful.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restful.beans.Employee;

@Path("/")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML  })
//@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
@Singleton
public class EmployeesResource {
	  
  private Map<Integer,Employee> employeeMap;
	
	private HashMap<Integer,Employee> loadEmployeeMap(){
		
		employeeMap=new HashMap<>();
		employeeMap.put(1,new Employee(1,"praveen","plano"));
		employeeMap.put(2,new Employee(2,"richard","irving"));
		employeeMap.put(3,new Employee(3,"champak","richardson"));
		return (HashMap<Integer, Employee>) employeeMap;
	
	}
 
	public EmployeesResource() {
		  loadEmployeeMap();
		  System.out.println("Values loaded");
	  }
	
	  @GET
	  @Path("/emp")
	  public List<Employee> getEmployees_JSON() {
	    List<Employee> Employees = new ArrayList<Employee>();
	    Employees.addAll(employeeMap.values());
	    return Employees;
	  }
	  
	  @GET
	  @Path("/emp/{id}")
	  public Employee getEmployee(@PathParam("id") int id) {
	    return employeeMap.get(id);
	  }

	@GET
	@Path("/emp/name/{name}")
	public List<Employee> findByName(@PathParam("name") String name){
		List<Employee> list = new ArrayList<>();
		for(Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
			if(entry.getValue().getEmpName().equals(name)) {
				list.add(entry.getValue());
			}
		}
		if(list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}
	
	@POST
	public String addEmployee(Employee emp) {
			employeeMap.put(emp.getId(), emp);
			return "Employee added";
	}
	
	@PUT
	public String updateEmployee(Employee emp) {
			employeeMap.put(emp.getId(), emp);
			return "Employee updated";
	}
	
	@DELETE
	@Path("/{empNo}")
	public String removeEmployee(@PathParam("empNo") int id) {
		employeeMap.remove(id);
		return "Employee removed";
	}
  
}