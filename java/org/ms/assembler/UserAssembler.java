/**
 * 
 */
package org.ms.assembler;

import javax.ejb.Stateless;

import org.ms.entity.User;
import org.ms.rest.model.UserDTO;

/**
 * Created on 09.04.2017 23:05:52
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class UserAssembler extends AbstractAssembler<UserDTO, User> {

	@Override
	public UserDTO toDTO(User entity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(entity.getId());
		userDTO.setLogin(entity.getLogin());
		userDTO.setPassword(entity.getPasswordHash());
		return userDTO;
	}

	@Override
	public User fromDTO(UserDTO dto) {
		User user = new User();
		if (dto.getId() != null)
			user.setId(dto.getId());
		user.setId(dto.getId());
		user.setLogin(dto.getLogin());
		user.setPasswordHash(dto.getPassword());
		return user;
	}

}
