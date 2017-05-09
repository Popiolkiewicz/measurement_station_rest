package org.ms.rest.service;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ms.manager.UserManager;
import org.ms.rest.model.UserDTO;

/**
 * Created on 05.04.2017 21:52:08
 * 
 * @author Hubert Popio³kiewicz
 */
@Path("/user")
public class UserService extends BaseService {

	@EJB
	UserManager userManager;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString, UserDTO userDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		userManager.login(userDTO);
		if (userDTO.getId() == null)
			return Response.status(Status.FORBIDDEN).build();
		return Response.status(Status.OK).entity(userDTO).build();
	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString, UserDTO userDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		boolean registered = userManager.register(userDTO);
		if (!registered)
			return Response.status(Status.NOT_MODIFIED).build();
		return Response.status(Status.OK).entity(userDTO).build();
	}

}
