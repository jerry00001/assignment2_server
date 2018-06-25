package introsde.rest.university.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import introsde.rest.university.model.ActivityType;

@Path("/activity_types")
public class ActivityCollectionResource {

	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_XML ,MediaType.APPLICATION_JSON})
    public ActivityType[] getActivityTypeXML(){
		ActivityType[] types = ActivityType.values();
		return types;
    }
	
}
