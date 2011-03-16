/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.entity.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

/**
 * @author louis.gueye@gmail.com
 * 
 */
public class CreateEntityInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7665073746478886827L;
	private JPanel jContentPane = null;
	private JLabel labelLabel = null;
	private JTextField labelTextField = null;
	private JButton okButton = null;
	private JButton cancelButton = null;

	/**
	 * This method initializes
	 * 
	 */
	public CreateEntityInternalFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes cancelButton
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
		}
		return cancelButton;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			labelLabel = new JLabel();
			labelLabel.setText("Label");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.setBorder(BorderFactory.createTitledBorder(null,
					"Create Entity", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jContentPane.add(labelLabel, gridBagConstraints);
			jContentPane.add(getLabelTextField(), gridBagConstraints1);
			jContentPane.add(getOkButton(), gridBagConstraints2);
			jContentPane.add(getCancelButton(), gridBagConstraints3);
		}
		return jContentPane;
	}

	/**
	 * This method initializes labelTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getLabelTextField() {
		if (labelTextField == null)
			labelTextField = new JTextField();
		return labelTextField;
	}

	/**
	 * This method initializes okButton
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ok");
		}
		return okButton;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(271, 121));
		this.setContentPane(getJContentPane());
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
