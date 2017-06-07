/**
 * Vidal Sisneros
 * 12 May 2017
 * 
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
// import javax.swing.JComboBox;
import javax.swing.JLabel;
// import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.Calculation;
import model.Material;

public class CalculatorPanel extends DIYPanel {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -1025228578226982800L;

	/**
	 * Represents the total current calculation.
	 */
	private double myTotal;
	
	/**
	 * Error message 
	 */
	private String myMessageToUser;
	
	/**
	 * Stores the difference costs of a project.
	 */
	private final ArrayList<Calculation> myCalculations;
	
	/**
	 * Constructs and returns a new calculator panel.
	 */
	public CalculatorPanel(final GUI theGUI) {
		super(theGUI);
		myTotal = 0;
		myMessageToUser = "";
		myCalculations = new ArrayList<Calculation>();
		init();
	}
	
	/**
	 * Initializes the Calculator Panel components.
	 */
	private void init() {
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		JLabel materialsLabel = new JLabel("Material");
		JTextField material = new JTextField();
		material.setColumns(20);
		material.setPreferredSize(new Dimension(200, 30));
		material.setFont(DIYPanel.MSG_FONT);
		materialsLabel.setForeground(Color.WHITE);
		materialsLabel.setFont(DIYPanel.MSG_FONT);

		add(materialsLabel);
		add(material);

		JLabel amountLabel = new JLabel("Amount");
		JTextField amount = new JTextField();
		amount.setColumns(20);
		amount.setPreferredSize(new Dimension(200, 30));
		amount.setFont(DIYPanel.MSG_FONT);
		amountLabel.setForeground(Color.WHITE);
		amountLabel.setFont(DIYPanel.MSG_FONT);

		add(amountLabel);
		add(amount);

		JButton calculate = new JButton("Calculate");
		add(calculate);
		
		JButton addButton = new JButton("Add to Project");
		add(addButton);
		
		JButton clear = new JButton("Clear Project");
		add(clear);
		
		JButton exportProject = new JButton("Export Project (.txt)");
		add(exportProject);
		
		final JLabel help1 = new JLabel("Use this page to calculate project");
		final JLabel help2 = new JLabel("costs. Enter a material and the amount");
		final JLabel help3 = new JLabel("needed, then press calculate. You may");
		final JLabel help4 = new JLabel(" add calculations to a project text file.");

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
		
		layout.putConstraint(SpringLayout.WEST, materialsLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, materialsLabel,
                120,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, material,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, material,
                120,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, amountLabel,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, amountLabel,
                170,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, amount,
                150,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, amount,
                170,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, calculate,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, calculate,
                230,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, addButton,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addButton,
                270,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, clear,
                50,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, clear,
                370,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, exportProject,
                180,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, exportProject,
                370,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help1,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help1,
                110,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help2,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help2,
                135,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help3,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help3,
                160,
                SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, help4,
                470,
                SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, help4,
                185,
                SpringLayout.NORTH, this);
		
		calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				final String m_type = material.getText();
				
				Material m = null;
				try {
					m = Material.importMaterial(m_type);
					myMessageToUser = "";
				} catch (ClassNotFoundException e1) {
					myMessageToUser = "Nonexistent material.";
				} catch (IOException e2) {
					myMessageToUser = "Nonexistent material.";
				}
				
				if (m != null) {
					double m_costPerUnit = m.getMyCost();
					
					int m_num = 0;
					try {
						m_num = Integer.parseInt(amount.getText());
						myMessageToUser = "";
					} catch (Exception e) {
						myMessageToUser = "Invalid quantity.";
					}
					myTotal = m_num * m_costPerUnit; 
				}
				repaint();
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				final String m_type = material.getText();
				
				Material m = null;
				try {
					m = Material.importMaterial(m_type);
					myMessageToUser = "";
				} catch (ClassNotFoundException e1) {
					System.out.println("Class not found");
					myMessageToUser = "Nonexistent material.";
				} catch (IOException e2) {
					myMessageToUser = "Nonexistent material.";
				}
				
				if (m != null) {
					double m_costPerUnit = m.getMyCost();
					
					String m_depot = m.getMyDepot();
					int m_num = 0;
					
					try {
						m_num = Integer.parseInt(amount.getText());
						myMessageToUser = "";
					} catch (Exception e3) {
						myMessageToUser = "Invalid quantity.";
					}
					
					if (m_num > 0) {
						Calculation c = new Calculation(m_type, m_num, m.getMyCost(), m.getMyUnit(),
								m_num * m_costPerUnit, m_depot);
						
						myCalculations.add(c);
					} else {
						myMessageToUser = "Invalid quantity.";
					}
				}
				repaint();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myCalculations.clear();
				myTotal = 0;
				repaint();
			}
		});
		
		exportProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File(System.getProperty("user.home") + File.separator + "Desktop"
			        		+ File.separator + "db" + File.separator
			        		+ "project.txt");
				    PrintWriter writer = new PrintWriter(file);
				    
				    if (myCalculations.size() > 0) {
					    for (int i = 0; i < myCalculations.size(); i++) {
				        	
				        	Calculation c = myCalculations.get(i);
				        	
				        	String c_name = c.getMyMaterialName();
				        	int c_amnt    = c.getMyAmount();
				        	double c_cpun = c.getMyCostPerUnit();
				        	String c_unit = c.getMyUnit();
				        	double c_ttlc = c.getMyTotalCost();
				        	String c_depo = c.getMyDepot();
				        	
				        	StringBuilder sb = new StringBuilder();
				        	sb.append(c_amnt);
				        	sb.append("x ");
				        	sb.append(c_name);
				        	sb.append(" from ");
				        	sb.append(c_depo);
				        	sb.append(" @ $");
				        	sb.append(c_cpun);
				        	sb.append("/");
				        	sb.append(c_unit);
				        	sb.append(" for $");
				        	sb.append(c_ttlc);
				        	
				        	writer.println(sb.toString() + "\n");
				        }
				    }
				    writer.close();
				    myMessageToUser = "Successfully exported project.";
				} catch (IOException theException) {
				    myMessageToUser = "Could not export.";
				}
				repaint();
			}
		});
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
        g.drawString("Material Cost Calculator", 50, 75);
        
        g.setFont(DIYPanel.MSG_FONT);
        g.drawString(myMessageToUser, 175, 289);
        
        g.drawString("$" + myTotal, 150, 250);
        
        g.drawLine(50, 315, 500, 315);
        
        g.setColor(Color.YELLOW);
        g.setFont(DIYPanel.BOLD_FONT);
        g.drawString("My Project's Total Cost: $" + sumProject(), 50, 350);
        
        g.setColor(Color.WHITE);
        g.setFont(DIYPanel.MSG_FONT);
        
        int x = 50;
        int y = 420;
        
        for (int i = 0; i < myCalculations.size(); i++) {
        	
        	Calculation c = myCalculations.get(i);
        	
        	String c_name = c.getMyMaterialName();
        	int c_amnt    = c.getMyAmount();
        	double c_cpun = c.getMyCostPerUnit();
        	String c_unit = c.getMyUnit();
        	double c_ttlc = c.getMyTotalCost();
        	String c_depo = c.getMyDepot();
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append(c_amnt);
        	sb.append("x ");
        	sb.append(c_name);
        	sb.append(" from ");
        	sb.append(c_depo);
        	sb.append(" @ $");
        	sb.append(c_cpun);
        	sb.append("/");
        	sb.append(c_unit);
        	sb.append(" for $");
        	sb.append(c_ttlc);
        	
        	g.setColor(new Color(99, 128, 153));
        	g.drawLine(x, y, 600, y);
        	g.setColor(Color.WHITE);
        	g.drawString(sb.toString(), x, y + i * 25);
        }
	}

	/**
	 * @return the sum of all calculations
	 */
	private double sumProject() {
		double sum = 0;
		for (Calculation c : myCalculations) {
			sum += c.getMyTotalCost();
		}
		return sum;
	}
}
