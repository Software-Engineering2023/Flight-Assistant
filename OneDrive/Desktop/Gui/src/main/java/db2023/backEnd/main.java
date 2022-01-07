package db2023.backEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class main {
	static String url="jdbc:mysql://127.0.0.1:3306/bookStrore";
	static String userName="root";
	static String password="5622192";
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connect =  (Connection) DriverManager
			      .getConnection(url, userName, password);
        String query ="insert into Book_publisher values(?, ?, ?)";
        PreparedStatement pStatement = connect.prepareStatement(query);
        pStatement.setString(1, "sssssss");
        pStatement.setString(2,"ln");
        pStatement.setString(3,"0102222220000");
        pStatement.execute();
        pStatement.close();


	}
	
	
	public static boolean addBook(Book book) {
		try {
		String query = "insert into Book(Book_ISBN,Title,Publisher_Name,Publication_Year,Selling_Price,Category,Copies,Threashold) values(default, ?, ?, ?,?,?,?,?)";
		String queryForAuthor = "insert into Book_author(Book_ISBN,Book_Author) values(?,?)";
		Connection connect =  (Connection) DriverManager
			      .getConnection(url, userName, password);
		PreparedStatement pStatement = connect.prepareStatement(query);
		pStatement.setString(1, book.getTitle());
		pStatement.setString(2, book.getPublisher());
		pStatement.setString(3, book.getPublicationYear());
		pStatement.setFloat(4, book.getSellingPrice());
		pStatement.setString(5, book.getCategory());
		pStatement.setInt(6, book.getCopies());
		pStatement.setInt(7, book.getThreashold());
		pStatement.execute();
		
		PreparedStatement pStatementForAuthor = connect.prepareStatement(queryForAuthor);
		ArrayList<String>authors=book.getAuthor();
		for(String author:authors) {
			pStatementForAuthor.setInt(1, book.getISBN());
			pStatementForAuthor.setString(2, author);
		}
		
		}
		
		catch(Exception e) {
			return false;
		}
		return true;
		
	}
	
	public static Book getBookByID(int  id) {
		String query="select * from Book where "+
				" Title="+id;
		String queryForAuthor="select * from Book_author where "+
				" Book_ISBN="+id;
		Book book = null;
		try {
			Connection connect =  (Connection) DriverManager
				      .getConnection(url, userName, password);
			Statement stmt=connect.createStatement();  
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				book=new Book(rs.getString("Title"),rs.getString("Publisher_Name"),rs.getString("Publication_Year"),rs.getString("Category"),rs.getFloat("Selling_Price"),rs.getInt("Copies"),rs.getInt("Threashold"));
				book.setISBN(rs.getInt("Book_ISBN"));
				
			}
			rs=stmt.executeQuery(queryForAuthor);
			ArrayList<String> authors=new ArrayList();
			while (rs.next()) {
				authors.add(rs.getString("Book_Author"));
			}
			book.setAuthor(authors);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
	}
	
	public static ArrayList<Book> getBookByTitle(String  Title) {
		String query="select * from Book where "+
				" Title="+Title;
		String queryForAuthor="select * from Book_author where "+
				" Book_ISBN= ?";
		ArrayList<Book> books = null;
		Book book;
		try {
			Connection connect =  (Connection) DriverManager
				      .getConnection(url, userName, password);
			Statement stmt=connect.createStatement();  
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				book=new Book(rs.getString("Title"),rs.getString("Publisher_Name"),rs.getString("Publication_Year"),rs.getString("Category"),rs.getFloat("Selling_Price"),rs.getInt("Copies"),rs.getInt("Threashold"));
				book.setISBN(rs.getInt("Book_ISBN"));
				PreparedStatement pStatementForAuthor = connect.prepareStatement(queryForAuthor);
				pStatementForAuthor.setInt(1, rs.getInt("Book_ISBN"));
            	ResultSet rsForAuthor = pStatementForAuthor.executeQuery();
            	ArrayList<String>authors=new ArrayList<String>();
            	while(rsForAuthor.next()) {
            		authors.add(rsForAuthor.getString("Book_Author"));
            	}
            	book.setAuthor(authors);
            	

				
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return books;
	}
	
	public static ArrayList<Book> getBookByCategory(String  Category) {
		String query="select * from Book where "+
				" Category="+Category;
		String queryForAuthor="select * from Book_author where "+
				" Book_ISBN= ?";
		ArrayList<Book> books = null;
		Book book;
		try {
			Connection connect =  (Connection) DriverManager
				      .getConnection(url, userName, password);
			Statement stmt=connect.createStatement();  
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				book=new Book(rs.getString("Title"),rs.getString("Publisher_Name"),rs.getString("Publication_Year"),rs.getString("Category"),rs.getFloat("Selling_Price"),rs.getInt("Copies"),rs.getInt("Threashold"));
				book.setISBN(rs.getInt("Book_ISBN"));
				PreparedStatement pStatementForAuthor = connect.prepareStatement(queryForAuthor);
				pStatementForAuthor.setInt(1, rs.getInt("Book_ISBN"));
            	ResultSet rsForAuthor = pStatementForAuthor.executeQuery();
            	ArrayList<String>authors=new ArrayList<String>();
            	while(rsForAuthor.next()) {
            		authors.add(rsForAuthor.getString("Book_Author"));
            	}
            	book.setAuthor(authors);
            	

				
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return books;
	}
	
	public static ArrayList<Book> getBookByPublisher_Name(String  Publisher_Name) {
		String query="select * from Book where "+
				" Category="+Publisher_Name;
		String queryForAuthor="select * from Book_author where "+
				" Book_ISBN= ?";
		ArrayList<Book> books = null;
		Book book;
		try {
			Connection connect =  (Connection) DriverManager
				      .getConnection(url, userName, password);
			Statement stmt=connect.createStatement();  
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				book=new Book(rs.getString("Title"),rs.getString("Publisher_Name"),rs.getString("Publication_Year"),rs.getString("Category"),rs.getFloat("Selling_Price"),rs.getInt("Copies"),rs.getInt("Threashold"));
				book.setISBN(rs.getInt("Book_ISBN"));
				PreparedStatement pStatementForAuthor = connect.prepareStatement(queryForAuthor);
				pStatementForAuthor.setInt(1, rs.getInt("Book_ISBN"));
            	ResultSet rsForAuthor = pStatementForAuthor.executeQuery();
            	ArrayList<String>authors=new ArrayList<String>();
            	while(rsForAuthor.next()) {
            		authors.add(rsForAuthor.getString("Book_Author"));
            	}
            	book.setAuthor(authors);
            	

				
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return books;
	}
	
	public static boolean updateBook (int Book_ISBN,int Copies) {
		String query="UPDATE Book "
				+ "SET Copies = "+Copies
				+ " WHERE Book_ISBN= "+Book_ISBN;
		Connection connect;
		try {
			connect = (Connection) DriverManager
				      .getConnection(url, userName, password);
			Statement stmt=connect.createStatement();  
			ResultSet rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
		
	}
	
	public static  void connect() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		  // Setup the connection with the DB
		
		
		try {
			
			Connection connect =  (Connection) DriverManager
			      .getConnection(url, userName, password);
			if(connect!=null) {
				System.out.println(" connection");
			}
			Statement stmt=connect.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Book_publisher");
			while (rs.next()) {
				System.out.println(rs.getString("Address"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
