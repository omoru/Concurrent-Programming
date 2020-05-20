package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import client_src.Client;
import msg_src.MsgAñadirArchivo;
import msg_src.MsgPedirFichero;

public class UsersTable extends JPanel {
	JTable table;
	public UsersTable(Client ctrlClient) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(	BorderFactory.createLineBorder(Color.black, 2), "Info",TitledBorder.LEFT
		,TitledBorder.TOP)); 
		
		UsersTableModel users_table = new UsersTableModel(ctrlClient);
		this.table = new JTable(users_table);
		add(new JScrollPane(table));
		this.setMaximumSize(new Dimension(800,500));
		this.setPreferredSize(new Dimension(800,500));
		
		table.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent me) {
	        	
	            JTable table2 = (JTable) me.getSource();
	            Point p = me.getPoint();
	            int row = table2.rowAtPoint(p);
	            String filename=(String) table2.getValueAt(row, 0);
	            int n = JOptionPane.showConfirmDialog(null, "¿Descargar archivo "+filename+"?","DESCARGA",JOptionPane.YES_NO_OPTION);
	            if (n == JOptionPane.YES_OPTION) {
	            	ctrlClient.sendMensaje(new MsgPedirFichero(ctrlClient.get_idUsuario(),ctrlClient.getIP(),
	            			ctrlClient.getIP_HOST(), filename));
	            }
	                  
	        }
	    });  
		
	}
	
	
}

