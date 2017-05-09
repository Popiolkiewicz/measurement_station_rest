/**
 * 
 */
package org.ms.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.ms.entity.MeasurementType;

/**
 * Created on 06.04.2017 21:54:41
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class MeasurementTypeDAO extends AbstractDAO<MeasurementType> {

	public List<MeasurementType> getAll(Long deviceId) {
		TypedQuery<MeasurementType> typedQuery = entityManager
				.createQuery("from " + entityClass.getSimpleName() + " where device_id = :deviceId", entityClass)
				.setParameter("deviceId", deviceId);
		return typedQuery.getResultList();
	}

	public MeasurementType getByCode(String measurementTypeCode) {
		TypedQuery<MeasurementType> typedQuery = entityManager
				.createQuery("from " + entityClass.getSimpleName() + " where code = :measurementTypeCode", entityClass)
				.setParameter("measurementTypeCode", measurementTypeCode);
		return getSingleResult(typedQuery);
	}

}
