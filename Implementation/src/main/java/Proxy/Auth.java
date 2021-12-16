package Proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Auth {
	private static Connection connect=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;
	private static String dataBaseName="airline";
	private static String  url="jdbc:mysql://127.0.0.1:3306/airline";
	private static String userName="root";
	private static String password="1111";
	private static final String QUERY = "SELECT Email FROM user where Email=";

	
	@SuppressWarnings("deprecation")
	public Auth() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
	}
	
	
	public static boolean checkVaildEmail(String Email) throws SQLException {
		resultSet= statement.executeQuery(QUERY+"'"+Email+"'");
		if(resultSet.next()==false) {
			return true;
		}
		 while(resultSet.next()){
	            //Display values
	            System.out.print("ID: " + resultSet.getString("Email"));
	         }
		
		return false;
	}
	
	public static boolean addNewUser() throws SQLException {
		connect.prepareStatement("INSERT INTO User (Name, Gender, isAdmin,Email,Password)"
				+ "VALUES  ('abdelrahman mohamed', 'm', 1,'sakr@gmail.com','123456789');").executeUpdate();
		System.out.println("sakr");
		return false;
	}
	
	
	public static void connect() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		  // Setup the connection with the DB
		try {
			connect =  (Connection) DriverManager
			      .getConnection(url, userName, password);
			statement=connect.createStatement();
			if(connect!=null) {
				System.out.println(" connection");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		connect();
		checkVaildEmail("sakr@gmail.com");
	}
}
