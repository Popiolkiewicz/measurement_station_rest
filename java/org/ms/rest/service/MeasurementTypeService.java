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

import org.ms.manager.MeasurementTypeManager;
import org.ms.rest.model.DeviceDTO;
import org.ms.rest.model.MeasurementTypeDTO;

/**
 * Created on 05.04.2017 21:55:04
 * 
 * @author Hubert Popio³kiewicz
 */
@Path("/measurementType")
public class MeasurementTypeService extends BaseService {

	@EJB
	MeasurementTypeManager measurementTypeManager;

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString,
			MeasurementTypeDTO measurementTypeDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		measurementTypeManager.add(measurementTypeDTO);
		return Response.status(200).build();
	}

	@POST
	@Path("/getForDevice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getForDevice(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString, DeviceDTO deviceDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		List<MeasurementTypeDTO> deviceDTOs = measurementTypeManager.getAll(deviceDTO.getId());
		return Response.status(200).entity(deviceDTOs).build();
	}

}
