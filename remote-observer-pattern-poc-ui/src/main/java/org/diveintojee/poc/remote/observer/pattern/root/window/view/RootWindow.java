package org.diveintojee.poc.remote.observer.pattern.root.window.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RootWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JDesktopPane jContentPane = null;

	private JLabel statusBarLabel;

	private JPanel statusBar;

	/**
	 * This is the default constructor
	 */
	public RootWindow() {
		super();
		initialize();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JDesktopPane getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JDesktopPane();
			this.jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getStatusBar(), BorderLayout.SOUTH);
		}
		return this.jContentPane;
	}

	public JPanel getStatusBar() {
		if (this.statusBar == null)
			this.statusBar = new JPanel();
		this.statusBar
				.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.statusBar.setLayout(new FlowLayout());
		this.statusBar.add(getStatusBarLabel());
		return statusBar;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JLabel getStatusBarLabel() {
		if (this.statusBarLabel == null)
			this.statusBarLabel = new JLabel("Waiting for messages");
		return this.statusBarLabel;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle(getClass().getName());
		this.setJMenuBar(new MenuBar());

	}

}
