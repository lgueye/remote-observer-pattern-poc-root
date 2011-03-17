/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.persistence;

import java.util.List;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public interface PersistenceManager {

	String BEAN_ID = "persistenceManager";

	/**
	 * @param entityClass
	 */
	<T> void clear(Class<T> entityClass);

	/**
	 * @param entity
	 */
	<T> void delete(Class<T> entityClass, Long id);

	/**
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	<T> List<T> findAll(Class<T> entityClass);

	/**
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	<T> T get(Class<T> entityClass, Long id);

	/**
	 * @param entity
	 */
	Long persist(Object entity);

}
