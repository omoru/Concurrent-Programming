package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import client_src.Client;
import msg_src.MsgPedirFichero;

public class DownloadsTable extends JPanel {
	JTable table;
	public DownloadsTable(Client ctrlClient) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(	BorderFactory.createLineBorder(Color.black, 2), "Info",TitledBorder.LEFT
		,TitledBorder.TOP)); 
		
		DownloadsTableModel download_table = new DownloadsTableModel(ctrlClient);
		this.table = new JTable(download_table);
		add(new JScrollPane(table));
		this.setMaximumSize(new Dimension(800,200));
		this.setPreferredSize(new Dimension(800,200));
		
	}

}
