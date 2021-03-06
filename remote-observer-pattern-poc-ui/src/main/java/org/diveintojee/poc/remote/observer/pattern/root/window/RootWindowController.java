/**
 * 
 */
package org.diveintojee.poc.remote.observer.pattern.root.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;

import org.diveintojee.poc.remote.observer.pattern.Constants;
import org.diveintojee.poc.remote.observer.pattern.domain.services.ReferentialChangesMessage;
import org.diveintojee.poc.remote.observer.pattern.entity.CreateEntityController;
import org.diveintojee.poc.remote.observer.pattern.root.window.view.MenuBar;
import org.diveintojee.poc.remote.observer.pattern.root.window.view.RootWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

/**
 * @author louis.gueye@gmail.com
 * 
 */
@Component(RootWindowController.BEAN_ID)
public class RootWindowController implements ActionListener {

	public static final String BEAN_ID = "rootWindowController";

	private RootWindow view;

	@Autowired
	@Qualifier(Constants.MESSAGE_SOURCE_BEAN_ID)
	MessageSource messageSource;

	@Autowired
	@Qualifier(CreateEntityController.BEAN_ID)
	private CreateEntityController createEntityController;

	private static Random colorsRandom;

	private static Color[] COLORS;

	static {

		colorsRandom = new Random();

		COLORS = new Color[] { Color.BLUE, Color.CYAN, Color.DARK_GRAY,
				Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW,
				Color.PINK };
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {

		if (e.getSource().equals(
				((MenuBar) getView().getJMenuBar()).getCreateEntityMenuItem()))
			onCreateEntityMenuItemClick();

	}

	public void addInternalFrame(final JInternalFrame frame) {

		if (frame == null)
			return;

		if (containsInternalFrame(frame))
			return;

		((JDesktopPane) getView().getContentPane()).add(frame);

	}

	protected void addListeners() {

		((MenuBar) getView().getJMenuBar()).getCreateEntityMenuItem()
				.addActionListener(this);
	}

	protected boolean containsInternalFrame(final JInternalFrame frame) {

		JDesktopPane desktop = (JDesktopPane) getView().getContentPane();

		if (desktop.getComponents() == null
				|| desktop.getComponents().length == 0)
			return false;

		return Arrays.asList(desktop.getComponents()).contains(frame);

	}

	private void createView() {

		try {
			UIManager.setLookAndFeel(PlasticXPLookAndFeel.class.getName());
		} catch (Throwable e) {
			e.printStackTrace();
			// How ugly
		}

		setView(new RootWindow());

		int initialWidth = Integer.valueOf(messageSource.getMessage(
				Constants.ROOT_WINDOW_INITIAL_WIDTH_KEY, null, null));

		int initialHeight = Integer.valueOf(messageSource.getMessage(
				Constants.ROOT_WINDOW_INITIAL_HEIGHT_KEY, null, null));

		getView().setPreferredSize(new Dimension(initialWidth, initialHeight));

		getView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getView().setResizable(true);

		getView().pack();
	}

	public RootWindow getView() {
		return view;
	}

	public void initialize() {

		createView();

		showView(true);

		addListeners();

	}

	protected void onCreateEntityMenuItemClick() {
		createEntityController.initialize();
		JInternalFrame frame = createEntityController.getView();
		createEntityController.showView(true);

		addInternalFrame(frame);
		showInternalFrame(frame);
	}

	public void setView(final Object view) {
		this.view = (RootWindow) view;
	}

	public void showInternalFrame(final JInternalFrame frame) {

		if (frame == null)
			return;

		if (!containsInternalFrame(frame))
			return;

		frame.moveToFront();

	}

	public void showView(final boolean visibility) {

		if (getView() == null)
			throw new IllegalStateException("Consider initializing the view");

		getView().setVisible(true);

	}

	/**
	 * Business method that occurs on referential change message<br/>
	 * 
	 * @param lastMessage
	 */
	public void updateStatusBarText(final ReferentialChangesMessage lastMessage) {

		getView().getStatusBar().setBackground(
				RootWindowController.COLORS[RootWindowController.colorsRandom
						.nextInt(RootWindowController.COLORS.length)]);
		getView().getStatusBarLabel().setFont(
				new Font("Tahoma", Font.BOLD | Font.BOLD, 12));
		getView().getStatusBarLabel().setText(lastMessage.toString());
		getView().getStatusBar().revalidate();
	}

}
