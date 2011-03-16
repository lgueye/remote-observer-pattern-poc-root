/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.domain.services;

import javax.jms.MessageListener;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public interface ReferentialChangesSubscriber extends MessageListener {

	String BEAN_ID = "referentialChangesSubscriber";

	ReferentialChangesMessage getLastMessage();

}
