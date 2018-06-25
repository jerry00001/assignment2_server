package introsde.rest.ehealth.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import introsde.rest.ehealth.init.DatabaseINIT;


@Path("/database_init")

public class databaseinitResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	//Request#0
	
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void getPersonsList() {
		DatabaseINIT.initializeDB();
	}
}
