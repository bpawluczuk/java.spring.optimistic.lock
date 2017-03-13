package pl.itse.optimistic.lock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "email")
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Version
    private Long version = 1L;
	
    public Long getVersion() {
    	
        return version;
    }

    public void setVersion(Long version) {
            this.version = version;
    }  

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public Email() {
		super();
	}

	public Email(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", name=" + name + "]";
	}
	
}
