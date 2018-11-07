
package Control;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionFactory {
    
    
    public ConnectionFactory() {
	
    }

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/qualidade";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection() throws SQLException {

		try {
			Class.forName(DRIVER);
			
                        System.out.println("Conectado!!!");
			
                        return DriverManager.getConnection(URL, USER, PASS);
		
                } catch (Exception e) {
			// TODO: handle exception
                       // System.out.println("ytuyiuo");
			throw new SQLException(e.getMessage());
		}
	}


    
}

    
    

