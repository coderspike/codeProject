package j2se.Java8;

public class Book {
    private String name;
    private String bookStrore;
    private String bookWriter;
    private Double Price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookStrore() {
        return bookStrore;
    }

    public void setBookStrore(String bookStrore) {
        this.bookStrore = bookStrore;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Book(String name, String bookStrore, String bookWriter, Double price) {
        this.name = name;
        this.bookStrore = bookStrore;
        this.bookWriter = bookWriter;
        Price = price;
    }
}
