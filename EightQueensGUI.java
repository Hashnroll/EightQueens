package eight_queens;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EightQueensGUI {

	private JFrame frame;
	private JTable table;
	
	private solveEightQueens solver;
	private ArrayList<Integer> queenPositions = new ArrayList<Integer>();
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EightQueensGUI window = new EightQueensGUI();
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
	public EightQueensGUI() {
		initialize();
		solver = new solveEightQueens();
	}
	
	class ChessRenderer extends DefaultTableCellRenderer
	{
	    
	   ImageIcon icon = null;    
	   
	   public ChessRenderer() {
	      setOpaque(true);
	      icon = new ImageIcon(getClass().getResource("queen1.png"));
	   }
	     
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      
	      if ((row+column)%2!=0) setBackground( new Color(148,58,23) );
	      else setBackground( new Color(233,158,103) );
	      
	      if (queenPositions.size()>0 && queenPositions.get(column)!=-1 && row == queenPositions.get(column)) setIcon(icon);
	      else setIcon(null);
	      
	      setHorizontalAlignment(JLabel.CENTER);
	      
	      return this;
	   }
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 1034, 704);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//table initialization
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		
		table.setBounds(22, 33, 600, 600);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowHeight(75);
	
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setMinWidth(50);
		
		for (int i=0;i<8;i++) {
			TableColumn tm = table.getColumnModel().getColumn(i);
			tm.setCellRenderer(new ChessRenderer());
		}
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(table);
		
		JButton solveBtn = new JButton("\u0412\u044B\u0447\u0438\u0441\u043B\u0438\u0442\u044C");
		solveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				queenPositions = new ArrayList<Integer>(solver.getSolution());
				textField_1.setText(solver.getSolution().toString());
				table.repaint();
			}
		});
		solveBtn.setFocusPainted(false);
		solveBtn.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		solveBtn.setBounds(690, 319, 131, 50);
		frame.getContentPane().add(solveBtn);
		
		JCheckBox checkBox = new JCheckBox("\u041F\u043E\u0448\u0430\u0433\u043E\u0432\u044B\u0439 \u0440\u0435\u0436\u0438\u043C");
		checkBox.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		checkBox.setBounds(690, 198, 209, 42);
		frame.getContentPane().add(checkBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(676, 454, 145, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
