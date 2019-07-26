import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    static String conStr = "jdbc:sqlserver://localhost:1433;databaseName=forJava;user=janfranco;password=nightmare97";
    private Connection conn = null;
    
    private ArrayList<Worker> workers = new ArrayList<Worker>();
    private ArrayList<String> id_list = new ArrayList<String>();
    private ArrayList<String> pwd_list = new ArrayList<String>();

    public ArrayList<String> getId_list() {
        return id_list;
    }

    public void setId_list(ArrayList<String> id_list) {
        this.id_list = id_list;
    }

    public ArrayList<String> getPwd_list() {
        return pwd_list;
    }

    public void setPwd_list(ArrayList<String> pwd_list) {
        this.pwd_list = pwd_list;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    public Database() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(conStr);
            System.out.println("Bağlantı başarılı");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class bulunamadı...");
        } catch (SQLException ex) {
            System.out.println("Bağlantı başarısız...");
        }
    }
    
    public void listQuery(){
        try {
            String query = "SELECT * FROM calisanlar";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                
                workers.add(new Worker(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(1)));
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int Query(String query){
        try {
            Statement statement = conn.createStatement();
            int value = statement.executeUpdate(query);
            return value;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return -1;
    }
    
    public void login() throws SQLException{
        
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM adminler");
        while(resultSet.next()){
            
            String id = resultSet.getString(1);
            String pwd = resultSet.getString(2);
            
            id_list.add(id);
            pwd_list.add(pwd);
            
        }
        
    }
    
}
