package it.plansoft.skills.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.skills.DTO.EmployeeDTO;
import it.plansoft.skills.DTO.HoursDTO;
import it.plansoft.skills.DTO.PositionDTO;
import it.plansoft.skills.DTO.PromotionDTO;
import it.plansoft.skills.DTO.SalaryDTO;
import it.plansoft.skills.Model.BaseModel;
import it.plansoft.skills.Service.BaseCrudService;
import it.plansoft.skills.Service.CompanyService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	CompanyService companyService;

	
	@GetMapping(value="/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getTestData() {		
		return "Test string";
    }
		
	@GetMapping(value="/employees", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@GetMapping(value="/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@PostMapping(path = "/hire", consumes = "application/json", produces = "application/json")
	public void hireEmployee(@RequestBody EmployeeDTO employee) throws ClassNotFoundException, SQLException {
		this.companyService.hireEmployee(employee);
	}
	
	@RequestMapping(value = "/setWorkHours/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setWorkHours(@PathVariable(value = "id") int id, @RequestBody HoursDTO hours) throws ClassNotFoundException, SQLException {
		this.companyService.applyWorkHourToEmployee(id, hours.getHours());
	}
	
	@RequestMapping(value = "/setExtraHours/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setExtraHours(@PathVariable(value = "id") int id, @RequestBody HoursDTO hours) throws ClassNotFoundException, SQLException {
		this.companyService.applyExtraHourToEmployee(id, hours.getHours());
	}
	
	@RequestMapping(value = "/setSalary/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setSalary(@PathVariable(value = "id") int id, @RequestBody SalaryDTO salary) throws ClassNotFoundException, SQLException {
		this.companyService.applySalaryToEmployee(id, salary.getSalary());
	}
	
	@DeleteMapping(path = "/dismiss/{id}")  // consumes = "application/json", produces = "application/json")
	public void hireEmployee(@PathVariable int id) throws ClassNotFoundException, SQLException {
		this.companyService.dismissEmployee(id);
	}
	
	
	@RequestMapping(path="/addPosition/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addPosition(@PathVariable("id") int id, @RequestBody PositionDTO position) throws ClassNotFoundException, SQLException {
		this.companyService.addEmployeePosition(id, position.getPosition());
	}
	
	@RequestMapping(path="/changePosition/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void changeEmployeePosition(@PathVariable("id") int id, @RequestBody PositionDTO position) throws ClassNotFoundException, SQLException {
		this.companyService.changeEmployeePosition(id, position.getPosition());
	}
	
	@GetMapping(value="/employeeTotalSalary/{id}")
    public String getTotalSalary(@PathVariable("id") Long id) throws ClassNotFoundException, SQLException {
		return "Total Salary: "+this.companyService.getTotalSalary(id);
    }
	
	@GetMapping(value="/promotions/{id}")
    public List<PromotionDTO> getPromotions(@PathVariable("id") Long id) throws ClassNotFoundException, SQLException {
		return this.companyService.getEmployeePromotions(id);
    }
	
	public static void main(String[] args) {

	}

}
