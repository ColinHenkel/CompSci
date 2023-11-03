import java.util.*;

public class HW {
	public static void main(String[] args) {
		
	}
}

abstract class Book {
	private String author, title, isbn;

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public Book() {
		author = title = isbn = "EMPTY";
	}
	public Book(String author, String title, String isbn) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
	}
	abstract public String getBookType();
	
	public String toString() {
		return isbn + "-" + title.toUpperCase() + " by " + author.toUpperCase();
	}
}

final class BookstoreBook extends Book {
	private double price, saleRate;
	private boolean onSale;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}
	public boolean isOnSale() {
		return onSale;
	}
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}
	
	public BookstoreBook() {
		super();
		price = saleRate = 0.0;
		onSale = false;
	}
	public BookstoreBook(String author, String title, String isbn, double price, double saleRate, boolean onSale) {
		super(author, title, isbn);
		this.price = price;
		this.saleRate = saleRate;
		this.onSale = onSale;
	}

	@Override
	public String getBookType() {
		return "Bookstore Book.";
	}
	
	@Override
	public String toString() {
		if(onSale)
			return "[" + super.toString() + " - $" + String.format("%.2f",price) + " listed for $" + String.format("%.2f",price - ((saleRate * price))) + "]";
		else
			return "[" + super.toString() + " , $" + String.format(".2f",price) + "]";
	}
}

final class LibraryBook extends Book {
	private String Subject, callNumber;
	
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	
	public LibraryBook() {
		super();
		Subject = callNumber = "EMPTY";
	}
	public LibraryBook(String author, String title, String isbn, String Subject, String callNumber) {
		super(author, title, isbn);
		this.Subject = Subject;
		this.callNumber = callNumber;
	}

	@Override
	public String getBookType() {
		return "Library Book";
	}
}
