package org.webcrawler.labels;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class ScoreLabel extends JLabel implements TableCellRenderer {
	Object[][] matches;
	Color color;
	public ScoreLabel(Object[][] match) {
		matches = match;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if( row <0 || column <0) {
			System.out.println("row is bad");
			return this;
		}
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		//check which team's color score this is
		if(column == 7) {
			
			color = Color.red;
		}else{
			color = Color.blue;
		}
		
		boolean redWon = true;
		if(color.equals(Color.red)) {
			if(Integer.parseInt((String) matches[row][column]) > Integer.parseInt((String) matches[row][column + 1])){
				redWon = true;
			}else 
				redWon = false;
		}else {
			if(Integer.parseInt((String) matches[row][column]) > Integer.parseInt((String) matches[row][column - 1])){
				redWon = false;
			}else 
				redWon = true;
		}
		setText((String) matches[row][column]);
		setForeground(color);
		setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("SansSerif", Font.PLAIN, 12);
		if(redWon && color.equals(Color.red)) {
			font = new Font("SansSerif", Font.BOLD, 14);
		}else if(!redWon && color.equals(Color.BLUE)) {
			font = new Font("SansSerif", Font.BOLD, 14);
		}
		setFont(font);
		return this;
	}

}
