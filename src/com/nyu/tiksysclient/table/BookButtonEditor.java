package com.nyu.tiksysclient.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.nyu.tiksysclient.services.TicketService;
import com.nyu.tiksysclient.ui.BookFrame;

public class BookButtonEditor extends DefaultCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6371218468069799019L;
	protected JButton button;//represent the  cellEditorComponent
    private boolean cellValue;//保存cellEditorValue
    private Timestamp time;
    private TicketService ticketService;

    public BookButtonEditor(TicketService pService) {
    	super(new JCheckBox());
    	ticketService = pService;
    	button = new JButton();
    	button.setOpaque(true);
    	button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	//JOptionPane.showMessageDialog(button, cellValue + ": Ouch!");
      	  	if(!button.isEnabled()){
      	  		return;
      	  	}
      	  	BookFrame bookFrame = new BookFrame(ticketService, button, time);
      	  	bookFrame.setVisible(true);
        	//刷新渲染器
        	fireEditingStopped();
        }
      });
    }

    public JComponent getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
    	//value 源于单元格数值
    	cellValue = (boolean) value;
    	time = Timestamp.valueOf(table.getValueAt(row, 0).toString()+":00.0");
    	return button;
    }

    public Object getCellEditorValue() {
    	return cellValue;
    }
    
}