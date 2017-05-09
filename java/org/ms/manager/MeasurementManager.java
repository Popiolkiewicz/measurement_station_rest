package org.ms.manager;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.ms.assembler.MeasurementAssembler;
import org.ms.dao.MeasurementDAO;
import org.ms.entity.Measurement;
import org.ms.entity.MeasurementType;
import org.ms.rest.model.MeasurementDTO;

/**
 * Created on 06.04.2017 23:54:59
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class MeasurementManager {

	@EJB
	MeasurementDAO measurementDAO;

	@EJB
	MeasurementAssembler measurementAssembler;

	public List<MeasurementDTO> getAll(Long measurementTypeId) {
		List<Measurement> measurements = measurementDAO.getAll(measurementTypeId);
		List<MeasurementDTO> dtos = measurementAssembler.toDTO(measurements);
		return dtos;
	}

	public MeasurementDTO getForPreview(List<Measurement> measurements, Double bottomCB, Double topCB) {
		MeasurementDTO dto = null;
		Optional<Measurement> optionalMeasurement = measurements.stream()
				.sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())).findFirst();
		if (optionalMeasurement.isPresent()) {
			dto = measurementAssembler.toDTO(optionalMeasurement.get());
			boolean warning = (bottomCB != null && dto.getValue() < bottomCB)
					|| (topCB != null && dto.getValue() > topCB);
			dto.setWarning(warning);
		}
		return dto;
	}

	public void addMeasurement(MeasurementDTO measurementDTO, MeasurementType mt) {
		Measurement entity = measurementAssembler.fromDTO(measurementDTO);
		entity.setMeasurementType(mt);
		measurementDAO.save(entity);
	}

}
