package com.unknown.CAMP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class Viewer {

	private String filePath = "";
	private String fileName = "";
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewer window = new Viewer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Viewer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 220, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCampOrganizer = new JLabel("CAMP Organizer");
		lblCampOrganizer.setBounds(45, 12, 110, 15);
		frame.getContentPane().add(lblCampOrganizer);
		
		JLabel lblCabinMax = new JLabel("Cabin Max");
		lblCabinMax.setBounds(12, 91, 80, 15);
		frame.getContentPane().add(lblCabinMax);
		
		JLabel lblMaleCabins = new JLabel("Male Cabins");
		lblMaleCabins.setBounds(12, 145, 91, 15);
		frame.getContentPane().add(lblMaleCabins);
		
		JLabel lblFemaleCabins = new JLabel("Female Cabins");
		lblFemaleCabins.setBounds(12, 172, 110, 15);
		frame.getContentPane().add(lblFemaleCabins);
		
		JLabel lblSmallGroupAmt = new JLabel("Small Group Amt");
		lblSmallGroupAmt.setBounds(12, 118, 130, 15);
		frame.getContentPane().add(lblSmallGroupAmt);
		
		final JSpinner sp_cabinMax = new JSpinner();
		sp_cabinMax.setBounds(170, 89, 28, 20);
		frame.getContentPane().add(sp_cabinMax);
		
		final JSpinner sp_smallGroup = new JSpinner();
		sp_smallGroup.setBounds(170, 116, 28, 20);
		frame.getContentPane().add(sp_smallGroup);
		
		final JSpinner sp_maleCabins = new JSpinner();
		sp_maleCabins.setBounds(170, 143, 28, 20);
		frame.getContentPane().add(sp_maleCabins);
		
		final JSpinner sp_femaleCabins = new JSpinner();
		sp_femaleCabins.setBounds(170, 170, 28, 20);
		frame.getContentPane().add(sp_femaleCabins);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 12, 198, -10);
		frame.getContentPane().add(tabbedPane);
		
		JButton btnCreateCabins = new JButton("Generate List");
		btnCreateCabins.setBounds(45, 261, 130, 25);
		frame.getContentPane().add(btnCreateCabins);
		btnCreateCabins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileName = textField.getText();
				Manager addie = new Manager(null,(int)sp_cabinMax.getValue(),(int)sp_smallGroup.getValue(),(int)sp_maleCabins.getValue(),(int)sp_femaleCabins.getValue());
				try {
					addie.parseFile(filePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton = new JButton("Select File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				 JFileChooser chooser = new JFileChooser();
				 File file = null;
				 int returnValue = chooser.showOpenDialog( null ) ;
				 if( returnValue == JFileChooser.APPROVE_OPTION ) {
				        file = chooser.getSelectedFile() ;
				 }
				 if(file != null)
				 {
				    filePath = file.getPath();
				 } 
			}
		});
		btnNewButton.setBounds(45, 213, 127, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblCreatedByArthur = new JLabel("Created By Arthur Howard");
		lblCreatedByArthur.setFont(new Font("Dialog", Font.PLAIN, 8));
		lblCreatedByArthur.setBounds(96, 298, 110, 15);
		frame.getContentPane().add(lblCreatedByArthur);
		
		textField = new JTextField();
		textField.setBounds(118, 49, 80, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSessionName = new JLabel("Session Name");
		lblSessionName.setBounds(12, 51, 103, 15);
		frame.getContentPane().add(lblSessionName);
	}
}
