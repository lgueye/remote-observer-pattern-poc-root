package org.diveintojee.poc.remote.observer.pattern.root.window.view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenu dealsMenu = null;

	private JMenuItem findDealsByCriteriaMenuItem = null;

	private JMenu referentialMenu = null;

	private JMenu entityMenu = null;

	private JMenuItem createEntityMenuItem = null;

	/**
	 * This method initializes
	 * 
	 */
	public MenuBar() {
		super();
		initialize();
	}

	/**
	 * This method initializes createEntityMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getCreateEntityMenuItem() {
		if (createEntityMenuItem == null)
			createEntityMenuItem = new JMenuItem("Create");
		createEntityMenuItem.setMnemonic(KeyEvent.VK_C);
		return createEntityMenuItem;
	}

	/**
	 * This method initializes dealsMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getDealsMenu() {
		if (dealsMenu == null) {
			dealsMenu = new JMenu("Deals");
			dealsMenu.setMnemonic(KeyEvent.VK_D);
			dealsMenu.add(getFindDealsByCriteriaMenuItem());
		}
		return dealsMenu;
	}

	/**
	 * This method initializes entityMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getEntityMenu() {
		if (entityMenu == null) {
			entityMenu = new JMenu("Entities");
			entityMenu.add(getCreateEntityMenuItem());
		}
		return entityMenu;
	}

	/**
	 * This method initializes indicatorsMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getFindDealsByCriteriaMenuItem() {
		if (findDealsByCriteriaMenuItem == null) {
			findDealsByCriteriaMenuItem = new JMenuItem("Find by criteria");
			findDealsByCriteriaMenuItem.setMnemonic(KeyEvent.VK_F);
		}
		return findDealsByCriteriaMenuItem;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getReferentialMenu() {
		if (referentialMenu == null)
			referentialMenu = new JMenu("Referential");
		referentialMenu.setMnemonic(KeyEvent.VK_R);
		referentialMenu.add(getEntityMenu());
		return referentialMenu;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(153, 21));

		this.add(getDealsMenu());
		this.add(getReferentialMenu());
	}

} // @jve:decl-index=0:visual-constraint="10,10"
