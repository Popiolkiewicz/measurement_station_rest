/**
 * 
 */
package org.ms.assembler;

import javax.ejb.Stateless;

import org.ms.entity.Measurement;
import org.ms.rest.model.MeasurementDTO;

/**
 * Created on 10.04.2017 19:01:42
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class MeasurementAssembler extends AbstractAssembler<MeasurementDTO, Measurement> {

	@Override
	public MeasurementDTO toDTO(Measurement entity) {
		MeasurementDTO measurementDTO = new MeasurementDTO();
		measurementDTO.setId(entity.getId());
		measurementDTO.setDate(entity.getDate());
		measurementDTO.setValue(entity.getValue());
		return measurementDTO;
	}

	@Override
	public Measurement fromDTO(MeasurementDTO dto) {
		Measurement measurement = new Measurement();
		if (dto.getId() != null)
			measurement.setId(dto.getId());
		measurement.setDate(dto.getDate());
		measurement.setValue(dto.getValue());
		return measurement;
	}

}
