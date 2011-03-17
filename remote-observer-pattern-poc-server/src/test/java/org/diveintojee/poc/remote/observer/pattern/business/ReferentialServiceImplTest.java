/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.business;

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
	public final void testSomething() {
	}
}
