/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.persistence;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author louis.gueye@gmail.com
 */
public class PersistenceManagerImplTest {

	private PersistenceManager persistenceManager;

	/**
	 * Test method for {@link
	 * org.diveintojee.poc.rest.persistence.PersistenceManagerImpl##findAll(java
	 * .lang.Class)} .
	 */
	@Test
	public final void findAllWillReturnNullWithEmptyEntityMap() {
		Assert.assertNull(persistenceManager.findAll(Entity.class));
	}

	/**
	 * Test method for {@link
	 * org.diveintojee.poc.rest.persistence.PersistenceManagerImpl##findAll(java
	 * .lang.Class)} .
	 */
	@Test
	public final void findAllWillReturnNullWithNullEntityClass() {
		Assert.assertNull(persistenceManager.findAll(null));
	}

	/**
	 * Test method for
	 * {@link org.diveintojee.poc.rest.persistence.PersistenceManagerImpl#get(java.lang.Class, java.lang.Long)}
	 * .
	 */
	@Test
	public final void getWillReturnNullWithNotFoundEntityMap() {
		Assert.assertNull(persistenceManager.get(Entity.class, Long.valueOf(5)));
	}

	/**
	 * Test method for
	 * {@link org.diveintojee.poc.rest.persistence.PersistenceManagerImpl#get(java.lang.Class, java.lang.Long)}
	 * .
	 */
	@Test
	public final void getWillReturnNullWithNullEntityClass() {
		Assert.assertNull(persistenceManager.get(null, Long.valueOf(0)));
	}

	/**
	 * Test method for
	 * {@link org.diveintojee.poc.rest.persistence.PersistenceManagerImpl#get(java.lang.Class, java.lang.Long)}
	 * .
	 */
	@Test
	public final void getWillReturnNullWithNullId() {
		Assert.assertNull(persistenceManager.get(Entity.class, null));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		persistenceManager = new PersistenceManagerImpl();
	}

}
