/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.server;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.services.DataModificationOperation;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesMessage;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@Component(ReferentialChangesPublisher.BEAN_ID)
public class ReferentialChangesPublisherImpl implements
		ReferentialChangesPublisher {

	@Autowired
	private final JmsTemplate jmsTemplate = null;

	/**
	 * @see org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesPublisher#addEntity(org.diveintojee.poc.remote.observer.pattern.domain.Entity)
	 */
	@Override
	public void addEntity(final Entity entity) {

		final ReferentialChangesMessage message = new ReferentialChangesMessage(
				DataModificationOperation.CREATE, entity.getId(), Entity.class);

		jmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(final Session session)
					throws JMSException {

				ObjectMessage msg = session.createObjectMessage();
				msg.setObject(message);
				return msg;

			}

		});

	}

}
