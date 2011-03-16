/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.server;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.services.DataModificationOperation;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesMessage;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesPublisher;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesSubscriber;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { Constants.TESTS_CONTEXT })
public class TopicListenerTest {

	// Publisher
	@Autowired
	@Qualifier(ReferentialChangesPublisher.BEAN_ID)
	private ReferentialChangesPublisher referentialChangesPublisher;

	// Subscriber
	@Autowired
	@Qualifier(ReferentialChangesSubscriber.BEAN_ID)
	private ReferentialChangesSubscriber referentialChangesSubscriber;

	@Test
	public final void addEntityWillNotifySubscribers() {
		Entity entity = new Entity();
		String label = "test entity";
		entity.setLabel(label);
		referentialChangesPublisher.addEntity(entity);

		ReferentialChangesMessage referentialChangesMessage = referentialChangesSubscriber
				.getLastMessage();
		Assert.assertNotNull(referentialChangesMessage);
		Assert.assertEquals(Entity.class,
				referentialChangesMessage.getEntityClass());
		Assert.assertEquals(DataModificationOperation.CREATE,
				referentialChangesMessage.getOperation());
		Assert.assertEquals(entity.getId(), referentialChangesMessage.getId());

	}

}
