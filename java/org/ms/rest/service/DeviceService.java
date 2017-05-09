package org.ms.rest.service;

import java.util.List;

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

import org.ms.manager.DeviceManager;
import org.ms.rest.model.DeviceDTO;
import org.ms.rest.model.UserDTO;

/**
 * Created on 05.04.2017 21:48:44
 * 
 * @author Hubert Popio³kiewicz
 */
@Path("/device")
public class DeviceService extends BaseService {

	@EJB
	DeviceManager deviceManager;

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString, DeviceDTO deviceDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		deviceDTO.setUserDTO(new UserDTO(authenticationManager.getUserId()));
		boolean registered = deviceManager.add(deviceDTO);
		if (!registered)
			return Response.status(Status.NOT_MODIFIED).build();
		return Response.status(200).entity(deviceDTO).build();
	}

	@POST
	@Path("/addMeasurements")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMeasurements(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString, DeviceDTO deviceDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		deviceManager.addMeasurements(deviceDTO);
		return Response.status(200).build();
	}

	@POST
	@Path("/getForUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getForUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		List<DeviceDTO> deviceDTOs = deviceManager.getAll(authenticationManager.getUserId());
		return Response.status(200).entity(deviceDTOs).build();
	}

	@POST
	@Path("/getForPreview")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getForPreview(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		List<DeviceDTO> newest = deviceManager.getForPreview(authenticationManager.getUserId());
		return Response.status(200).entity(newest).build();
	}
}