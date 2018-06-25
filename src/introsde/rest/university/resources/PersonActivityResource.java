package introsde.rest.university.resources;

import introsde.rest.university.model.Activity;
import introsde.rest.university.model.ActivityType;
import introsde.rest.university.model.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

	public class PersonActivityResource {
	    @Context
	    UriInfo uriInfo;
	    @Context
	    Request request;
	    String type;
	    int id;

	    
	    public PersonActivityResource() {
	    }

	    public PersonActivityResource(UriInfo uriInfo, Request request,String type, int id) {
	        this.uriInfo = uriInfo;
	        this.request = request;
	        this.type = type;
	        this.id = id;
	    }
	    
	    @GET
	    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	    //person/{id}/{activity_type}?before={beforeDate}&after={afterDate}
	    public List<Activity> getActByPIdAType(@DefaultValue("")@QueryParam("before") String beforeDate, 
				@DefaultValue("")@QueryParam("after") String afterDate){
	    	
	    		int flag=0;
	    		List<Activity> activities= new LinkedList<Activity>();
	    		Person p=Person.getPersonById(id);
	    		if (p == null)
	    			throw new NotFoundException("Get: Person with " + id + " not found");
	    		List<Activity> allAct=p.getActivities();
	    		for(Activity activity:allAct) {
	    			if(type.equals(activity.getType().name())) {
	    				if(beforeDate.isEmpty() || afterDate.isEmpty()) {
	    					activities.add(activity);
	    				}
	    				else
	    					if(beforeDate.compareTo(activity.getStartdate())<0 && afterDate.compareTo(activity.getStartdate())>0) {
	    						activities.add(activity);
	    					}
	    				flag=1;
	    			}
	    		}
	    		if(flag==0) {
	    			throw new NotFoundException("Get: Activity with type : " + type + " not found");
	    		}
	    		return activities;
	    }
	    
	    
	    @GET
	    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	    @Path("{activity_id}")
	    //person/{id}/{activity_type}/{activity_id} 
	    public Activity getActivityByIDgivenPersonID(@PathParam("activity_id") int activity_id) {
	    		Person p=Person.getPersonById(id);
	    		
	    		if (p == null)
	    			throw new NotFoundException("Get: Person with " + id + " not found");
	    		
	    		for(Activity act : p.getActivities()) {
	    			if(act.getType().name().equals(type) && act.getIdActivity()==activity_id) {
	    				return act;
	    			}
	    		}
	    		throw new NotFoundException("Get: Activity with type: " + type + " with id: "+id+ "not found");
	    }
	    
	    @PUT
	    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	    @Path("{activity_id}")
	    //person/{id}/{activity_type}/{activity_id} 
	    public Response updateActivityByIDgivenPersonID(@PathParam("activity_id") int activity_id,Activity value) {
	    		
	    		Response res= Response.noContent().build();
	    		Person existingPerson = Person.getPersonById(this.id);
	    		for(Activity act:existingPerson.getActivities()) {
	    			if(act.getIdActivity()==activity_id && act.getType().name().equals(type)) {
	    				act.setType(value.getType());
	    				Activity.updateActivity(act);
	    				Person.updatePerson(existingPerson);
	    				return Response.created(uriInfo.getAbsolutePath()).build();
	    			}
	    		}
			return res;
	    }
	    
	    //person/{id}/{activity_type}
	    @POST
	    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	    public Person addNewValue(String value) {
	    		System.out.println("adding new ActivityType ......." +value);
	    		
	    		Person p=Person.getPersonById(this.id);
	    		if (p == null)
	    			throw new NotFoundException("Get: Person with " + id + " not found");
	    		
	    		for(Activity act: p.getActivities()) {
	    			if(act.getType().name().equals(this.type)) {
	    				if(act.getOldTypes()==null) 
	    					act.setOldTypes(new TreeSet<String>());
	    				act.getOldTypes().add(act.getType().name());
	    				act.setType(ActivityType.valueOf(value));
	    				Activity.updateActivity(act);
	    			}
	    		}
	    		Person.updatePerson(p);
	    		
	    		return p;
	    		
	    }
}
