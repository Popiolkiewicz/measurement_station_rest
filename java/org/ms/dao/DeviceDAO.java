/**
 * 
 */
package org.ms.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.ms.entity.Device;

/**
 * Created on 06.04.2017 21:54:41
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class DeviceDAO extends AbstractDAO<Device> {

	public List<Device> getAll(Long userId) {
		TypedQuery<Device> typedQuery = entityManager
				.createQuery("from " + entityClass.getSimpleName() + " where user_id = :userId", entityClass)
				.setParameter("userId", userId);
		return typedQuery.getResultList();
	}

	public Device getBySerialNumber(String deviceSerialNumber) {
		TypedQuery<Device> typedQuery = entityManager
				.createQuery("from " + entityClass.getSimpleName() + " where serial_number = :serialNumber",
						entityClass)
				.setParameter("serialNumber", deviceSerialNumber);
		return getSingleResult(typedQuery);
	}

}
