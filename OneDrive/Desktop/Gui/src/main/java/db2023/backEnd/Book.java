package db2023.backEnd;

import java.util.ArrayList;

public class Book {
	private int ISBN;
	private String title;
	private ArrayList<String>author;
	private String publisher;
	private String publicationYear;
	private String category;
	private float sellingPrice;
	private int Copies;
	private int Threashold;
	public Book(String title, ArrayList<String> author, String publisher, String publicationYear, String category,
			float sellingPrice, int copies, int threashold) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.category = category;
		this.sellingPrice = sellingPrice;
		Copies = copies;
		Threashold = threashold;
	}
	public Book(String title,  String publisher, String publicationYear, String category,
			float sellingPrice, int copies, int threashold) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.category = category;
		this.sellingPrice = sellingPrice;
		Copies = copies;
		Threashold = threashold;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getAuthor() {
		return author;
	}
	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getCopies() {
		return Copies;
	}
	public void setCopies(int copies) {
		Copies = copies;
	}
	public int getThreashold() {
		return Threashold;
	}
	public void setThreashold(int threashold) {
		Threashold = threashold;
	}
	
	
	
	
	
	
}
