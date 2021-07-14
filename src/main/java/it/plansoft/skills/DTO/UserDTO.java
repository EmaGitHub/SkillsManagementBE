package it.plansoft.skills.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import it.plansoft.skills.Model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class UserDTO extends BaseModel<Long>{

	@Getter @Setter
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Getter @Setter
	@Column(name = "password", nullable = false)
	private String password;
	@Getter @Setter
	@Column(name = "first_name", nullable = true)
	private String firstName;
	@Getter @Setter
	@Column(name = "last_name", nullable = true)
	private String lastName;
	@Getter @Setter
	@Column(name = "dt_insert", nullable = true)
	private java.util.Date  dtInsert;
	@Getter @Setter
	@Column(name = "is_system_admin", nullable = true, columnDefinition="BOOLEAN DEFAULT false")
	private Boolean isSystemAdmin;
}