package org.ms.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.ms.assembler.MeasurementTypeAssembler;
import org.ms.dao.DeviceDAO;
import org.ms.dao.MeasurementTypeDAO;
import org.ms.entity.MeasurementType;
import org.ms.rest.model.MeasurementTypeDTO;

/**
 * Created on 06.04.2017 23:54:59
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class MeasurementTypeManager {

	@EJB
	DeviceDAO deviceDAO;
	@EJB
	MeasurementTypeDAO measurementTypeDAO;
	@EJB
	MeasurementManager measurementManager;
	@EJB
	MeasurementTypeAssembler measurementTypeAssembler;

	public List<MeasurementTypeDTO> getAll(Long deviceId) {
		List<MeasurementType> measurements = measurementTypeDAO.getAll(deviceId);
		return measurementTypeAssembler.toDTO(measurements);
	}

	public void add(MeasurementTypeDTO dto) {
		MeasurementType entity = measurementTypeAssembler.fromDTO(dto);
		if (dto.getDeviceDTO() != null) {
			if (dto.getDeviceDTO().getId() != null)
				entity.setDevice(deviceDAO.get(dto.getDeviceDTO().getId()));
			else if (dto.getDeviceDTO().getSerialNumber() != null)
				entity.setDevice(deviceDAO.getBySerialNumber(dto.getDeviceDTO().getSerialNumber()));
		}
		MeasurementType savedEntity = measurementTypeDAO.save(entity);
		dto.setId(savedEntity.getId());
	}

	public List<MeasurementTypeDTO> getForPreview(List<MeasurementType> types) {
		List<MeasurementTypeDTO> result = new ArrayList<>();
		types.forEach(type -> {
			MeasurementTypeDTO typeDTO = measurementTypeAssembler.toDTO(type);
			Double bottomCB = typeDTO.getBottomCriticalBound();
			Double topCB = typeDTO.getTopCriticalBound();
			typeDTO.setMeasurementDTO(measurementManager.getForPreview(type.getMeasurements(), bottomCB, topCB));
			result.add(typeDTO);
		});
		return result;
	}

	public void addMeasurements(List<MeasurementTypeDTO> typeDTOs, String deviceSerialNumber) {
		typeDTOs.forEach(typeDTO -> {
			MeasurementType mt = measurementTypeDAO.getByCode(typeDTO.getCode());
			measurementManager.addMeasurement(typeDTO.getMeasurementDTO(), mt);
		});
	}

}
