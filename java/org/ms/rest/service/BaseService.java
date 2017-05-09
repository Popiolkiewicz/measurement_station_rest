/**
 * 
 */
package org.ms.rest.service;

import javax.ejb.EJB;

import org.ms.manager.AuthenticationManager;

/**
 * Created on 08.04.2017 21:19:22
 * 
 * @author Hubert Popio³kiewicz
 */
public abstract class BaseService {

	@EJB
	protected AuthenticationManager authenticationManager;
}
