package chekalkin.labs;

import java.io.Serializable;
import java.util.Date;
import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@SuppressWarnings("serial")
public class User implements Serializable {
	private String firstName;
    private String lastName;
    private Long id;
    private Date birthday;
    
    public User(Long id, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
    public User(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
    public User() {
		// TODO Auto-generated constructor stub
	}

	//Gettes and Setters for Id, FirstName, LastName and Birthday
    public Long getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date string) {
        this.birthday = string;
    }
    
    //with help StringBuilder we can working with Strings without any problems like when we have a lot of inforamations 
    public String getFullName() {
        StringBuilder builder = new StringBuilder();
        builder.append(lastName)
            .append(", ")
            .append(firstName);
        return builder.toString();
    }
    
    //LocalDate 
    public int getAge() {
    	ZoneId zoneId = ZoneId.systemDefault();
    	Instant inst = birthday.toInstant();
        LocalDate currentLocalDate = LocalDate.now(zoneId);
        LocalDate birthLocalDate = inst.atZone(zoneId).toLocalDate();
        return (int) ChronoUnit.YEARS.between(birthLocalDate, currentLocalDate);
    }
    
	@Override
	public int hashCode() {
		if(this.getId() == null) {
			return 0;
		}
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this == obj) {
			return true;
		}
		if(this.getId() == null && ((User) obj).getId() == null) {
			return true;
		}
		
		return this.getId().equals(((User) obj).getId());
	}

}