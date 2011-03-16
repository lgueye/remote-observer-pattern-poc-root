/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.domain;

import java.io.Serializable;

/**
 * @author louis.gueye@gmail.com
 */
public interface PersistableEntity extends Serializable {

	/**
	 * @return
	 */
	Long getId();

	/**
	 * @param id
	 */
	void setId(Long id);
}
