/**
 * 
 */
package org.ms.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.ms.entity.AbstractEntity;

/**
 * Created on 06.04.2017 21:28:09
 * 
 * @author Hubert Popio³kiewicz
 */
public abstract class AbstractDAO<E extends AbstractEntity> {

	static class TypeArgumentIsRequiredException extends RuntimeException {

		private static final long serialVersionUID = -2641244911977775542L;
	}

	@PersistenceContext(unitName = "measurement_station")
	protected EntityManager entityManager;

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	protected AbstractDAO() {
		Type[] types = findArgumentsOfParametrizedSuperclass(getClass(), AbstractDAO.class);
		if (types != null && types.length == 1)
			entityClass = (Class<E>) types[0];
		else
			throw new TypeArgumentIsRequiredException();
	}

	public Type[] findArgumentsOfParametrizedSuperclass(Class<?> aClazz, Class<?> searchedParametrizedSuperclass) {
		if (aClazz.equals(Object.class)) {
			return null;
		}
		Type type = aClazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			if (searchedParametrizedSuperclass.equals(parameterizedType.getRawType())) {
				return parameterizedType.getActualTypeArguments();
			}
		}
		return findArgumentsOfParametrizedSuperclass(aClazz.getSuperclass(), searchedParametrizedSuperclass);
	}

	@SuppressWarnings("unchecked")
	public E getSingleResult(Query query) {
		try {
			return (E) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public E get(long id) {
		try {
			return entityManager.find(entityClass, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<E> getAll() {
		TypedQuery<E> typedQuery = entityManager.createQuery(" FROM " + entityClass.getSimpleName(), entityClass);
		return typedQuery.getResultList();
	}

	public E update(E entity) {
		E mergedEntity = entityManager.merge(entity);
		return mergedEntity;
	}

	public E save(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	public List<E> save(List<E> entities) {
		List<E> result = new ArrayList<>();
		entities.forEach(entity -> result.add(save(entity)));
		return result;
	}
}
