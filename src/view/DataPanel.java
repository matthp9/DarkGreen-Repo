/**
 * Jasvir Dosanjh
 * 12 May 2017
 * DataPanel.java
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.Consumable;
import model.ContractorProfile;
import model.Material;

/**
 * The DataPanel extends DIYPanel and allows you to either save, update, or delete
 * data in the database by updating or creating .ser files.
 * 
 * @author Jasvir Dosanjh
 * @version 12 May 2017
 */
public class DataPanel extends DIYPanel {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 5689541567595912264L;
	
	public static final String[] TYPES = {"Material", "Consumable", "Contractor"};
	
	private String myMessageToUser;

	/**
	 * Constructs and returns a new DataPanel.
	 */
	public DataPanel(final GUI theGUI) {
		super(theGUI);
		myMessageToUser = "";
		init();
	}
	
	/**
	 * Initializes DataPanel and data
	 */
	private void init() { 
		
		final JLabel typeLabel = new JLabel("Item Type");
		final JComboBox type = new JComboBox(TYPES);
		
		type.setPreferredSize(new Dimension(200, 30));
		type.setFont(DIYPanel.MSG_FONT);
		typeLabel.setForeground(Color.WHITE);
		typeLabel.setFont(DIYPanel.MSG_FONT);
		
		add(typeLabel);
		add(type);
		
		final JLabel nameLabel = new JLabel("Name");
		final JTextField name = new JTextField();

		name.setColumns(20);
		name.setPreferredSize(new Dimension(200, 30));
		name.setFont(DIYPanel.MSG_FONT);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(DIYPanel.MSG_FONT);
		
		add(nameLabel);
		add(name);
		
		final JLabel unitLabel = new JLabel("Unit");
		final JTextField unit = new JTextField();
		
		unit.setColumns(20);
		unit.setPreferredSize(new Dimension(200, 30));
		unit.setFont(DIYPanel.MSG_FONT);
		unitLabel.setForeground(Color.WHITE);
		unitLabel.setFont(DIYPanel.MSG_FONT);
		
		add(unitLabel);
		add(unit);

		final JLabel costLabel = new JLabel("Cost/Unit");
		final JTextField cost = new JTextField();
		
		cost.setColumns(20);
		cost.setPreferredSize(new Dimension(200, 30));
		cost.setFont(DIYPanel.MSG_FONT);
		costLabel.setForeground(Color.WHITE);
		costLabel.setFont(DIYPanel.MSG_FONT);
		
		add(costLabel);
		add(cost);
		
		final JLabel depotLabel = new JLabel("Depot");
		final JTextField depot = new JTextField();
		
		depot.setColumns(20);
		depot.setPreferredSize(new Dimension(200, 30));
		depot.setFont(DIYPanel.MSG_FONT);
		depotLabel.setForeground(Color.WHITE);
		depotLabel.setFont(DIYPanel.MSG_FONT);
		
		add(depotLabel);
		add(depot);

		final JLabel supplierLabel = new JLabel("Supplier");
		final JTextField supplier = new JTextField();
		
		supplier.setColumns(20);
		supplier.setPreferredSize(new Dimension(200, 30));
		supplier.setFont(DIYPanel.MSG_FONT);
		supplierLabel.setForeground(Color.WHITE);
		supplierLabel.setFont(DIYPanel.MSG_FONT);
		
		add(supplierLabel);
		add(supplier);
		
		final JLabel reputationLabel = new JLabel("Reputation");
		final JTextField reputation = new JTextField();
		
		reputation.setColumns(20);
		reputation.setPreferredSize(new Dimension(200, 30));
		reputation.setFont(DIYPanel.MSG_FONT);
		reputationLabel.setForeground(Color.WHITE);
		reputationLabel.setFont(DIYPanel.MSG_FONT);
		
		add(reputationLabel);
		add(reputation);
		
		supplier.setEnabled(false);
		reputation.setEnabled(false);
		supplier.setBackground(Color.GRAY);
		reputation.setBackground(Color.GRAY);

		type.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent e) {
				System.out.println(e.getItem().toString());
				switch (e.getItem().toString()) {
					case "Material":
						unit.setEnabled(true);
						cost.setEnabled(true);
						depot.setEnabled(true);
						unit.setBackground(Color.WHITE);
						cost.setBackground(Color.WHITE);
						depot.setBackground(Color.WHITE);
						supplier.setEnabled(false);
						reputation.setEnabled(false);
						supplier.setBackground(Color.GRAY);
						reputation.setBackground(Color.GRAY);
						break;

					case "Consumable":
						supplier.setEnabled(true);
						unit.setEnabled(true);
						cost.setEnabled(true);
						supplier.setBackground(Color.WHITE);
						unit.setBackground(Color.WHITE);
						cost.setBackground(Color.WHITE);
						depot.setEnabled(false);
						reputation.setEnabled(false);
						depot.setBackground(Color.GRAY);
						reputation.setBackground(Color.GRAY);
						break;
						
					case "Contractor":
						reputation.setEnabled(true);
						reputation.setBackground(Color.WHITE);
						unit.setEnabled(false);
						cost.setEnabled(false);
						depot.setEnabled(false);
						supplier.setEnabled(false);
						unit.setBackground(Color.GRAY);
						cost.setBackground(Color.GRAY);
						depot.setBackground(Color.GRAY);
						supplier.setBackground(Color.GRAY);
						break;
				}
			}
		});
		
		final JButton save   = new JButton("Save/Update");
		final JButton delete = new JButton("Delete");
		
		add(save);
		add(delete);
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final String key = type.getSelectedItem().toString();
				final String i_name = name.getText();
				boolean flag = true;
				if (key.equals("Material")) {
					final String i_unit = unit.getText();
					double i_cost = 0;
					try {
						i_cost = Double.parseDouble(cost.getText());
					} catch (Exception e) {
						myMessageToUser = "Invalid cost.";
						repaint();
						flag = false;
					}
					final String i_depot = depot.getText();
					Material m = new Material(i_name, i_unit, i_cost, i_depot);
					try {
						if (flag) {
							m.exportMaterial(i_name); //"db/materials/" + i_name + ".ser"
							myMessageToUser = "Saved " + i_name + " to materials!";
						}
					} catch (IOException e) {
						myMessageToUser = "Could not export.";
						repaint();
						flag = false;
					}
				} else if (key.equals("Consumable")) {
					final String i_supplier = supplier.getText();
					final String i_unit = unit.getText();
					double i_cost = 0;
					try {
						i_cost = Double.parseDouble(cost.getText());
					} catch (Exception e) {
						myMessageToUser = "Invalid cost.";
						repaint();
						flag = false;
					}
					Consumable c = new Consumable(i_name, i_unit, i_cost, i_supplier);
					try {
						if (flag) {
							c.exportConsumable(i_name);
							myMessageToUser = "Saved " + i_name + " to consumables!";
						}
					} catch (IOException e) {
						myMessageToUser += "Could not export";
						repaint();
					}
				} else if (key.equals("Contractor")) {
					int i_reputation = 0;
					try {
						i_reputation = Integer.parseInt(reputation.getText());
					} catch (Exception e) {
						myMessageToUser = "Invalid reputation.";
						repaint();
						flag = false;
					}
					ContractorProfile c = new ContractorProfile(i_name,"");
					c.setMyReputation(i_reputation);
					try {
						if (flag) {
							c.exportContractor(i_name);
							myMessageToUser = "Saved " + i_name + " to contractors!";
						}
					} catch (IOException e) {
						myMessageToUser = "Could not export.";
						repaint();
					}
				}
				repaint();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final String key = type.getSelectedItem().toString();
				final String i_name = name.getText();
				if (key.equals("Material")) {
					File file = new File(System.getProperty("user.home") + File.separator + "Desktop"
			        		+ File.separator + "db" + File.separator + "materials" + File.separator
			        		+ i_name + ".ser");
					if (file.delete()) {
						myMessageToUser = "Deleted.";
					}
				} else if (key.equals("Consumable")) {
					File file = new File(System.getProperty("user.home") + File.separator + "Desktop"
			        		+ File.separator + "db" + File.separator + "consumables" + File.separator
			        		+ i_name + ".ser");
					if (file.delete()) {
						myMessageToUser = "Deleted.";
					}
				} else if (key.equals("Contractor")) {
					File file = new File(System.getProperty("user.home") + File.separator + "Desktop"
			        		+ File.separator + "db" + File.separator + "contractors" + File.separator
			        		+ i_name + ".ser");
					if (file.delete()) {
						myMessageToUser = "Deleted.";
					}
				}
				repaint();
			}
		});
		
		final JLabel help1 = new JLabel("Use this page to edit your DIY items.");
		final JLabel help2 = new JLabel("Enter the required fields, then press");
		final JLabel help3 = new JLabel("'Save' to store the item, or press");
		final JLabel help4 = new JLabel("'Delete' to remove it if it exists.");

		help1.setFont(MSG_FONT);
		help1.setForeground(Color.WHITE);
		add(help1);
		help2.setFont(MSG_FONT);
		help2.setForeground(Color.WHITE);
		add(help2);
		help3.setFont(MSG_FONT);
		help3.setForeground(Color.WHITE);
		add(help3);
		help4.setFont(MSG_FONT);
		help4.setForeground(Color.WHITE);
		add(help4);
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		layout.putConstraint(SpringLayout.WEST, typeLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, typeLabel,
                120,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, type,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, type,
                120,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, nameLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nameLabel,
                160,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, name,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, name,
                160,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, unitLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, unitLabel,
                260,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, unit,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, unit,
                260,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, costLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, costLabel,
                300,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, cost,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, cost,
                300,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, depotLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, depotLabel,
                380,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, depot,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, depot,
                380,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, supplierLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, supplierLabel,
                460,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, supplier,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, supplier,
                460,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, reputationLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, reputationLabel,
                560,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, reputation,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, reputation,
                560,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, save,
                550,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, save,
                120,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, delete,
                565,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, delete,
                160,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help1,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help1,
                360,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help2,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help2,
                385,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help3,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help3,
                410,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help4,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help4,
                435,
                SpringLayout.NORTH, this);
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
        g.drawString("Enter, Update, or Remove My Data", 50, 75);
        
        g.setColor(Color.YELLOW);
        g.setFont(DIYPanel.MSG_FONT);
        g.drawLine(50, 255, 375, 255);
        g.drawString("If Material or Consumable, please enter:", 50, 250);
        g.drawLine(50, 375, 255, 375);
        g.drawString("If Material, please enter:", 50, 370);
        g.drawLine(50, 455, 280, 455);
        g.drawString("If Consumable, please enter:", 50, 450);
        g.drawLine(50, 555, 280, 555);
        g.drawString("If Contractor, please enter:", 50, 550);
        
        g.setColor(Color.YELLOW);
        g.drawString(myMessageToUser, 470, 300);
	}
}
