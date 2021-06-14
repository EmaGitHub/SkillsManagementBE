package com.example.demo.Services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.EmployeeDAO;
import com.example.demo.DTO.Employee;

@Service
public class CompanyService {
	
	private ArrayList<Employee> employees;
    private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	public CompanyService () {
		this.employees = new ArrayList<Employee>();
	}
	
	public void getAllEmployees() throws SQLException, ClassNotFoundException {
		this.employees.clear();
		this.employees = this.employeeDao.getAllEmployees();
	}
	
	public String getAllEmployeesInfo() throws SQLException, ClassNotFoundException {
	    logger.info("Print employees' info");
		return this.employeeDao.getAllEmployeesInfo();
	}
	
	public void hireEmployee(int id, String name, String lastName, int position, float baseSalary) throws SQLException, ClassNotFoundException {
		this.employeeDao.hireEmployee(id, name, lastName, position, baseSalary);
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
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
	

}
