package it.plansoft.skills.Model.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import it.plansoft.skills.DTO.RoleDTO;
import it.plansoft.skills.Model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel<Long> implements Serializable {

	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate  dtInsert;
    private Set<RoleDTO> roles = new HashSet<>();
	
}