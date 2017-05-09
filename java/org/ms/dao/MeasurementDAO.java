package org.ms.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.ms.entity.Device;
import org.ms.entity.Measurement;
import org.ms.entity.MeasurementType;

/**
 * Created on 05.04.2017 22:12:08
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class MeasurementDAO extends AbstractDAO<Measurement> {

	public List<Measurement> getNewest(Long userId) {
		TypedQuery<Measurement> typedQuery = entityManager.createQuery(
				"select distinct m from " + entityClass.getSimpleName() + " m, " + MeasurementType.class.getSimpleName()
						+ " mt, " + Device.class.getSimpleName() + " d " + "where m.measurementType.id = mt.id "
						+ "and mt.device.id = d.id " + "and d.user.id = :userId ",
				entityClass).setParameter("userId", userId);
		return typedQuery.getResultList();
	}

	public List<Measurement> getAll(Long measurementTypeId) {
		TypedQuery<Measurement> typedQuery = entityManager
				.createQuery("from " + entityClass.getSimpleName()
						+ " where measurement_type_id = :measurementTypeId ORDER BY date DESC", entityClass)
				.setParameter("measurementTypeId", measurementTypeId);
		return typedQuery.setMaxResults(100).getResultList();
	}

}
