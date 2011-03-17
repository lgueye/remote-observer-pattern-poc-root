/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.business;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import junit.framework.Assert;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesPublisher;
import org.diveintojee.poc.remote.observer.pattern.persistence.PersistenceManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author louis
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ReferentialServiceImplTest {

	@InjectMocks
	private final ReferentialServiceImpl underTest = new ReferentialServiceImpl();

	@Mock
	private final PersistenceManager persistenceManager = null;

	@Mock
	private final ReferentialChangesPublisher referentialChangesPublisher = null;

	@Test
	public final void addEntityWillPublishNotifications() {

		// Given
		Entity entity = new Entity();
		final Long id = 45L;
		given(persistenceManager.persist(entity)).willReturn(id);
		doNothing().when(referentialChangesPublisher).addEntity(entity);

		// When
		Long result = underTest.addEntity(entity);

		verify(persistenceManager, times(1)).persist(entity);
		verify(referentialChangesPublisher, times(1)).addEntity(entity);
		Assert.assertEquals(id, result);

	}
}
