package Proxy;

import com.swe2023.model.signUpAndLogin.User;

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
	private static ResultSet resultSet = null;

	private static final String QUERY = "SELECT "+ User.DB_EMAIL+" FROM "+User.DB_TABLE_NAME+" WHERE "+
			User.DB_EMAIL+" = ";

	
	@SuppressWarnings("deprecation")
	public Auth() throws Exception {
		
	}
	
	
//	public static boolean checkValidEmail(String Email) throws SQLException {
//		resultSet = statement.executeQuery(QUERY+"'"+Email+"'");
//		if(!resultSet.next()) {
//			return true;
//		}
//		 while(resultSet.next()){
//	            //Display values
//	            System.out.print("ID: " + resultSet.getString("Email"));
//	         }
//
//		return false;
//	}
//
//	public static boolean addNewUser() throws SQLException {
//		connect.prepareStatement("INSERT INTO User (Name, Gender, isAdmin,Email,Password)"
//				+ "VALUES  ('abdelrahman mohamed', 'm', 1,'sakr@gmail.com','123456789');").executeUpdate();
//		System.out.println("sakr");
//		return false;
//	}
//
//
//	public static void connect() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		  // Setup the connection with the DB
//		try {
//
//			// connection  = DB_Utils.getDataSource().getConnection();
//
//			connect =  (Connection) DriverManager
//			      .getConnection(url, userName, password);
//			statement=connect.createStatement();
//			if(connect!=null) {
//				System.out.println(" connection");
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException("Failed to connect");
////			e.printStackTrace();
//		}
//
//	}
//	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		connect();
//		checkValidEmail("sakr@gmail.com");
//	}


}
