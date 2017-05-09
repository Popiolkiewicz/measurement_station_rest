/**
 * 
 */
package org.ms.manager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import org.jboss.resteasy.util.Base64;
import org.ms.dao.UserDAO;
import org.ms.entity.User;

/**
 * Created on 08.04.2017 18:00:04
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateful
public class AuthenticationManager {

	@EJB
	protected UserDAO userDao;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public boolean authenticate(String authString) {
		if (authString == null || authString.isEmpty())
			return false;
		String[] credentials = decodeCredBase64(authString);
		if (credentials == null || credentials.length != 2)
			return false;
		User user = userDao.getSingle(credentials[0], credentials[1]);
		if (user == null)
			return false;
		userId = user.getId();
		return true;
	}

	private String[] decodeCredBase64(String authString) {
		String decodedAuth = "";
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		byte[] bytes = null;
		try {
			bytes = Base64.decode(authInfo);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		decodedAuth = new String(bytes);
		String[] credentials = decodedAuth.split(":", 2);
		return credentials;
	}
}
