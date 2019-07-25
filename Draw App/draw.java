import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.List;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String path;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		setResizable(false);
		setTitle("Draw");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List list = new List();
		list.setBounds(10, 10, 312, 137);
		contentPane.add(list);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 223, 312, 35);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("Draw!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(list.getItemCount() != 0) {
					Random random = new Random();
					int size = list.getItemCount();
					int winner = random.nextInt(size);
					label.setText("Winner is : " + list.getItem(winner));
					list.remove(winner);
				} else {
					label.setText("Please add people.");
				}
			
			}
		});
		btnNewButton.setBounds(223, 157, 99, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add People");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				int r = fc.showOpenDialog(null);
				
				if (r == JFileChooser.APPROVE_OPTION) { 
					path = fc.getSelectedFile().getAbsolutePath();
					
					try(Scanner scanner = new Scanner(new FileReader(path))){
						
						while(scanner.hasNextLine()) {
							
							list.add(scanner.nextLine());
							
						}
						
						
					} catch (IOException e) {
						System.out.println("Hata oluştu...");
					}
					
	            } 
				
			}
		});
		btnNewButton_1.setBounds(10, 157, 99, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				list.removeAll();
				label.setText("");
				
			}
		});
		btnClearList.setBounds(119, 157, 99, 23);
		contentPane.add(btnClearList);
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText() != "") {
					list.add(textField.getText());
					label.setText("");
				}
				else {
					label.setText("Empty text.");
				}
				
			}
		});
		btnNewButton_2.setBounds(119, 191, 99, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(list.getSelectedIndex() >= 0) {
					list.remove(list.getSelectedIndex());
					label.setText("");
				}
				else {
					label.setText("Empty list.");
				}
				
			}
		});
		btnRemove.setBounds(223, 191, 99, 23);
		contentPane.add(btnRemove);
		
		textField = new JTextField();
		textField.setBounds(10, 192, 99, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
