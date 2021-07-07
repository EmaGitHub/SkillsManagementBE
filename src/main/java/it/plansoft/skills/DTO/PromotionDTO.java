package it.plansoft.skills.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Promotion")
public class PromotionDTO {

	@Id
	private int id;
	@ManyToOne(targetEntity=EmployeeDTO.class, optional=false)
	@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="ID")
	private int employeeId;	
	@Column
	private short position;
	@Column
	private float money;
	
	public PromotionDTO(int employeeId, short position, float money) {
		this.employeeId = employeeId;
		this.position = position;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public short getPosition() {
		return position;
	}
	public void setPosition(short position) {
		this.position = position;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
	
}
