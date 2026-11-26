// 1.	Create a Book class with private data members including book ID, book title, author name, price, and availability status. Provide public setter methods to assign values to these data members and public getter methods to retrieve their values. Include validation in setter methods to ensure that the price is a positive value.

class Book {

    private int bookID;
    private String title;
    private String author;
    private double price;
    private boolean isAvailable;

    public Book (int bookID, String title, String author, double price, boolean isAvailable) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        setPrice(price);
        this.isAvailable = isAvailable;
    }

    public void setBook(int bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Price must be a positive value.");
        }
    }

    public void setAvail(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getBook() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String isAvail() {
        if (isAvailable) {
            return "Book is available.";
        } else {
            return "Book is not available.";
        }
    }

    public static void main(String[] args) {
        Book b = new Book(101,
            "Harry Potter and The Order of the Pheonix",
            "JK Rowling",
            500,
            true
        );

        System.out.println("Book ID: " + b.getBook());
        System.out.println("Title: " + b.getTitle());
        System.out.println("Author: " + b.getAuthor());
        System.out.println("Price: " + b.getPrice());
        System.out.println("Availability: " + b.isAvail());
    }
}