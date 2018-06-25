package introsde.rest.university.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="activity_Type")
public enum ActivityType implements Serializable {
	Sport("Sport"),
	Social("Social"),
	Culture("Culture"),
	WorkMeeting("WorkMeeting");
	
	private String name;
	
	ActivityType(String name) {
		name=this.name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Override
	 public String toString() {
	    return name;
	 }
	
	public static List<ActivityType> getAll() {
        return Arrays.asList(ActivityType.values());
	}
}
