import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkerPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String conStr = "jdbc:sqlserver://localhost:1433;databaseName=forJava;user=janfranco;password=123456789";
    private Connection conn = null;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtSalary;
    private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerPanel frame = new WorkerPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void update_table() {
		lblNewLabel.setText("");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(conStr);
            String query = "SELECT * FROM dbo.workers";
            Statement statement;
            statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
	        while(resultSet.next()){
	            Object[] add = {resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)};
	            model.addRow(add);
	        }
        } catch (ClassNotFoundException ex) {
            lblNewLabel.setText("Error!");
        } catch (SQLException ex) {
            lblNewLabel.setText("SQL Error!");
        }
	}

	public WorkerPanel() {
		setResizable(false);
		setTitle("Worker Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Surname", "Department", "Salary"
			}
		));
		table.setBounds(10, 11, 516, 150);
		contentPane.add(table);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 171, 381, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnDeleteWorker = new JButton("Delete Worker");
		btnDeleteWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        int selectedRow = table.getSelectedRow();
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        if(selectedRow == -1){
		            if(table.getRowCount() == 0){
		                lblNewLabel.setText("Workers = 0");
		            }
		            else{
		                lblNewLabel.setText("Select a worker!");
		            }
		        }
		        else{
		        	String query = "DELETE FROM dbo.workers WHERE id = ";
		        	query += model.getValueAt(selectedRow, 0);
		        	Statement statement;
					try {
						statement = conn.createStatement();
						statement.executeUpdate(query);
						update_table();
					} catch (SQLException e) {
						lblNewLabel.setText("SQL Error!");
					}
		        }
			}
		});
		btnDeleteWorker.setBounds(401, 172, 125, 23);
		contentPane.add(btnDeleteWorker);
		
		txtName = new JTextField();
        txtName.setText("Name");
        txtName.setBounds(536, 14, 126, 20);
        contentPane.add(txtName);
        txtName.setColumns(10);
        
        txtSurname = new JTextField();
        txtSurname.setText("Surname");
        txtSurname.setBounds(536, 45, 126, 20);
        contentPane.add(txtSurname);
        txtSurname.setColumns(10);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"DEP1", "DEP2", "DEP3", "DEP4"}));
        comboBox.setBounds(536, 76, 126, 20);
        contentPane.add(comboBox);
        
        txtSalary = new JTextField();
        txtSalary.setText("Salary");
        txtSalary.setBounds(536, 107, 126, 20);
        contentPane.add(txtSalary);
        txtSalary.setColumns(10);
        
		JButton btnNewButton = new JButton("Add Worker");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO dbo.workers VALUES ('";
				query += txtName.getText() + "', '";
				query += txtSurname.getText() + "', '";
				query += comboBox.getSelectedItem() + "', ";
				query += Integer.valueOf(txtSalary.getText()) + ")";
				
				Statement statement;
				try {
					statement = conn.createStatement();
			        ResultSet res = statement.executeQuery(query);
				} catch (SQLException e) {
					lblNewLabel.setText("SQL Error!");
				}
				
				update_table();
				
			}
		});
		btnNewButton.setBounds(537, 138, 125, 23);
		contentPane.add(btnNewButton);
		
		JButton btnUpdateWorker = new JButton("Update Worker");
		btnUpdateWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        if(selectedRow != -1){
		        	String query = "UPDATE dbo.workers SET name = '";
					query += txtName.getText() + "', surname = '";
					query += txtSurname.getText() + "', department = '";
					query += comboBox.getSelectedItem() + "', salary = ";
					query += Integer.valueOf(txtSalary.getText()) + " WHERE id = ";
					query += model.getValueAt(selectedRow, 0) + ";";
					
					Statement statement;
					try {
						statement = conn.createStatement();
				        ResultSet res = statement.executeQuery(query);
					} catch (SQLException e) {
						lblNewLabel.setText("SQL Error!");
					}
					
					update_table();
		        } else {
		        	lblNewLabel.setText("Select a worker!");
		        }
			}
		});
		btnUpdateWorker.setBounds(537, 172, 125, 23);
		contentPane.add(btnUpdateWorker);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				txtName.setText((String) model.getValueAt(selectedRow, 1));
	            txtSurname.setText((String) model.getValueAt(selectedRow, 2));
			}
		});
		
        update_table();
        
	}
	
}
