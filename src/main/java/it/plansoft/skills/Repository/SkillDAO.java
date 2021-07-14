package it.plansoft.skills.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.skills.DTO.SkillDTO;

@Repository
public interface SkillDAO extends JpaRepository<SkillDTO, Integer> {
	
//    private final Logger logger = Logger.getLogger(this.getClass());
//    private Connection conn;
//    
//    private String connString = "jdbc:mysql://localhost:3306/skills?user=root&password=root";
//	
//	public ResponseEntity<?> saveSkillArea(SkillAreaDTO skillArea) throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//    	conn = DriverManager.getConnection(this.connString);
//    	
//    	String pattern = "yyyy-MM-dd HH:mm:ss";
//    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//    	String date = simpleDateFormat.format(new Date());	
//        
//		Statement st = conn.createStatement(); 
//		String query = "INSERT INTO skill_area "
//				+ "(area, dt_insert) "
//				+ "VALUES ("
//				+ "'"+skillArea.getArea()+"', " +
//						"'"+ date + "')";
//		st.execute(query); 
//		conn.close();
//		
//		Map<String,String> response = new HashMap<String, String>();
//		response.put("info", "Skill created");
//		logger.info("Skill area '"+skillArea.getArea()+"' created");
//        return ResponseEntity.ok(response);
//	}

}	