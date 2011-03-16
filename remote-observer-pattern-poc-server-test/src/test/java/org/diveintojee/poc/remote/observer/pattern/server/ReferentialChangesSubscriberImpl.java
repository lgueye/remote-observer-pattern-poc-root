package org.diveintojee.poc.remote.observer.pattern.server;

/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.commons.collections.CollectionUtils;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesMessage;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesSubscriber;
import org.springframework.stereotype.Component;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@Component(value = ReferentialChangesSubscriber.BEAN_ID)
public class ReferentialChangesSubscriberImpl implements
		ReferentialChangesSubscriber {

	private List<ReferentialChangesMessage> messages;

	/**
	 * @see org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesSubscriber#getLastMessage()
	 */
	@Override
	public ReferentialChangesMessage getLastMessage() {

		if (CollectionUtils.isEmpty(getMessages()))
			return null;

		return getMessages().get(getMessages().size() - 1);

	}

	/**
	 * @return
	 */
	private List<ReferentialChangesMessage> getMessages() {
		return messages;
	}

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {

		if (getMessages() == null)
			setMessages(new ArrayList<ReferentialChangesMessage>());

		try {

			getMessages().add(
					(ReferentialChangesMessage) ((ObjectMessage) message)
							.getObject());

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param messages
	 */
	private void setMessages(final List<ReferentialChangesMessage> messages) {
		this.messages = messages;
	}

}
