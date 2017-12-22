package com.nyu.tiksysclient.main;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.nyu.tiksysclient.services.TicketService;
import com.nyu.tiksysclient.ui.TiksysClientMainInterface;

public class TiksysClientMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {  
            URL url= new URL("http://wiebo.net:8899/tiksys/webservice?wsdl");  
            // Qname qname是qualified name 的简写  
            // 2.构成：由名字空间(namespace)前缀(prefix)以及冒号(:),还有一个元素名称构成   
            QName sname = new QName("http://servicesImpl.tiksys.nyu.com/","TicketServiceImplService");  
            Service service = Service.create(url, sname);  
            // 获取Service对应的port,从wsdl中获取的  
            QName pname = new QName("http://servicesImpl.tiksys.nyu.com/", "TicketServiceImplPort");  
            TicketService pservice = service.getPort(pname, TicketService.class); 
            TiksysClientMainInterface tiksysClientMainInterface = new TiksysClientMainInterface(pservice);
    		tiksysClientMainInterface.setVisible(true);
        } catch (MalformedURLException e) { 
        	JOptionPane.showMessageDialog(null, "Error: Cannot reach the service.");
            e.printStackTrace();  
        }  
	}

}
