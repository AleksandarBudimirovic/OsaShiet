package rs.ac.uns.ftn.informatika.osa.mailService.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                
@Table(name="log")
public class EventLog {
	
    private static EventLog single_instance = null; 
  
    @Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="id", unique=true, nullable=false)
    public int id; 
    
    @Column(name="log", unique=false, nullable=false, columnDefinition = "longtext")
    public String log; 
	
    private EventLog() 
    { 
        log = "START \n"; 
    } 
   
    public static EventLog getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new EventLog(); 
  
        return single_instance; 
    }

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log += log;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
