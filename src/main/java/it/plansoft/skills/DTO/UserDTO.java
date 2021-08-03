package it.plansoft.skills.DTO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.plansoft.skills.Model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class UserDTO extends BaseModel<Long> {

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
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_roles",
	    joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
	    inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	)
    private Set<RoleDTO> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CompetenceDTO> competences = new HashSet<>();
}