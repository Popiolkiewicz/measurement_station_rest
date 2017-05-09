package org.ms.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.ms.assembler.UserAssembler;
import org.ms.dao.UserDAO;
import org.ms.entity.User;
import org.ms.rest.model.UserDTO;

/**
 * TODO obs³u¿yæ instniej¹cy login Created on 06.04.2017 21:58:50
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class UserManager {

	@EJB
	UserDAO userDAO;

	@EJB
	UserAssembler userAssembler;

	public void login(UserDTO userDTO) {
		User single = userDAO.getSingle(userDTO.getLogin(), userDTO.getPassword());
		if (single != null)
			userDTO.setId(single.getId());
	}

	public boolean register(UserDTO userDTO) {
		User user = userAssembler.fromDTO(userDTO);
		try {
			userDAO.save(user);
			userDTO.setId(user.getId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
