/**
 * 
 */
package org.ms.assembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 09.04.2017 23:06:01
 * 
 * @author Hubert Popio³kiewicz
 */
public abstract class AbstractAssembler<DTO, E> {

	public abstract DTO toDTO(E entity);

	public abstract E fromDTO(DTO dto);

	public List<E> fromDTO(List<DTO> dtos) {
		List<E> result = new ArrayList<E>();
		dtos.forEach(dto -> result.add(fromDTO(dto)));
		return result;
	}

	public List<DTO> toDTO(List<E> entities) {
		List<DTO> result = new ArrayList<DTO>();
		entities.forEach(dto -> result.add(toDTO(dto)));
		return result;
	}
}
