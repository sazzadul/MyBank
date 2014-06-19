package no.mybank.ws;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@WebService
public interface AccountWS {
	
	@GET
	@Produces (MediaType.TEXT_PLAIN)
	@Path("/createAccounts/{numberofaccounts}")
	public abstract String createAccounts(@PathParam(value="numberofaccounts") int numberofaccounts);
	
    @GET
    @Produces(value={"text/plain"})
    @Path(value="/ping")
    public abstract String ping();

}
