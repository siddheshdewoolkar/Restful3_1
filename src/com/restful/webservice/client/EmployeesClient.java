package com.restful.webservice.client;


import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restful.beans.Employee;

public class EmployeesClient {

	public static void main(String[] args) { 

		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/Restful3_1/emp").request().get(Response.class);
		List<Employee> lst = response.readEntity(new GenericType<List<Employee>>() {});
		System.out.println(lst);
	
		Employee emp = new Employee(4,"Siddhesh","Missori");
		response = client.target("http://localhost:8080/Restful3_1/").request(MediaType.APPLICATION_XML).post(Entity.xml(emp));
		String result = response.readEntity(String.class);
		System.out.println("add: "+result);
		
		response = client.target("http://localhost:8080/Restful3_1/emp/2").request(MediaType.APPLICATION_XML).get(Response.class);
		emp = response.readEntity(Employee.class);
		System.out.println(emp);
		
		response = client.target("http://localhost:8080/Restful3_1/emp/name/champak").request().get(Response.class);
		lst = response.readEntity(new GenericType<List<Employee>>() {});
		System.out.println(lst);
		
		response = client.target("http://localhost:8080/Restful3_1/4").request(MediaType.APPLICATION_XML).delete(Response.class);
		Employee result1 = response.readEntity(Employee.class);
		System.out.println("delete: "+result1);
		System.out.println("Employee Deleted");
	}

}