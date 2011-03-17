/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.PersistableEntity;
import org.springframework.stereotype.Component;

/**
 * @author louis.gueye@gmail.com
 */
@Component(PersistenceManager.BEAN_ID)
public class PersistenceManagerImpl implements PersistenceManager {

	private final Map<Class<?>, Map<Long, Object>> repository = new HashMap<Class<?>, Map<Long, Object>>();

	public static Entity[] ENTITIES = null;

	static {

		Entity paris = new Entity();
		paris.setId(0L);
		paris.setCurrencyCodes(Arrays.asList(new String[] { "EUR", "USD" }));
		paris.setLabel("Paris");

		Entity london = new Entity();
		london.setId(1L);
		london.setCurrencyCodes(Arrays.asList(new String[] { "EUR", "GBP",
				"USD" }));
		london.setLabel("London");

		Entity newYork = new Entity();
		newYork.setId(2L);
		newYork.setCurrencyCodes(Arrays.asList(new String[] { "GBP", "USD" }));
		newYork.setLabel("New-York");

		Entity tokyo = new Entity();
		tokyo.setId(3L);
		tokyo.setCurrencyCodes(Arrays.asList(new String[] { "USD", "JPY" }));
		tokyo.setLabel("Tokyo");

		Entity hkg = new Entity();
		hkg.setId(4L);
		hkg.setCurrencyCodes(Arrays.asList(new String[] { "USD" }));
		hkg.setLabel("Hong-Kong");

		PersistenceManagerImpl.ENTITIES = new Entity[] { paris, london,
				newYork, hkg, tokyo };

	}

	/**
	 * @see org.diveintojee.poc.rest.persistence.PersistenceManager#clear(java.lang.Class)
	 */
	@Override
	public <T> void clear(final Class<T> entityClass) {

		if (entityClass == null)
			return;

		getRepository().remove(entityClass);

	}

	/**
	 * @see org.diveintojee.poc.rest.persistence.PersistenceManager#delete(java.lang.Class,
	 *      java.lang.Object)
	 */
	@Override
	public <T> void delete(final Class<T> entityClass, final Long id) {

		if (entityClass == null)
			return;

		if (id == null)
			return;

		Map<Long, Object> entities = getRepository().get(entityClass);

		if (MapUtils.isEmpty(entities))
			return;

		entities.remove(id);

	}

	/**
	 * @see org.diveintojee.poc.rest.persistence.PersistenceManager#findAll(java.lang.Class)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> List<T> findAll(final Class<T> entityClass) {

		if (entityClass == null)
			return null;

		Map<Long, Object> entities = getRepository().get(entityClass);

		if (MapUtils.isEmpty(entities))
			return null;

		return new ArrayList(entities.values());

	}

	/**
	 * @see org.diveintojee.poc.rest.persistence.PersistenceManager#get(java.lang.Class,
	 *      java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(final Class<T> entityClass, final Long id) {

		if (entityClass == null)
			return null;

		if (id == null)
			return null;

		Map<Long, Object> entities = getRepository().get(entityClass);

		if (MapUtils.isEmpty(entities))
			return null;

		return (T) entities.get(id);

	}

	/**
	 * @return
	 */
	private Map<Class<?>, Map<Long, Object>> getRepository() {
		return repository;
	}

	/**
	 * @see org.diveintojee.poc.rest.persistence.PersistenceManager#persist(java.lang.Object)
	 */
	@Override
	public Long persist(final Object entity) {

		if (entity == null)
			return null;

		Map<Long, Object> entities = getRepository().get(entity.getClass());

		if (entities == null)
			getRepository().put(entity.getClass(), new HashMap<Long, Object>());

		entities = getRepository().get(entity.getClass());

		Set<Long> idsAsSet = entities.keySet();

		List<Long> idsAsList = new ArrayList<Long>(idsAsSet);

		Collections.sort(idsAsList);

		Long entityId = ((PersistableEntity) entity).getId();

		if (entityId == null) {

			Long lastId = idsAsList.size() == 0 ? null : idsAsList
					.get(idsAsList.size() - 1);

			if (lastId == null)
				((PersistableEntity) entity).setId(0L);
			else
				((PersistableEntity) entity).setId(Long.valueOf(lastId
						.longValue() + 1));

		}

		getRepository().get(entity.getClass()).put(
				((PersistableEntity) entity).getId(), entity);

		return ((PersistableEntity) entity).getId();

	}

}
