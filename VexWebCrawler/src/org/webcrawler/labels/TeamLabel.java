package org.webcrawler.labels;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;


public class TeamLabel extends JLabel implements TableCellRenderer {
	Object[][] matches;
	Color color;
	public TeamLabel(Object[][] match) {
		matches = match;
	}
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			//if column is one for blue teams,
			if( column > 3) {
				color = Color.BLUE;
			}else 
				color = Color.red;
			
			setText((String) matches[row][column]);
			setForeground(color);
			setHorizontalAlignment(SwingConstants.CENTER);
			Font font = new Font("SansSerif", Font.PLAIN, 12);
			setFont(font);
			return this;
		}

	}
