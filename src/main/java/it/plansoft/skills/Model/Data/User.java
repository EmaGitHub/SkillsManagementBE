package it.plansoft.skills.Model.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import it.plansoft.skills.DTO.RoleDTO;
import it.plansoft.skills.Model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User extends BaseModel<Long> implements Serializable {

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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<RoleDTO> roles = new HashSet<>();
	
}