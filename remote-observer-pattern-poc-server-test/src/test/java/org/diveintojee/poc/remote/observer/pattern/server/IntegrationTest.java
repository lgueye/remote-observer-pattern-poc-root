package org.diveintojee.poc.remote.observer.pattern.server;

import java.util.ResourceBundle;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.services.DataModificationOperation;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesMessage;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesSubscriber;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author louis.gueye@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { Constants.TESTS_CONTEXT })
public class IntegrationTest {

	private static final String SERVER_MODULE_CONTEXT = ResourceBundle
			.getBundle(Constants.CONFIG_BUNDLE_NAME).getString(
					Constants.SERVER_MODULE_CONTEXT_KEY);

	private static final String JETTY_PORT = ResourceBundle.getBundle(
			Constants.CONFIG_BUNDLE_NAME).getString(Constants.JETTY_PORT_KEY);

	private static Server server;

	/**
	 * @throws Throwable
	 */
	@AfterClass
	public static void afterClass() throws Throwable {

		IntegrationTest.server.stop();
		IntegrationTest.server.join();

	}

	/**
	 * @throws Throwable
	 */
	@BeforeClass
	public static void beforeClass() throws Throwable {

		IntegrationTest.server = new Server();

		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMaxThreads(100);
		IntegrationTest.server.setThreadPool(threadPool);

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(Integer.valueOf(IntegrationTest.JETTY_PORT));
		connector.setMaxIdleTime(30000);
		connector.setConfidentialPort(8443);
		IntegrationTest.server.setConnectors(new Connector[] { connector });

		WebAppContext webappTestingServerWebApp = new WebAppContext();
		webappTestingServerWebApp.setWar("../"
				+ IntegrationTest.SERVER_MODULE_CONTEXT + "/target/"
				+ IntegrationTest.SERVER_MODULE_CONTEXT + ".war");
		webappTestingServerWebApp.setContextPath("/"
				+ IntegrationTest.SERVER_MODULE_CONTEXT);

		IntegrationTest.server.addHandler(webappTestingServerWebApp);

		IntegrationTest.server.start();

		IntegrationTest.server.setStopAtShutdown(true);

		IntegrationTest.server.setSendServerVersion(true);

	}

	@Autowired
	@Qualifier(ReferentialService.BEAN_ID)
	private ReferentialService referentialService;

	// Subscriber
	@Autowired
	@Qualifier(ReferentialChangesSubscriber.BEAN_ID)
	private ReferentialChangesSubscriber referentialChangesSubscriber;

	@Test
	public final void addEntityWillNotifySubscribers() {
		Entity entity = new Entity();
		String label = "test entity";
		entity.setLabel(label);
		Long id = referentialService.addEntity(entity);

		ReferentialChangesMessage referentialChangesMessage = referentialChangesSubscriber
				.getLastMessage();
		Assert.assertNotNull(referentialChangesMessage);
		Assert.assertEquals(Entity.class,
				referentialChangesMessage.getEntityClass());
		Assert.assertEquals(DataModificationOperation.CREATE,
				referentialChangesMessage.getOperation());
		Assert.assertEquals(id, referentialChangesMessage.getId());

	}

	/**
    * 
    */
	@Before
	public void before() {
		Assert.assertNotNull(referentialService);
	}

}
