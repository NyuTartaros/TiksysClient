package com.nyu.tiksysclient.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RootPaneContainer;

import com.nyu.tiksysclient.services.TicketService;
import com.nyu.tiksysclient.ui.BookFrame;

public class CancelSeatButtonEditor extends DefaultCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6371218468069799019L;
	protected JButton button;//represent the  cellEditorComponent
    private boolean cellValue;//����cellEditorValue
    private Timestamp time;
    private TicketService ticketService;
    private String name;
    private String phoneNo;

    public CancelSeatButtonEditor(TicketService pService, String name, String phoneNo) {
    	super(new JCheckBox());
    	ticketService = pService;
    	this.name = name;
    	this.phoneNo = phoneNo;
    	button = new JButton();
    	button.setOpaque(true);
    	button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
      	  	if(!button.isEnabled()){
      	  		return;
      	  	}
      	  	if(ticketService.cancelMeal(time.getTime(), name, phoneNo)) {
      	  		JOptionPane.showMessageDialog(button, "�˶��ɹ�");
      	  	}else {
      	  		JOptionPane.showMessageDialog(button, "�˶�ʧ��");
      	  	}
        	//ˢ����Ⱦ��
        	fireEditingStopped();
        }
      });
    }

    public JComponent getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
    	//value Դ�ڵ�Ԫ����ֵ
    	cellValue = (boolean) value;
    	time = Timestamp.valueOf(table.getValueAt(row, 0).toString()+":00.0");
    	return button;
    }

    public Object getCellEditorValue() {
    	return cellValue;
    }
    
}