package introsde.rest.ehealth.init;

import java.util.LinkedList;
import java.util.List;

import introsde.rest.ehealth.model.*;



public class DatabaseINIT {
public static void initializeDB(){
		
		Activity a=new Activity();
		a= setActivity("Hiking","Hiking in the mountain","Trentino","2017-02-11T01:02:03.4",ActivityType.Persistence);
		
		Person p=new Person();
		p=setPerson("1990-12-29","Kelrawa","Meng",a);
		
		a= setActivity("Jumping","Jumping to park","Park","2017-03-1T01:02:03.4",ActivityType.Persistence);
		p= setPerson( "1984-06-21", "Pallo", "Pinco",a);
		
		a= setActivity("Running", "Running to the park","Gocciadoro","2017-04-06T01:02:03.4",ActivityType.Powerful);
		p= setPerson("1951-1-28", "Coia","Qi",a);
		
		a= setActivity("Walking","Walking in the mountain","Jilin","2018-05-12T01:02:03.4",ActivityType.Relax);
		p= setPerson("2007-8-27", "Ehdos","Xiong",a);
		
		a= setActivity("Sleeping","Sleeping in the mountain","Bondone","2017-12-30T01:02:03.4",ActivityType.Relax);
		p= setPerson("2002-4-12","Ldjso","Udwak",a);
	}

	private static Person setPerson(String date, String string, String string2, Activity a){
		Person p=new Person();
		p.setBirthdate(date);
		p.setFirstname(string);
		p.setLastname(string2);
		List<Activity> act= p.getActivities(); 
		if(act == null)
			act= new LinkedList<Activity>();
		act.add(a);
		p.setActivities(act);
		p=Person.savePerson(p);
		Activity.updateActivity(a);
		return p;
	}

	private static Activity setActivity(String string, String string2, String string3, String date, ActivityType type) {
		Activity a= new Activity();
		a.setName(string);
		a.setDescription(string2);
		a.setPlace(string3);
		a.setStartdate(date);
		a.setType(type);
		return a;
	}

}
