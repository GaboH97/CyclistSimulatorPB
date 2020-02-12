package com.app.prracesimulator.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.app.prracesimulator.controllers.Controller;
import com.app.prracesimulator.models.entities.Cyclist;
import com.app.prracesimulator.models.entities.CyclistState;
import com.app.prracesimulator.util.Constants;

public class TopCyclistPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MyTableModel model;
	private JTable table;
	static Object[][] lista;

	public TopCyclistPanel() {
		setBackground(Color.WHITE);
		this.add(new JLabel("Top 10"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		model = new MyTableModel();
		lista = new Object[10][5];

		table = new JTable(model);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		JScrollPane pane = new JScrollPane(table);
		add(BorderLayout.CENTER, pane);
		setSize(300, 400);
		setVisible(true);
	}

	public void setRacers(ArrayList<Cyclist> racers) {
		if (racers.size() >= 10) {
			for (int i = 0; i < 10; i++) {
				this.lista[i][0] = (i + 1) + " - Ciclista" + racers.get(i).getId();
				this.lista[i][1] = racers.get(i).getCyclistState();
				this.lista[i][2] = racers.get(i).getFitnessFactor();
				this.lista[i][3] = racers.get(i).getVelocityAccordingFormKmH();
				this.lista[i][4] = racers.get(i).getFatigue();
			}
		} else {
			for (int i = 0; i < racers.size(); i++) {
				this.lista[i][0] = (i + 1) + " - Ciclista" + racers.get(i).getId();
				this.lista[i][1] = racers.get(i).getCyclistState();
				this.lista[i][2] = racers.get(i).getFitnessFactor();
				this.lista[i][3] = racers.get(i).getVelocityAccordingFormKmH();
				this.lista[i][4] = racers.get(i).getFatigue();
			}
		}
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		repaint();
		revalidate();
	}

	public void setRacersToFinaly(ArrayList<Cyclist> racers) {
		for (int i = 0; i < racers.size(); i++) {
			this.lista[i][0] = (i + 1) + " - Ciclista" + racers.get(i).getId();
			this.lista[i][1] = racers.get(i).getCyclistState();
			this.lista[i][2] = racers.get(i).getFitnessFactor();
			this.lista[i][3] = racers.get(i).getVelocityAccordingFormKmH();
			this.lista[i][4] = racers.get(i).getFatigue();
		}
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		repaint();
		revalidate();
	}
}

class MyTableModel extends AbstractTableModel {
	String[] columnNames = { "#", "Estado", "Fitness", "Velocidad", "Fatiga" };

	public String getColumnName(int col) {
		return columnNames[col].toString();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return 10;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return TopCyclistPanel.lista[rowIndex][columnIndex];
	}
}

class ImageRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	JLabel lbl = new JLabel();

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value != null) {
			ImageIcon icon = new ImageIcon(value.toString().equals(CyclistState.DEQUALIFIED) ? Constants.DEQUALIFIED
					: value.toString().equals(CyclistState.FINISHER) ? Constants.IMG_FINISHER : Constants.RACING);
			lbl.setIcon(icon);
		}
		return lbl;
	}
}
