/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.business;

import java.util.Arrays;
import java.util.List;

import javax.jws.WebService;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.ProductType;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesPublisher;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialService;
import org.diveintojee.poc.remote.observer.pattern.persistence.PersistenceManager;
import org.diveintojee.poc.remote.observer.pattern.persistence.PersistenceManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@Service(ReferentialService.BEAN_ID)
@WebService(endpointInterface = ReferentialService.WEBSERVICE_ENDPOINT_INTERFACE)
public class ReferentialServiceImpl implements ReferentialService {

	@Autowired
	@Qualifier(PersistenceManager.BEAN_ID)
	private PersistenceManager persistenceManager;

	@Autowired
	@Qualifier(ReferentialChangesPublisher.BEAN_ID)
	private ReferentialChangesPublisher referentialChangesPublisher;

	/**
	 * @see org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialService#addEntity(org.diveintojee.poc.remote.observer.pattern.domain.Entity)
	 */
	@Override
	public Long addEntity(final Entity entity) {

		Long id = persistenceManager.persist(entity);
		referentialChangesPublisher.addEntity(entity);
		return id;

	}

	/**
	 * @see org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialService#loadAllEntities()
	 */
	@Override
	public List<Entity> loadAllEntities() {

		return Arrays.asList(PersistenceManagerImpl.ENTITIES);

	}

	@Override
	public List<ProductType> loadAllProductTypes() {

		return Arrays.asList(PersistenceManagerImpl.PRODUCT_TYPES);

	}

}
