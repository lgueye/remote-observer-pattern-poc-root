/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.domain.services;

import java.util.List;

import javax.jws.WebService;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@WebService
public interface ReferentialService {

	String BEAN_ID = "referentialService"; //$NON-NLS-1$

	String WEBSERVICE_ENDPOINT_INTERFACE = "org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialService"; //$NON-NLS-1$

	Long addEntity(Entity entity);

	List<Entity> loadAllEntities();

}
