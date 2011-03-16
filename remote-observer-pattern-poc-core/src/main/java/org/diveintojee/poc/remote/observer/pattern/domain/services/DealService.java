/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.domain.services;

import java.util.List;

import javax.jws.WebService;

import org.diveintojee.poc.remote.observer.pattern.domain.Deal;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@WebService
public interface DealService {

	String BEAN_ID = "dealService";

	String WEBSERVICE_ENDPOINT_INTERFACE = "org.diveintojee.poc.remote.observer.pattern.domain.services.DealService";

	List<Deal> findByCriteria(DealsSearchCriteria criteria);
}
