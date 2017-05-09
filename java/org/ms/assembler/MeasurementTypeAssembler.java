/**
 * 
 */
package org.ms.assembler;

import javax.ejb.Stateless;

import org.ms.entity.MeasurementType;
import org.ms.rest.model.MeasurementTypeDTO;

/**
 * Created on 10.04.2017 18:34:03
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class MeasurementTypeAssembler extends AbstractAssembler<MeasurementTypeDTO, MeasurementType> {

	@Override
	public MeasurementTypeDTO toDTO(MeasurementType entity) {
		MeasurementTypeDTO measurementTypeDTO = new MeasurementTypeDTO();
		measurementTypeDTO.setId(entity.getId());
		measurementTypeDTO.setName(entity.getName());
		measurementTypeDTO.setCode(entity.getCode());
		measurementTypeDTO.setUnit(entity.getUnit());
		measurementTypeDTO.setTopCriticalBound(entity.getTopCriticalBound());
		measurementTypeDTO.setBottomCriticalBound(entity.getBottomCriticalBound());
		return measurementTypeDTO;
	}

	@Override
	public MeasurementType fromDTO(MeasurementTypeDTO dto) {
		MeasurementType measurementType = new MeasurementType();
		if (dto.getId() != null)
			measurementType.setId(dto.getId());
		measurementType.setName(dto.getName());
		measurementType.setCode(dto.getCode());
		measurementType.setUnit(dto.getUnit());
		measurementType.setTopCriticalBound(dto.getTopCriticalBound());
		measurementType.setBottomCriticalBound(dto.getBottomCriticalBound());
		return measurementType;
	}

}
