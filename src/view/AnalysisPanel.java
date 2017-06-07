/**
 * Matthew Phillips/Vidal Sisneros
 * 12 May 2017
 * AnalysiPanel.java
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Consumable;
import model.ContractorProfile;
import model.Material;

/**
 * The AnalysisPanel extends DIYPanel and allows you to view the data in
 * the database and refresh that data.
 * 
 * @author Vidal Sisneros/Matthew Phillips
 * @version 12 May 2017
 */
public class AnalysisPanel extends DIYPanel {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 413750924644714309L;
	
	private JTable myMaterialsTable;
	
	private JTable myConsumablesTable;
	
	private JTable myContractorsTable;
	
	/**
	 * Constructs and returns a new AnaylsisPanel.
	 * @param theGUI is our GUI.
	 */
	public AnalysisPanel(final GUI theGUI) {
		super(theGUI);
		
		myMaterialsTable = new JTable();
		myMaterialsTable.setModel(createMaterialsTable());
		myConsumablesTable = new JTable();
		myConsumablesTable.setModel(createConsumablesTable());
		myContractorsTable = new JTable();
		myContractorsTable.setModel(createContractorsTable());
		
		init();	 
	}
	
	
	/**
	 * Initializes the actual panel and sets up tables.
	 */
	private void init(){
		
		// Call create table for all 3 tables.
		// Create JComponents for combo box and table.
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		String[] types = {"Materials", "Consumables", "Contractors"};
		
		layout.putConstraint(SpringLayout.WEST, myMaterialsTable,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, myMaterialsTable,
                180,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, myConsumablesTable,
                372,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, myConsumablesTable,
                180,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, myContractorsTable,
                700,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, myContractorsTable,
                180,
                SpringLayout.NORTH, this);
		
		add(myMaterialsTable);
		add(myConsumablesTable);
		add(myContractorsTable);
		
		JButton refresh = new JButton("Refresh");
		
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				myMaterialsTable.setModel(createMaterialsTable());
				myConsumablesTable.setModel(createConsumablesTable());
				myContractorsTable.setModel(createContractorsTable());
			}
			
		});
		
		layout.putConstraint(SpringLayout.WEST, refresh,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, refresh,
                103,
                SpringLayout.NORTH, this);
		
		add(refresh);
	}
	
	/**
	 * Initializes the actual panel.
	 */
	protected TableModel createMaterialsTable() {
		String[] myColumnNames = {"Name","Unit", "Cost", "Depot"};
		Material[] materialsList = null;
		File directory = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "materials");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int counter = 0;
        materialsList = new Material[fList.length];
        
        for (File file : fList){
            if (file.isFile()){
                //System.out.println(file.getName());
                Material mat = null;
                try {
                	String target = file.getName().replaceAll(".ser", "");
					mat = Material.importMaterial(target);
					materialsList[counter] = mat;
					counter++; 
					
				} catch (ClassNotFoundException | IOException e) {
					
					e.printStackTrace();
				} 
            }
        }
        
        Object[][] myData = new Object[materialsList.length][4];
        
        for (int i = 0; i < materialsList.length; i++) {
        	myData[i][0] = materialsList[i].getMyName();
        	myData[i][1] = materialsList[i].getMyUnit();
        	myData[i][2] = materialsList[i].getMyCost();
        	myData[i][3] = materialsList[i].getMyDepot();
        }
        
        for (Object[] o : myData) {
        	System.out.println(Arrays.toString(o));
        }
        
        TableModel m = new DefaultTableModel(myData, myColumnNames);
		return m;
	}
	
	/**
	 * Creates consumables table to display on the panel.
	 */
	protected TableModel createConsumablesTable() {
		String[] myColumnNames = {"Name", "Unit", "Cost", "Supplier"};
		Consumable[] consumablesList = null;
		File directory = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "consumables");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int counter = 0;
        consumablesList = new Consumable[fList.length];
        
        for (File file : fList){
            if (file.isFile()){
                //System.out.println(file.getName());
                Consumable mat = null;
                try {
                	String target = file.getName().replaceAll(".ser", "");
					mat = Consumable.importConsumable(target);
					consumablesList[counter] = mat;
					counter++; 
					
				} catch (ClassNotFoundException | IOException e) {
					
					e.printStackTrace();
				} 
            }
        }
        
        Object[][] myData = new Object[consumablesList.length][4];
        
        for (int i = 0; i < consumablesList.length; i++) {
        	myData[i][0] = consumablesList[i].getMyName();
        	myData[i][1] = consumablesList[i].getMyUnit();
        	myData[i][2] = consumablesList[i].getMyCost();
        	myData[i][3] = consumablesList[i].getMySupplier();
        }
        
        for (Object[] o : myData) {
        	System.out.println(Arrays.toString(o));
        }
        
        TableModel m = new DefaultTableModel(myData, myColumnNames);
		return m;
	}

	/**
	 * Creates contractors table to display on the panel.
	 */
	protected TableModel createContractorsTable() {
		String[] myColumnNames = {"Name", "Reputation"};
		ContractorProfile[] contractorsList = null;
		File directory = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "contractors");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int counter = 0;
        contractorsList = new ContractorProfile[fList.length];
        
        for (File file : fList){
            if (file.isFile()){
                ContractorProfile mat = null;
                try {
                	String target = file.getName().replaceAll(".ser", "");
					mat = ContractorProfile.importContractor(target);
					contractorsList[counter] = mat;
					counter++; 
					
				} catch (ClassNotFoundException | IOException e) {
					
					e.printStackTrace();
				} 
            }
        }
        
        Object[][] myData = new Object[contractorsList.length][2];
        
        for (int i = 0; i < contractorsList.length; i++) {
        	myData[i][0] = contractorsList[i].getMyName();
        	myData[i][1] = contractorsList[i].getMyReputation();
        }
        
        for (Object[] o : myData) {
        	System.out.println(Arrays.toString(o));
        }
        
        TableModel m = new DefaultTableModel(myData, myColumnNames);
		return m;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;   
        
		g.setColor(Color.WHITE);
		g.setFont(DIYPanel.HEADER_FONT);
		g.drawString("My Resources", 50, 75);
		
		g.setColor(Color.YELLOW);
		g.setFont(DIYPanel.MSG_FONT);
		g.drawString("Materials", 50, 170);
		g.drawString("Consumables", 372, 170);
		g.drawString("Contractors", 700, 170);
	}
}
