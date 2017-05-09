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

import org.ms.manager.MeasurementManager;
import org.ms.rest.model.MeasurementDTO;
import org.ms.rest.model.MeasurementTypeDTO;

/**
 * Created on 05.04.2017 21:55:04
 * 
 * @author Hubert Popio³kiewicz
 */
@Path("/measurement")
public class MeasurementService extends BaseService {

	@EJB
	MeasurementManager measurementManager;

	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAll(@HeaderParam(HttpHeaders.AUTHORIZATION) String authString,
			MeasurementTypeDTO measurementTypeDTO) {
		if (!authenticationManager.authenticate(authString))
			return Response.status(Status.FORBIDDEN).build();
		List<MeasurementDTO> all = measurementManager.getAll(measurementTypeDTO.getId());
		return Response.status(200).entity(all).build();
	}
}
