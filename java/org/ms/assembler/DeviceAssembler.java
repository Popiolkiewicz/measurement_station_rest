/**
 * 
 */
package org.ms.assembler;

import javax.ejb.Stateless;

import org.ms.entity.Device;
import org.ms.rest.model.DeviceDTO;

/**
 * Created on 09.04.2017 23:57:47
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class DeviceAssembler extends AbstractAssembler<DeviceDTO, Device> {

	@Override
	public DeviceDTO toDTO(Device entity) {
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setId(entity.getId());
		deviceDTO.setName(entity.getName());
		deviceDTO.setSerialNumber(entity.getSerialNumber());
		return deviceDTO;
	}

	@Override
	public Device fromDTO(DeviceDTO dto) {
		Device device = new Device();
		if (dto.getId() != null)
			device.setId(dto.getId());
		device.setName(dto.getName());
		device.setSerialNumber(dto.getSerialNumber());
		return device;
	}

}
