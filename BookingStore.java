import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " by " + author + " ($" + price + ")";
    }
}

public class BookstoreGUI extends JFrame {
    private ArrayList<Book> inventory = new ArrayList<>();
    private JTextField titleField, authorField, priceField;
    private JTextArea bookListArea;

    public BookstoreGUI() {
        setTitle("Bookstore");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Book Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        JButton addButton = new JButton("Add Book");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

      
        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        add(new JScrollPane(bookListArea), BorderLayout.CENTER);

      
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        setVisible(true);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        double price;

        try {
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a number!");
            return;
        }

        Book book = new Book(title, author, price);
        inventory.add(book);
        updateBookList();

    
        titleField.setText("");
        authorField.setText("");
        priceField.setText("");
    }

    private void updateBookList() {
        bookListArea.setText("");
        for (Book b : inventory) {
            bookListArea.append(b.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        new BookstoreGUI();
    }
}