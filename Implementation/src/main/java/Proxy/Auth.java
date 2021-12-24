package Proxy;

import com.swe2023.Proxy.DB_Utils;
import com.swe2023.model.signUpAndLogin.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;



public class Auth {
	private static Connection connect=null;
	private static Statement statement=null;
	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet = null;

	private static final String QUERYOfCheckAdmin = "SELECT "+ User.DB_isAdmin+" FROM "+User.DB_TABLE_NAME+" WHERE "+
			User.DB_EMAIL+" = ";
	private static final String QUERYOfgetPassword = "SELECT "+ User.DB_PASSWORD+" FROM "+User.DB_TABLE_NAME+" WHERE "+
			User.DB_EMAIL+" = ";
	private static final String QUERYOfgetVaildEmail = "SELECT "+ User.DB_EMAIL+" FROM "+User.DB_TABLE_NAME+" WHERE "+
			User.DB_EMAIL+" = ";
	private static final String QUERYOfgetUser = "SELECT * FROM "+User.DB_TABLE_NAME+" WHERE "+
			User.DB_EMAIL+" = ";



	//	@SuppressWarnings("deprecation")
	public Auth() throws Exception {
		
	}
///////////////////////////////////function to check if it is admin or passenger//////////////	
	public static String[] checkAdmin(String Email) throws SQLException {
		//checker string with index to check the first index is admin or not 
		String[] checker = new String[]{"",""};
		Connection connection = DB_Utils.getDataSource().getConnection();//start connection 
        PreparedStatement pStatement = connection.prepareStatement(QUERYOfCheckAdmin+"'"+Email+"'");//statement to get the result 
        resultSet=pStatement.executeQuery();//result of the result
        while(resultSet.next()){
            //Display values
        	if(resultSet.getString("isAdmin").equals("1")) {
        		checker[0]="true"; //return true if admin
        	}else {
        		checker[0]="false";
        	}
        	
        }
        /////////////get password of the email////////////////
        pStatement= connection.prepareStatement(QUERYOfgetPassword+"'"+Email+"'");
        resultSet=pStatement.executeQuery();
        while(resultSet.next()){
            //Display values
        	checker[1]=resultSet.getString("Password");
        }
        ///////////close connection 
        pStatement.close();
        connection.close();
        if(checker[0].equals("")&&checker[1].equals("")) {
        	checker[0]="-1";
        	return checker;
        }
        return checker;//return result if he id admin or not and password of him 
	}
	/////////////////////////check valid Email to sign up new user///////////////////////
	public static boolean checkValidEmail(String Email) throws SQLException {
		resultSet = statement.executeQuery(QUERYOfgetVaildEmail+"'"+Email+"'");
		if(!resultSet.next()) {
			return true;
		}
		 while(resultSet.next()){
	            //Display values
	            System.out.print("ID: " + resultSet.getString("Email"));
	     }
		 resultSet.close();
		return false;
	}
////////////////////////////add new passenger//////////////////////////////
	public static boolean addNewUser(User user) throws SQLException {
		 String query = "insert into User values(?, ?, ?, ?, ?)";
	        try {
	        	//////////make the connection
	            Connection connection = DB_Utils.getDataSource().getConnection();
	            PreparedStatement pStatement = connection.prepareStatement(query);
	            pStatement.setString(1, user.getEmail());
	            pStatement.setString(2, user.getGender());
	            pStatement.setBoolean(3, user.getAdmin());
	            pStatement.setString(4, user.getEmail());
	            pStatement.setString(5, user.getPassword());
	            pStatement.execute();
	            pStatement.close();
	            connection.close();
	        }catch(Exception e) {
	        	
	        }
		return true;
	}
	////////////////////////get the user and fill it //////////////////////
	public static User getUser(String Email) throws SQLException {
		User user = null;
		/////////////////////////start connection///////////////
		Connection connection = DB_Utils.getDataSource().getConnection();
        PreparedStatement pStatement = connection.prepareStatement(QUERYOfgetUser+"'"+Email+"'");
        resultSet=pStatement.executeQuery();

        /////////////fill the user attribute here /////////////////
        while(resultSet.next()){
            //Display values
			boolean isAdmin= resultSet.getString("isAdmin").equals("1") ;
        	user = new User(resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Gender"),isAdmin);
        	user.setID(resultSet.getInt("Id"));
        }
        /////////////////////close the connection /////////////////
		resultSet.close();
		pStatement.close();
		connection.close();
		return user;///return the user 
		
	}



	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		//connect();
	}


}
