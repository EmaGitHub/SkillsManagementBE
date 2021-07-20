package it.plansoft.skills.DTO;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
public class RoleDTO {
	
    @Id
    @Column(name = "role_id")
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @Getter @Setter
    @Column(name = "name")
    private String name;
    }