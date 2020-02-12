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
	private JLabel lbTitle;

	public TopCyclistPanel() {
		setBackground(Color.WHITE);
		lbTitle = new JLabel("Top 10");
		this.add(lbTitle);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		model = new MyTableModel();
		lista = new Object[10][6];

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
//		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		repaint();
		revalidate();
	}

	public void setRacersToFinaly(ArrayList<Cyclist> racers) {
		lbTitle.setText("REPORTE");
		model.setRows(racers.size());
		model.setCols(6);
		model.setColumnNames(new String[] { "#", "Estado", "Fitness", "Velocidad", "Fatiga", "Tiempo de Llegada" });
		lista = new Object[racers.size()][model.getColumnCount()];
		for (int i = 0; i < racers.size(); i++) {
			this.lista[i][0] = (i + 1) + " - Ciclista" + racers.get(i).getId();
			this.lista[i][1] = racers.get(i).getCyclistState();
			this.lista[i][2] = racers.get(i).getFitnessFactor();
			this.lista[i][3] = racers.get(i).getVelocityAccordingFormKmH();
			this.lista[i][4] = racers.get(i).getFatigue();
			this.lista[i][5] = racers.get(i).getArrivalTime();
		}
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		repaint();
		revalidate();
	}
}

class MyTableModel extends AbstractTableModel {
	String[] columnNames = { "#", "Estado", "Fitness", "Velocidad", "Fatiga", "Tiempo de Llegada" };
	private int rows;
	private int cols;

	public MyTableModel() {
		this.rows = 10;
		this.cols = columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col].toString();
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public int getColumnCount() {
		return cols;
	}

	public int getRowCount() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
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
//			System.err.println(CyclistState.DEQUALIFIED.name());
			ImageIcon icon = new ImageIcon(
					value.toString().equals(CyclistState.DEQUALIFIED.name()) ? Constants.DEQUALIFIED
							: value.toString().equals(CyclistState.FINISHER.name()) ? Constants.IMG_FINISHER
									: Constants.RACING);
			lbl.setIcon(icon);
		}
		return lbl;
	}
}
