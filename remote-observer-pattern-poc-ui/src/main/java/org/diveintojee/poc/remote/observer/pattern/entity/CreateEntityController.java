/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;
import org.diveintojee.poc.remote.observer.pattern.domain.Entity;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialService;
import org.diveintojee.poc.remote.observer.pattern.entity.view.CreateEntityInternalFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@Component(CreateEntityController.BEAN_ID)
public class CreateEntityController implements ActionListener {

	public static final String BEAN_ID = "createEntityController";

	@Autowired
	@Qualifier(ReferentialService.BEAN_ID)
	private ReferentialService referentialService;

	private CreateEntityInternalFrame view;

	private Entity model;

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (getView().getOkButton().equals(e.getSource()))
			onOkButtonClick();
		else if (getView().getCancelButton().equals(e.getSource()))
			onCancelButtonClick();
	}

	protected void addListeners() {
		getView().getOkButton().addActionListener(this);
		getView().getCancelButton().addActionListener(this);
	}

	protected void createView() {
		setView(new CreateEntityInternalFrame());
	}

	/**
	 * @return the model
	 */
	public Entity getModel() {
		return model;
	}

	/**
	 * @return the view
	 */
	public CreateEntityInternalFrame getView() {
		return view;
	}

	public void initialize() {

		createView();

		loadReferentialData();

		setModel(null);

		showView(true);

	}

	/**
	 * 
	 */
	protected void loadReferentialData() {
	}

	protected void onCancelButtonClick() {
		getView().doDefaultCloseAction();
	}

	protected void onOkButtonClick() {
		updateModelFromView();
		Long id = referentialService.addEntity(getModel());
		getModel().setId(id);
		JOptionPane.showMessageDialog(getView(),
				String.format("Entity %s created successfully", getModel()));
		updateViewFromModel();
		getView().doDefaultCloseAction();
	}

	protected void removeListeners() {
		getView().getOkButton().removeActionListener(this);
		getView().getCancelButton().removeActionListener(this);
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(final Entity model) {
		this.model = model;
		updateViewFromModel();
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(final CreateEntityInternalFrame view) {
		this.view = view;
	}

	public void showView(final boolean visible) {
		getView().setVisible(visible);
	}

	protected void updateModelFromView() {

		removeListeners();

		if (getModel() == null)
			model = new Entity();

		getModel().setLabel(getView().getLabelTextField().getText());

		addListeners();

	}

	protected void updateViewFromModel() {

		removeListeners();

		if (getModel() == null)
			getView().getLabelTextField().setText(StringUtils.EMPTY);
		else
			getView().getLabelTextField().setText(getModel().getLabel());

		addListeners();

	}
}
