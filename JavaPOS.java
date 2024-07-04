package pointOfSale;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;

public class JavaPOS {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTextField jtxtTax;
	private JTextField jtxtSubTotal;
	private JTextField jtxtTotal;
	private JTextField jtxtDisplay;
	private JTextField jtxtChange;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaPOS window = new JavaPOS();
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
	public JavaPOS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void ItemCost()
	{
		double sum = 0;
		double tax = 2.5;
		
		for (int i= 0; i < table.getRowCount(); i++)
		{sum = sum + Double.parseDouble(table.getValueAt(i, 2).toString());
	}
		jtxtSubTotal.setText(Double.toString(sum)); 
		double cTotal = Double.parseDouble(jtxtSubTotal.getText());
		
		double ctax =(cTotal * tax)/100; 
		String iTaxTotal = String.format("Ksh %.2f", ctax);
		jtxtTax.setText(iTaxTotal);
		
		String iSubTotal= String.format("Ksh %.2f", cTotal);
		jtxtSubTotal.setText(iSubTotal); 
		
		String iTotal = String.format("Ksh %.2f", cTotal + ctax);
		jtxtTotal.setText(iTotal);
	}
	
	 public void Change()
	 {
		 double sum = 0;
		 double tax = 2.5;
		 double Cash = Double.parseDouble(jtxtDisplay.getText());
		 
		 for ( int i = 0; i < table.getRowCount(); i++ )
		 {
			 sum = sum + Double.parseDouble(table.getValueAt(i, 2).toString());
		 }
		 double cTax = (tax * sum)/100;
		 double cChange = (Cash - (sum + cTax));
		 String ChangeGiven = String.format("Ksh %.2f", cChange); 
		 jtxtChange.setText(ChangeGiven);
	 }
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1650, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 411, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* jtxtSearch = new JTextField();
				jtxtSearch.setBounds(10, 10, 506, 34);

				jbtnSearch = new JButton("Search");
				jbtnSearch.setBounds(526, 10, 100, 34);

				jbtnSearch.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        // Get search query from text field
				        String query = jtxtSearch.getText();

				        // Create prepared statement
				        PreparedStatement pstmt = null;
				        try {
				            pstmt = conn.prepareStatement("SELECT * FROM items WHERE serial_number LIKE? OR bar_code_number LIKE? OR item_name LIKE?");
				            pstmt.setString(1, "%" + query + "%");
				            pstmt.setString(2, "%" + query + "%");
				            pstmt.setString(3, "%" + query + "%");
				        } catch (SQLException ex) {
				            System.err.println("Error preparing statement: " + ex.getMessage());
				            return;
				        }

				        // Execute query and get results
				        ResultSet results = null;
				        try {
				            results = pstmt.executeQuery();
				        } catch (SQLException ex) {
				            System.err.println("Error executing query: " + ex.getMessage());
				            return;
				        }

				        // Check if item is already in the table
				        boolean itemExists = false;
				        int rowIndex = -1;
				        for (int i = 0; i < jTable1.getRowCount(); i++) {
				            if (jTable1.getValueAt(i, 0).equals(results.getString("serial_number"))) {
				                itemExists = true;
				                rowIndex = i;
				                break;
				            }
				        }

				        // Add item to table if it doesn't exist, or update quantity if it does
				        try {
				            if (itemExists) {
				                int quantity = (int) jTable1.getValueAt(rowIndex, 3) + 1;
				                jTable1.setValueAt(quantity, rowIndex, 3);
				            } else {
				                jTable1.addRow(new Object[] {
				                    results.getString("serial_number"),
				                    results.getString("bar_code_number"),
				                    results.getString("item_name"),
				                    1,
				                    results.getDouble("price")
				                });
				            }
				        } catch (SQLException ex) {
				            System.err.println("Error processing results: " + ex.getMessage());
				            return;
				        }

				        // Close prepared statement and result set
				        try {
				            pstmt.close();
				            results.close();
				        } catch (SQLException ex) {
				            System.err.println("Error closing resources: " + ex.getMessage());
				        }
				    }
				});*/
				double PriceOfItem = 180;
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				boolean itemExists = false;
				int rowIndex = -1;
				for (int i = 0; i < model.getRowCount(); i++) {
					if ("Sugar".equals(model.getValueAt(i, 0))) {
						itemExists = true;
						rowIndex = i;
						break;
					}
				}

				if (itemExists) {
					
					int quantity = (int) model.getValueAt(rowIndex, 1) + 1;
					double amount = quantity * PriceOfItem;
					model.setValueAt(quantity, rowIndex, 1);
					model.setValueAt(amount, rowIndex, 2);
				} else {
					
					model.addRow(new Object[]{"Sugar", 1, PriceOfItem});
				}

				ItemCost();  
			}
			
		});
		jbtnSearch.setBounds(431, 11, 89, 28);
		frame.getContentPane().add(jbtnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1153, 980);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Quantity", "Amount"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(1177, 56, 447, 218);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 136, 56);
		panel.add(lblNewLabel);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubTotal.setBounds(10, 92, 136, 49);
		panel.add(lblSubTotal);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(10, 158, 136, 49);
		panel.add(lblTotal);
		
		jtxtTax = new JTextField();
		jtxtTax.setBounds(156, 15, 281, 56);
		panel.add(jtxtTax);
		jtxtTax.setColumns(10);
		
		jtxtSubTotal = new JTextField();
		jtxtSubTotal.setColumns(10);
		jtxtSubTotal.setBounds(156, 92, 281, 56);
		panel.add(jtxtSubTotal);
		
		jtxtTotal = new JTextField();
		jtxtTotal.setColumns(10);
		jtxtTotal.setBounds(156, 158, 281, 56);
		panel.add(jtxtTotal);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(1177, 285, 447, 218);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Method");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 11, 177, 47);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Display Cash");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 73, 133, 47);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Change");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(10, 148, 133, 47);
		panel_1.add(lblNewLabel_1_2);
		
		JComboBox jcboPayment = new JComboBox();
		jcboPayment.setModel(new DefaultComboBoxModel(new String[] {"", "Cash", "Card", "Mobile Money"}));
		jcboPayment.setBounds(197, 11, 240, 47);
		panel_1.add(jcboPayment);
		
		jtxtDisplay = new JTextField();
		jtxtDisplay.setBounds(153, 69, 284, 51);
		panel_1.add(jtxtDisplay);
		jtxtDisplay.setColumns(10);
		
		jtxtChange = new JTextField();
		jtxtChange.setColumns(10);
		jtxtChange.setBounds(153, 148, 284, 51);
		panel_1.add(jtxtChange);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(1177, 512, 447, 218);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton jbtnReset = new JButton("Reset");
		jbtnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtxtDisplay.setText(null);
				jtxtChange.setText(null);
				jtxtTax.setText(null);
				jtxtSubTotal.setText(null);
				
				  DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
				  RecordTable.setRowCount(0);
			}
		});
		jbtnReset.setFont(new Font("Tahoma", Font.BOLD, 19));
		jbtnReset.setBounds(10, 11, 131, 98);
		panel_2.add(jbtnReset);
		
		JButton jbtnPay = new JButton("Pay");
		jbtnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jcboPayment.getSelectedItem().equals("Cash"))
				{
					Change();
				}
				else 
				{
					jtxtChange.setText("");
					jtxtDisplay.setText("");
				}
			}
		});
		jbtnPay.setFont(new Font("Tahoma", Font.BOLD, 19));
		jbtnPay.setBounds(151, 11, 131, 98);
		panel_2.add(jbtnPay);
		
		JButton jbtnExit = new JButton("Exit");
		jbtnExit.setFont(new Font("Tahoma", Font.BOLD, 19));
		jbtnExit.setBounds(10, 120, 131, 87);
		panel_2.add(jbtnExit);
		
		JButton jbtnPrint = new JButton("Print");
		jbtnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer");
			
			try
			{
				table.print(JTable.PrintMode.NORMAL,header,footer);
			}
			
			catch(java.awt.print.PrinterException ex)
			{
				System.err.format("No Printer Found", ex.getMessage());
			}
			}
		});
		jbtnPrint.setFont(new Font("Tahoma", Font.BOLD, 19));
		jbtnPrint.setBounds(151, 120, 131, 87);
		panel_2.add(jbtnPrint);
		
		JButton jbtnRemove = new JButton("Remove Item");
		jbtnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int RemoveItem = table.getSelectedRow();
				if (RemoveItem >=0)
				{
					model.removeRow(RemoveItem);
				}
				
				ItemCost();
				
				if (jcboPayment.getSelectedItem().equals("Cash"))
				{
					Change();
				}
				else 
				{
					jtxtChange.setText("");
					jtxtDisplay.setText("");
				}
			}
		});
		jbtnRemove.setFont(new Font("Tahoma", Font.BOLD, 17));
		jbtnRemove.setBounds(282, 64, 165, 104);
		panel_2.add(jbtnRemove);
	}
}
