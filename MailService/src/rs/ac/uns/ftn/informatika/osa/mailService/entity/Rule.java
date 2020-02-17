package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity                
@Table(name="rule")
public class Rule implements Serializable{
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="rule_id", unique=true, nullable=false)
	private int id;
	//int? enum?
	
	@Column(name="condition", unique=false, nullable=false)
	private Condition condition;
	
	@Column(name="operation", unique=false, nullable=false)
	private Operation operation;
	
	@OneToOne
	@JoinColumn(name="folder_id", nullable=true)
	private Folder folder;
	
	public Rule() {
		super();
	}
	public Rule(Condition condition, Operation operation,Folder fodler) {
		super();
		this.condition = condition;
		this.operation = operation;
		this.folder=fodler;
	}
	
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	
}
