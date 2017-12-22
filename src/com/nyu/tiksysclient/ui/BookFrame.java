package com.nyu.tiksysclient.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.nyu.tiksysclient.services.TicketService;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.awt.Toolkit;

public class BookFrame extends JFrame{
	private TicketService ticketService;
	private JButton button;
	private Timestamp time;
	private JTextField nameField;
	private JTextField phoneNoField;
	
	
	public BookFrame(TicketService pService, JButton jButton, Timestamp timestamp){
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workspace\\TiksysClient\\Train.png"));
		setAlwaysOnTop(true);
		setTitle("\u9884\u8BA2\u8F66\u7968");
		ticketService = pService;
		button = jButton;
		time = timestamp;
		setSize(400, 200);
		setLocationRelativeTo(button);
		getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(nameField.getText().equals("\u8BF7\u8F93\u5165\u59D3\u540D")){
					nameField.setText("");
				}
			}
		});
		nameField.setText("\u8BF7\u8F93\u5165\u59D3\u540D");
		nameField.setBounds(81, 28, 232, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		phoneNoField = new JTextField();
		phoneNoField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(phoneNoField.getText().equals("\u8BF7\u8F93\u5165\u7535\u8BDD")){
					phoneNoField.setText("");
				}
			}
		});
		phoneNoField.setText("\u8BF7\u8F93\u5165\u7535\u8BDD");
		phoneNoField.setBounds(81, 70, 232, 21);
		getContentPane().add(phoneNoField);
		phoneNoField.setColumns(10);
		
		JButton bookBtn = new JButton("\u9884\u8BA2");
		bookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText();
				String phoneNo = phoneNoField.getText();
				if(name.equals("")){
					JOptionPane.showMessageDialog(rootPane, "����д����");
				}
				if(phoneNo.equals("")){
					JOptionPane.showMessageDialog(rootPane, "����д�ֻ���");
				}
				if(!judgePhoneNo(phoneNo)){
					JOptionPane.showMessageDialog(rootPane, "��Ч���ֻ���");
					return;
				}
				if(pService.book(time.getTime(), name, phoneNo)){
					JOptionPane.showMessageDialog(rootPane, name+"�ͻ�����ϲ����Ʊ�ɹ�");
				}else {
					JOptionPane.showMessageDialog(rootPane, "�ܱ�Ǹ����Ʊʧ��");
				}
			}
		});
		bookBtn.setBounds(144, 116, 93, 23);
		getContentPane().add(bookBtn);
	}
	
	private boolean judgePhoneNo(String phoneStr){
		if(phoneStr.length() != 11){
			return false;
		}else if (phoneStr.charAt(0) != '1') {
			return false;
		}
		return true;
	}
	
}
