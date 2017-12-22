package com.nyu.tiksysclient.table;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;

public class BookButtonRenderer extends JButton implements TableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 901721317826770297L;

	public JComponent getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
        //value 源于editor
        //String text = (value == null) ? "" : value.toString();
		if(value.equals(true)){
			setText("预订");
		}else{
			setText("无票");
			setEnabled(false);
		}
        //按钮文字
        //setText(text);
        //if(text.equals("full") || text.equals("canceled")){
      	//  setEnabled(false);
        //}
        //单元格提示
        //setToolTipText(text);
        //背景色
        //setBackground(Color.BLACK);
        //前景色
        //setForeground(Color.green);
		return this;
    }
}