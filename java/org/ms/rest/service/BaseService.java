/**
 * 
 */
package org.ms.rest.service;

import javax.ejb.EJB;

import org.ms.manager.AuthenticationManager;

/**
 * Created on 08.04.2017 21:19:22
 * 
 * @author Hubert Popiołkiewicz
 */
public abstract class BaseService {

	@EJB
	protected AuthenticationManager authenticationManager;
}
