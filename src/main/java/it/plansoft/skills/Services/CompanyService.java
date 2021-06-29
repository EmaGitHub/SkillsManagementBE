package it.plansoft.skills.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DAO.EmployeeDAO;
import it.plansoft.skills.DTO.EmployeeDTO;
import it.plansoft.skills.DTO.PromotionDTO;
import it.plansoft.skills.Model.BaseModel;

@Service
public class CompanyService extends BaseCrudService<JpaRepository<EmployeeDTO, Integer>, EmployeeDTO, Integer>{
	
	private ArrayList<EmployeeDTO> employees;
    private final Logger logger = Logger.getLogger(this.getClass());
	
	//@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	public CompanyService (EmployeeDAO employeeDAO) {
		this.employees = new ArrayList<EmployeeDTO>();
		this.employeeDao = employeeDAO;
	}
	
	public void getAllEmployees() throws SQLException, ClassNotFoundException {
		this.employees.clear();
		this.employees = this.employeeDao.getAllEmployees();
	}
	
	public ArrayList<EmployeeDTO> getAllEmployeesInfo() throws SQLException, ClassNotFoundException {
	    logger.info("Print all employees' info");
		return this.employeeDao.getAllEmployeesInfo();
	}
	
	public EmployeeDTO getEmployeeById(int id) throws ClassNotFoundException, SQLException {
	    logger.info("Print employees' info");
	    EmployeeDTO e = this.employeeDao.getEmployeeInfo(id);
        if (e == null)
        	logger.error("Employee with id "+id+" not found");
	    return e;
	}
	
	public void hireEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
		this.employeeDao.hireEmployee(employee.getName(), employee.getLastName(), employee.getPositions(), employee.getBaseSalary());
	}
	
	public void dismissEmployee(int id) throws SQLException, ClassNotFoundException {
		this.employeeDao.dismissEmployee(id);
	}
	
	public void applyPromotionToEmployee(int employeeId, float money, short position) throws SQLException, ClassNotFoundException {
		this.employeeDao.applyPromotionToEmployee(employeeId, money, position);
	}
	
	public void applySalaryToEmployee(int id, float baseSalary) throws SQLException, ClassNotFoundException {
		this.employeeDao.applySalaryToEmployee(id, baseSalary);
	}
	
	public void applyWorkHourToEmployee(int id, float maxWorkHours) throws SQLException, ClassNotFoundException {
		this.employeeDao.applyWorkHourToEmployee(id, maxWorkHours);
	}
	
	public void applyExtraHourToEmployee(int id, float maxWorkHours) throws SQLException, ClassNotFoundException {
		this.employeeDao.applyExtraHourToEmployee(id, maxWorkHours);
	}
	
	public ArrayList<Short> getEmployeePositions(int id) throws SQLException, ClassNotFoundException {
		return this.employeeDao.getEmployeePositions(id);
	}
	
	public float getFinalSalary(int employeeId) throws ClassNotFoundException, SQLException {
		return this.employeeDao.getFinalSalary(employeeId);
	}
	
	public void addEmployeePosition(int id, short position) throws SQLException, ClassNotFoundException {
		this.employeeDao.addEmployeePosition(id, position);
	}
	
	public void changeEmployeePosition(int id, short position) throws ClassNotFoundException, SQLException {
		this.employeeDao.changeEmployeePosition(id, position);
	}
	
	public float getTotalSalary(int id) throws ClassNotFoundException, SQLException {
		return this.employeeDao.getFinalSalary(id);
	}
	
	public List<PromotionDTO> getPromotions(int id) throws ClassNotFoundException, SQLException {
		return this.employeeDao.getEmployeePromotions(id);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
	

}
