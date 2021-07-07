package it.plansoft.skills.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.EmployeeDTO;
import it.plansoft.skills.DTO.PromotionDTO;

@Repository
public class EmployeeDAO implements JpaRepository<EmployeeDTO, Integer>{
	
    private final Logger logger = Logger.getLogger(this.getClass());
    private Connection conn;
    private String connString = "jdbc:mysql://localhost:3306/skills?user=root&password=root";
    
    public EmployeeDAO() throws SQLException {}
	
	public void hireEmployee(String name, String lastName, ArrayList<Short> positions, float baseSalary) throws SQLException, ClassNotFoundException {
		
		EmployeeDTO newEmp = new EmployeeDTO(name, lastName);
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = conn.createStatement(); 
		String query = "INSERT INTO EMPLOYEE "
				+ "(NAME, LAST_NAME, POSITIONS, BASE_SALARY) "
				+ "VALUES ("
				+ "'"+name+"', "
				+ "'"+lastName+"', "
				+ "'"+this.convertPositionListToString(positions)+"', "
				+ ""+baseSalary+")";
		st.execute(query); 
		conn.close();
	}
	
	public void dismissEmployee(int id) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		st.execute("DELETE FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public ArrayList<EmployeeDTO> getAllEmployees()  throws SQLException, ClassNotFoundException {
		
		ArrayList<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE"); 
		
		while (rs.next()) {
			EmployeeDTO emp = new EmployeeDTO(rs.getString("name"), rs.getString("last_name"));
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			
			String positions = rs.getString("position");
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			
			employees.add(emp);
		}
		conn.close();
		
		return employees;
	}
	
	public ArrayList<EmployeeDTO> getAllEmployeesInfo()  throws SQLException, ClassNotFoundException {	
		
		ArrayList<EmployeeDTO> employeesList = new ArrayList<EmployeeDTO>();
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE"); 
		
		while (rs.next()) {
			EmployeeDTO emp = new EmployeeDTO(rs.getString("name"), rs.getString("last_name"));
			emp.setId(rs.getLong("id"));
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			String positions = rs.getString("positions");
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			emp.setFinalSalary(this.getFinalSalary((Long)emp.getId()));
			
			employeesList.add(emp);
		}
		conn.close();
		return employeesList;
	}
	
	public EmployeeDTO getEmployeeInfo(int id) throws SQLException, ClassNotFoundException {
		EmployeeDTO emp = null;
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			emp = new EmployeeDTO(rs.getString("name") != null ? rs.getString("name") : "", rs.getString("last_name") != null ? rs.getString("last_name") : "");
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			String positions = rs.getString("positions") != null ? rs.getString("positions") : "";
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			emp.setFinalSalary(this.getFinalSalary((Long)emp.getId()));
		}
		conn.close();
		return emp;
	}
	
	public void applySalaryToEmployee(int id, float baseSalary) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET base_salary = "+baseSalary+" "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public void applyWorkHourToEmployee(int id, float maxWorkHours) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = conn.createStatement(); 
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET work_hours = "+maxWorkHours+" "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public void applyExtraHourToEmployee(int id, float extraHours) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = conn.createStatement(); 
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET extra_hours = "+extraHours+" "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public ArrayList<Short> getEmployeePositions(int id) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT POSITION FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		conn.close();
		
		String positionAsString = "";
		
		if (rs.next())
			positionAsString = rs.getString(1);
		
		return convertStringPositionToList(positionAsString);
	}
	
	public void addEmployeePosition(int id, short position) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT POSITIONS FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		
		String positionAsString = "";
		
		if (rs.next()) {
			positionAsString = rs.getString(1);
			if (!positionAsString.equals(""))
				positionAsString += ",";
			positionAsString += position;

			int rs2 = st.executeUpdate("UPDATE EMPLOYEE "
					+ "SET POSITIONS = '" + positionAsString + "'"
					+ "WHERE ID = "+id); 
			conn.close();
		}
		else 
			logger.error("Positions not found");
	}
	
	public float getFinalSalary(Long id) throws SQLException, ClassNotFoundException {

		long promotionEarnings = 0;
		
		float baseSalary = 0;
		float extraHours = 0;
		float workHours = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
    	
		Statement st = this.conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery("SELECT BASE_SALARY FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			baseSalary = rs.getFloat(1);
		}
		
		rs = st.executeQuery("SELECT EXTRA_HOURS FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			extraHours += rs.getFloat(1);
		}
		
		rs = st.executeQuery("SELECT WORK_HOURS FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			workHours += rs.getFloat(1);
		}
		
		rs = st.executeQuery("SELECT * FROM PROMOTION "
				+ "WHERE EMPLOYEE_ID = "+id); 
		while (rs.next()) {
			promotionEarnings += rs.getFloat("money");	// promotion number * position
		}
		float y = (float) ((baseSalary / 30 * workHours) * 1.7);
			
		conn.close();
		return (baseSalary + promotionEarnings + extraHours * y);
	}
	
	public void changeEmployeePosition(int id, short position) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
    	ResultSet rs;
    	
    	int promotionNumber = 0;
    	float baseSalary = 0;
    	
		Statement st = this.conn.createStatement();
		st.executeUpdate("UPDATE EMPLOYEE SET "
				+ "PRINCIPAL_POSITION = "+position+" "
				+ "WHERE ID = "+id);
		
		rs = st.executeQuery("SELECT count(*) FROM PROMOTION "
				+ "WHERE EMPLOYEE_ID = "+id); 
		while (rs.next()) {
			promotionNumber += rs.getInt(1);
		}
		
		rs = st.executeQuery("SELECT BASE_SALARY FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			baseSalary += rs.getFloat(1);
		}
		
		float newSalary;
		newSalary = baseSalary + promotionNumber * position;
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET BASE_SALARY = "+newSalary+" "
				+ "where ID = "+id);
	}
	
	public ArrayList<Short> convertStringPositionToList(String positionAsString) {
		ArrayList<Short> list = new ArrayList<Short>();
		if (positionAsString != null && !positionAsString.equals("")) {
			String[] arr = positionAsString.split(",");
			if (arr != null )
				for (String s: arr)
					if (!s.equals(""))
						list.add(Short.valueOf(s));
			else
				list.add(Short.valueOf(positionAsString));
		}
		else
			list.add((short) 0);
		return list;
	}
	
	public String convertPositionListToString(ArrayList<Short> positionList) {
		String positions = "";
		for (Short pos: positionList) 
			positions.concat(pos.toString()).concat(",");
		return positions.substring(0, positions.length() > 0 ? positions.length()-1 : 0);
	}

	@Override
	public Page<EmployeeDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EmployeeDTO> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(EmployeeDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends EmployeeDTO> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends EmployeeDTO> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends EmployeeDTO> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmployeeDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDTO> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends EmployeeDTO> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<EmployeeDTO> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeeDTO getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDTO getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EmployeeDTO> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
