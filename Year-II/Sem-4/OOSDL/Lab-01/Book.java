// 1. Create a Book class with private data members including book ID, book title, author name, price, and availability status. Provide public setter methods to assign values to these data members and public getter methods to retrieve their values. Include validation in setter methods to ensure that the price is a positive value.

public class Book {
    private int bookID, price;
    private String title, author;
    private boolean available;
    
    public Book(int bookID, int price, String title, String author, boolean availabile) {
        this.bookID=bookID;
        setPrice(price);
        this.title=title;
        this.author=author;
        this.available=availabile;
    }

    // Setter methods

    void setBookID(int bookID) {
        this.bookID=bookID;
    } 

    void setPrice(int price) {
        if (price > 0) {
            this.price=price;
        } else {
            System.out.println("Invalid price. Must be positive.");
        }
    }
    
    void setTitle(String title) {
        this.title=title;
    }

    void setAuthor(String author) {
        this.author=author;
    }

    void setAvail(Boolean available) {
        this.available=available;
    }

    // Getter methods

    int getBookID() {
       return bookID;
    } 

    int getPrice() {
        return price;
    }
    
    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    String getAvail() {
        if (available) {
            return "yes";
        } else {
            return "no";
        }
    }

    public static void main(String[] args) {
        Book b = new Book(101, 100, "Atomic Habits", "James Clear", true);

        System.out.println("Displaying Book Data:\n");
        System.out.println("Book ID: " + b.bookID);
        System.out.println("Book Price: " + b.price);
        System.out.println("Book Title: " + b.title);
        System.out.println("Author Name: " + b.author);
        System.out.println("Available: " + b.getAvail());
    }
}