package it.plansoft.skills.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import it.plansoft.skills.Model.BaseModel;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseModel<Long>{

	@Id
	@Getter @Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	@Getter @Setter
	@Column(name = "username", nullable = false)
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
	@Column(name = "is_system_admin", nullable = true)
	private Boolean isSystemAdmin;
}