/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.business;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.services.DataModificationOperation;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public class ReferentialChangesPublisherImplTest {

	private ReferentialChangesPublisherImpl underTest;

	@Before
	public final void before() {
		underTest = new ReferentialChangesPublisherImpl();
	}

	/**
	 * Test method for
	 * {@link org.diveintojee.poc.remote.observer.pattern.business.ReferentialChangesPublisherImpl#addEntity(org.diveintojee.poc.remote.observer.pattern.domain.Entity)}
	 * .
	 */
	@Test
	public final void buildCreateEntityMessageShouldReturnCreateMessage() {

		// Given
		Entity entity = new Entity();
		Long id = 45L;
		entity.setId(id);

		// When
		ReferentialChangesMessage message = underTest
				.buildCreateMessage(entity);

		// Then
		Assert.assertNotNull(message);
		Assert.assertEquals(DataModificationOperation.CREATE,
				message.getOperation());
		Assert.assertEquals(id, message.getId());
		Assert.assertEquals(Entity.class, message.getEntityClass());

	}

}
