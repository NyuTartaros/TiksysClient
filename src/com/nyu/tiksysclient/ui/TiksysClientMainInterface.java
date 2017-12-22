package com.nyu.tiksysclient.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.alibaba.fastjson.JSON;
import com.nyu.tiksysclient.entity.BookedSeatRecord;
import com.nyu.tiksysclient.entity.RemainTicketRecord;
import com.nyu.tiksysclient.services.TicketService;
import com.nyu.tiksysclient.table.BookButtonEditor;
import com.nyu.tiksysclient.table.BookButtonRenderer;
import com.nyu.tiksysclient.table.CancelSeatButtonEditor;
import com.nyu.tiksysclient.table.CancelSeatButtonRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class TiksysClientMainInterface extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6269676331368987169L;
	
	private TicketService pService;
	
	private DefaultTableModel remainTicketModel = new DefaultTableModel();
	private DefaultTableModel cancelSeatModel = new DefaultTableModel();
	
	private JTabbedPane tabbedPane;
	private JPanel bookingPanel;
	private JPanel cancelSeatPanel;
	private JTextField bookingDateField;
	private JButton queryTicketBtn;
	private JScrollPane bookingScrollPane;
	private JTable bookingTable;
	private JTextField cancleSeatNameField;
	private JTextField cancleSeatPhoneField;
	private JTable cancleSeatTable;
	private JButton cancleSeatQueryBtn;
	private JScrollPane cancleSeatScrollPane;
	
	public TiksysClientMainInterface() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workspace\\TiksysClient\\Train.png"));
		setResizable(false);
		setTitle("\u8BA2\u7968\u5BA2\u6237\u7AEF");
		setSize(370, 510);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		bookingPanel = new JPanel();
		tabbedPane.addTab("\u8BA2\u7968", null, bookingPanel, null);
		bookingPanel.setLayout(null);
		
		bookingDateField = new JTextField();
		bookingDateField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(bookingDateField.getText().equals("\u8BF7\u8F93\u5165\u65E5\u671F")){
					bookingDateField.setText("");
				}
			}
		});
		bookingDateField.setBounds(50, 24, 134, 21);
		bookingDateField.setText("\u8BF7\u8F93\u5165\u65E5\u671F");
		bookingPanel.add(bookingDateField);
		
		queryTicketBtn = new JButton("\u67E5\u8BE2\u4F59\u7968");
		queryTicketBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String dateStr = bookingDateField.getText();
				Integer month = Integer.parseInt(dateStr.substring(0, 2));
				Integer day = Integer.parseInt(dateStr.substring(2,4));
				java.util.Date nowDate = new java.util.Date();
				Integer nowYear = nowDate.getYear()+1900;
				Integer nowMonth = nowDate.getMonth()+1;
				Integer nowDay = nowDate.getDate();
				Integer nowHour = nowDate.getHours();
				if(day == nowDay && nowHour >= 23){
					JOptionPane.showMessageDialog(rootPane, "超过23:00, 不可再预订今日车票.");
					return;
				}else if (day < nowDay || month < nowMonth) {
					JOptionPane.showMessageDialog(rootPane, "不可预定过期车票.");
					return;
				}else if (day > nowDay+1 || month > nowMonth) {
					JOptionPane.showMessageDialog(rootPane, "仅可预订两天内车票.");
					return;
				}
				queryRemainTickets(new Date(nowYear-1900, month-1, day));
				bookingTable.setModel(remainTicketModel);
				bookingTable.getColumn("预订").setCellRenderer(new BookButtonRenderer());
				bookingTable.getColumn("预订").setCellEditor(new BookButtonEditor(pService));
			}
		});
		queryTicketBtn.setBounds(220, 24, 93, 23);
		bookingPanel.add(queryTicketBtn);
		
		bookingScrollPane = new JScrollPane();
		bookingScrollPane.setBounds(10, 70, 339, 372);
		bookingPanel.add(bookingScrollPane);
		
		bookingTable = new JTable();
		bookingTable.setShowVerticalLines(false);
		bookingTable.setFont(new Font("Century Gothic", Font.ITALIC, 12));
		bookingTable.setRowSelectionAllowed(false);
		bookingTable.setBorder(null);
		bookingTable.setBounds(0, 0, 1, 1);
		bookingScrollPane.setViewportView(bookingTable);
		bookingTable.setRowHeight(30);
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(SwingConstants.CENTER);   
		bookingTable.setDefaultRenderer(Object.class, r);
		
		cancelSeatPanel = new JPanel();
		tabbedPane.addTab("\u53D6\u6D88\u9910\u4F4D", null, cancelSeatPanel, null);
		cancelSeatPanel.setLayout(null);
		
		cancleSeatNameField = new JTextField();
		cancleSeatNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(cancleSeatNameField.getText().equals("\u8BF7\u8F93\u5165\u59D3\u540D")){
					cancleSeatNameField.setText("");
				}
			}
		});
		cancleSeatNameField.setText("\u8BF7\u8F93\u5165\u59D3\u540D");
		cancleSeatNameField.setBounds(50, 10, 134, 21);
		cancelSeatPanel.add(cancleSeatNameField);
		cancleSeatNameField.setColumns(10);
		
		cancleSeatPhoneField = new JTextField();
		cancleSeatPhoneField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cancleSeatPhoneField.getText().equals("\u8BF7\u8F93\u5165\u624B\u673A\u53F7")){
					cancleSeatPhoneField.setText("");
				}
			}
		});
		cancleSeatPhoneField.setText("\u8BF7\u8F93\u5165\u624B\u673A\u53F7");
		cancleSeatPhoneField.setBounds(50, 41, 134, 21);
		cancelSeatPanel.add(cancleSeatPhoneField);
		cancleSeatPhoneField.setColumns(10);
		
		cancleSeatQueryBtn = new JButton("\u67E5\u8BE2\u9910\u4F4D");
		cancleSeatQueryBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String name = cancleSeatNameField.getText();
				String phoneNo = cancleSeatPhoneField.getText();
				if(!judgePhoneNo(phoneNo)) {
					JOptionPane.showMessageDialog(rootPane, "电话号码无效");
					return;
				}
				queryBookedSeat(name, phoneNo);
				cancleSeatTable.setModel(cancelSeatModel);
				cancleSeatTable.getColumn("餐位").setCellRenderer(new CancelSeatButtonRenderer());
				cancleSeatTable.getColumn("餐位").setCellEditor(new CancelSeatButtonEditor(pService, name, phoneNo));
				
			}
		});
		cancleSeatQueryBtn.setBounds(220, 24, 93, 23);
		cancelSeatPanel.add(cancleSeatQueryBtn);
		
		cancleSeatScrollPane = new JScrollPane();
		cancleSeatScrollPane.setBounds(10, 70, 339, 372);
		cancelSeatPanel.add(cancleSeatScrollPane);
		
		cancleSeatTable = new JTable();
		cancleSeatTable.setRowSelectionAllowed(false);
		cancleSeatTable.setShowVerticalLines(false);
		cancleSeatTable.setFont(new Font("Century Gothic", Font.ITALIC, 12));
		cancleSeatTable.setBounds(0, 0, 1, 1);
		cancleSeatScrollPane.setViewportView(cancleSeatTable);
		cancleSeatTable.setRowHeight(30);
		DefaultTableCellRenderer cancleSeatTableRendererTmp   = new DefaultTableCellRenderer();   
		cancleSeatTableRendererTmp.setHorizontalAlignment(SwingConstants.CENTER);   
		cancleSeatTable.setDefaultRenderer(Object.class, cancleSeatTableRendererTmp);
	}
	
	public TiksysClientMainInterface(TicketService pservice){
		this();
		this.pService = pservice;
	}
	
	public void queryRemainTickets(Date date) {
		ArrayList<RemainTicketRecord> remainTicketRecords = (ArrayList<RemainTicketRecord>) JSON.parseArray(pService.query(date.getTime()),RemainTicketRecord.class);
		String[] columnNames = {"时间",
                "余票",
                "预订"};
        Object[][] data = new Object[remainTicketRecords.size()][3];
        for(int i=0; i<remainTicketRecords.size(); i++){
        	Timestamp timestamp = remainTicketRecords.get(i).getTime();
        	String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
        	String remainTicketNum = String.valueOf(remainTicketRecords.get(i).getRemainTicketNum());
        	boolean bookable = true;
        	if(remainTicketRecords.get(i).getRemainTicketNum() <= 0){
        		bookable = false;
        	}
        	data[i][0] = time;
        	data[i][1] = remainTicketNum;
        	data[i][2] = bookable;
        }
        remainTicketModel.setDataVector(data, columnNames);
	}
	
	public boolean bookTicket(Timestamp time, String name, String phoneNo) {
		return pService.book(time.getTime(), name, phoneNo);
	}
	
	public void queryBookedSeat(String name, String phoneNo) {
		ArrayList<BookedSeatRecord> bookedSeatRecords = (ArrayList<BookedSeatRecord>) JSON.parseArray(pService.queryBookedSeat(name, phoneNo), BookedSeatRecord.class);
		//DEBUG
		for(int i=0; i< bookedSeatRecords.size(); i++) {
			System.out.println(bookedSeatRecords.get(i).getTime().toString()
					+ " " + bookedSeatRecords.get(i).getName()
					+ " " + bookedSeatRecords.get(i).getPhoneNo()
					+ " " + bookedSeatRecords.get(i).getHavaMeal());
		}
		
		String[] columnNames = {
				"时间",
				"姓名",
				"餐位"
		};
		Object[][] data = new Object[bookedSeatRecords.size()][3];
		for(int i=0; i<bookedSeatRecords.size(); i++) {
			Timestamp timestamp =bookedSeatRecords.get(i).getTime();
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
			boolean haveMeal = bookedSeatRecords.get(i).getHavaMeal();
			data[i][0]=time;
			data[i][1]=name;
			data[i][2]=haveMeal;
		}
		cancelSeatModel.setDataVector(data, columnNames);
	}
	
	public boolean cancelMeal(Timestamp time, String name, String phoneNo) {
		return pService.cancelMeal(time.getTime(), name, phoneNo);
	}
	
	private boolean judgePhoneNo(String phoneStr) {
		if(phoneStr.length() != 11){
			return false;
		}else if (phoneStr.charAt(0) != '1') {
			return false;
		}
		return true;
	}
	
}
