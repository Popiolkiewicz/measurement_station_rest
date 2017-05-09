/**
 * 
 */
package org.ms.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.ms.assembler.DeviceAssembler;
import org.ms.dao.DeviceDAO;
import org.ms.dao.UserDAO;
import org.ms.entity.Device;
import org.ms.rest.model.DeviceDTO;

/**
 * Created on 06.04.2017 23:55:09
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class DeviceManager {

	@EJB
	UserDAO userDAO;
	@EJB
	DeviceDAO deviceDAO;
	@EJB
	MeasurementTypeManager measurementTypeManager;
	@EJB
	DeviceAssembler deviceAssembler;

	public boolean add(DeviceDTO deviceDTO) {
		Device device = deviceAssembler.fromDTO(deviceDTO);
		device.setUser(userDAO.get(deviceDTO.getUserDTO().getId()));
		try {
			deviceDAO.save(device);
			deviceDTO.setId(device.getId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<DeviceDTO> getAll(Long userId) {
		List<Device> all = deviceDAO.getAll(userId);
		return deviceAssembler.toDTO(all);
	}

	public List<DeviceDTO> getForPreview(Long userId) {
		List<Device> devicesForPreview = deviceDAO.getAll(userId);
		List<DeviceDTO> deviceDTOs = new ArrayList<>();
		devicesForPreview.forEach(device -> {
			DeviceDTO deviceDTO = deviceAssembler.toDTO(device);
			deviceDTO.setMeasurementTypeDTOs(measurementTypeManager.getForPreview(device.getMeasurementTypes()));
			checkIfDeviceWarning(deviceDTO);
			deviceDTOs.add(deviceDTO);
		});
		return deviceDTOs;
	}

	private void checkIfDeviceWarning(DeviceDTO deviceDTO) {
		deviceDTO.getMeasurementTypeDTOs().forEach(x -> {
			if (x.getMeasurementDTO() != null && x.getMeasurementDTO().getWarning()) {
				deviceDTO.setWarning(true);
				return;
			}
		});

	}

	public void addMeasurements(DeviceDTO deviceDTO) {
		String serialNumber = deviceDTO.getSerialNumber();
		Device device = deviceDAO.getBySerialNumber(serialNumber);
		if (device == null)
			return;
		measurementTypeManager.addMeasurements(deviceDTO.getMeasurementTypeDTOs(), serialNumber);
	}
}
