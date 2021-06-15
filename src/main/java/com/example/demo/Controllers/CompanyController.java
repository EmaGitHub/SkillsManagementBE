package com.example.demo.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.Hours;
import com.example.demo.DTO.Position;
import com.example.demo.DTO.Salary;
import com.example.demo.Services.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
@RequestMapping("api")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping(value="/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getTestData() {		
		return "Test string";
    }
		
	@GetMapping(value="/getAllEmployeesInfo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getAllEmployeesInfo() throws ClassNotFoundException, SQLException, JsonProcessingException {
		
		ArrayList<EmployeeDTO> list = this.companyService.getAllEmployeesInfo();
		// Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Convert object to JSON string
        String empJson = mapper.writeValueAsString(list);
        System.out.println(empJson);
		return empJson;
    }
	
	@GetMapping(value="/getEmployeeById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEmployeeById(@PathVariable int id) throws ClassNotFoundException, SQLException, JsonProcessingException {
				
		EmployeeDTO e = this.companyService.getEmployeeById(id);
		
		// Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Convert object to JSON string
        String empJson = mapper.writeValueAsString(e);
        System.out.println(empJson);

		return empJson;
    }
	
	@PostMapping(path = "/hire")  // consumes = "application/json", produces = "application/json")
	public void hireEmployee(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("position") int position, @RequestParam("basesalary") float base_salary) throws ClassNotFoundException, SQLException {
		this.companyService.hireEmployee(id, name, lastName, position, base_salary);
	}
	
	@RequestMapping(value = "/setWorkHours/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setWorkHours(@PathVariable(value = "id") int id, @RequestBody Hours hours) throws ClassNotFoundException, SQLException {
		this.companyService.applyWorkHourToEmployee(id, hours.getHours());
	}
	
	@RequestMapping(value = "/setSalary/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setSalary(@PathVariable(value = "id") int id, @RequestBody Salary salary) throws ClassNotFoundException, SQLException {
		this.companyService.applySalaryToEmployee(id, salary.getSalary());
	}
	
	@DeleteMapping(path = "/dismiss/{id}")  // consumes = "application/json", produces = "application/json")
	public void hireEmployee(@PathVariable int id) throws ClassNotFoundException, SQLException {
		this.companyService.dismissEmployee(id);
	}
	
	@PostMapping(path="/promote")
	public void applyPromotionToEmployee(@RequestParam("employeeId") int id, @RequestParam("money") float money, @RequestParam("position") short position) throws ClassNotFoundException, SQLException {
		this.companyService.applyPromotionToEmployee(id, money, position);
		
	}
	
	@RequestMapping(path="/addPosition/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addPosition(@PathVariable("id") int id, @RequestBody Position position) throws ClassNotFoundException, SQLException {
		this.companyService.addEmployeePosition(id, position.getPosition());
	}
	
	@RequestMapping(path="/changePosition/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void changeEmployeePosition(@PathVariable("id") int id, @RequestBody Position position) throws ClassNotFoundException, SQLException {
		this.companyService.changeEmployeePosition(id, position.getPosition());
	}
	
	@GetMapping(value="/employeeTotalSalary/{id}")
    public String getTotalSalary(@PathVariable("id") int id) throws ClassNotFoundException, SQLException {
		return "Total Salary: "+this.companyService.getTotalSalary(id);
    }

	public static void main(String[] args) {

	}

}
