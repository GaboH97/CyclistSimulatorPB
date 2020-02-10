package com.app.prracesimulator.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private RaceAnimationPanel raceAnimationPanel;
	private CompetitorsPanel competitorsPanel;

	public MainWindow(Controller controller) {
		designWindow();
		setSize(800, 800);
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		initialComponents(controller);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeMainWindow();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}

	private void initialComponents(Controller controller) {
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(null);
		pnCenter.setBackground(Color.WHITE);
		raceAnimationPanel = new RaceAnimationPanel(controller);
		JScrollPane scroll = new JScrollPane(raceAnimationPanel);
		scroll.getVerticalScrollBar().setUnitIncrement(15);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		scroll.setBounds(0, 0, (int)(screenSize.getWidth() * .8), 380);
		pnCenter.add(scroll);
		competitorsPanel = new CompetitorsPanel(controller);
		competitorsPanel.setBounds(0, 381, (int)(screenSize.getWidth()), 245);
		pnCenter.add(competitorsPanel);

		TopCyclistPanel topCyclistPanel = new TopCyclistPanel(controller);
		topCyclistPanel.setBounds((int)(screenSize.getWidth() * .8) + 1, 0, (int)(screenSize.getWidth() * .2), 380);
		pnCenter.add(topCyclistPanel);
		add(pnCenter, BorderLayout.CENTER);

		JPanel pnTitle = new JPanel();
		pnTitle.add(new JLabel("Simulación Carrera Paris Roubaix"), BorderLayout.NORTH);
		add(pnTitle, BorderLayout.NORTH);
	}

	private static void designWindow() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		this.raceAnimationPanel.setRacersPositions(racers);
		this.competitorsPanel.setRacers(racers);
	}
}