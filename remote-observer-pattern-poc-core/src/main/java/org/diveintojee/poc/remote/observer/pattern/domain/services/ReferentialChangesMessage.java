/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.domain.services;

import java.io.Serializable;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public class ReferentialChangesMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6619355290048252299L;

	public static final String BEAN_ID = "referentialChangesMessage";

	private final DataModificationOperation operation;
	private final Long id;
	private final Class<?> entityClass;

	public ReferentialChangesMessage(final DataModificationOperation operation,
			final Long id, final Class<?> entityClass) {
		super();
		this.operation = operation;
		this.id = id;
		this.entityClass = entityClass;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public Long getId() {
		return id;
	}

	public DataModificationOperation getOperation() {
		return operation;
	}

	@Override
	public String toString() {

		String translatedOperation = getOperation().name();
		switch (getOperation()) {
		case CREATE:
			translatedOperation = "created";
			break;
		case DELETE:
			translatedOperation = "deleted";
			break;
		case UPDATE:
			translatedOperation = "updated";
			break;

		default:
			break;
		}

		return "Successfully " + translatedOperation + " "
				+ getEntityClass().getSimpleName() + " with id " + getId();

	}

}
