/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.domain.services;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public interface ReferentialChangesPublisher {

	String BEAN_ID = "referentialChangesPublisher";

	void addEntity(Entity entity);

}
