package it.plansoft.skills.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import it.plansoft.skills.Model.IdModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class RoleDTO extends IdModel<Integer>{
	
    @Getter @Setter
    @Column(name = "name")
    private String name;
}