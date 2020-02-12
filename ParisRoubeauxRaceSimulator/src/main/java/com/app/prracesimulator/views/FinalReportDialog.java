package com.app.prracesimulator.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;
import com.app.prracesimulator.util.Constants;

public class FinalReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private MyTableModel model;

	public FinalReportDialog(ArrayList<Cyclist> ciclistas) {
		setLayout(new BorderLayout());
		TopCyclistPanel topCyclistPanel = new TopCyclistPanel();
		topCyclistPanel.setRacersToFinaly(ciclistas);
		add(topCyclistPanel, BorderLayout.CENTER);
		setSize(700, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}