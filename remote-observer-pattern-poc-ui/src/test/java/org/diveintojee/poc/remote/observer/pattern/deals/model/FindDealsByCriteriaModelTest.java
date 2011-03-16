/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.deals.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public class FindDealsByCriteriaModelTest {

	/**
	 * Test method for
	 * {@link org.diveintojee.poc.remote.observer.pattern.deals.model.FindDealsByCriteriaModel#FindDealsByCriteriaModel()}
	 * .
	 */
	@Test
	public final void defaultConstructorWillInstanciateSearchCriteria() {
		FindDealsByCriteriaModel model = new FindDealsByCriteriaModel();
		Assert.assertNotNull(model.getCriteria());
	}

}
