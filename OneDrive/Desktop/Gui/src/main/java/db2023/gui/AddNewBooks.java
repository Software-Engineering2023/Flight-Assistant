package db2023.gui;

import db2023.backEnd.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddNewBooks {
    @FXML
    public TextField ISBN;
    public TextField Title;
    public TextField Publisher;
    public TextField PublicationYear;
    public TextField Category;
    public TextField SellingPrice;
    public TextField copies;
    public TextField Threshold;
    public TextField Authors;
    public Button b1;
    public Button b2;
    public Button b3;
    public Label addedAuthors;
    public ListView<String> listView;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<String> authors = new ArrayList<>();

    public void addBook(){
        Book book = new Book(Title.getText(),authors,Publisher.getText(),PublicationYear.getText(),Category.getText(),Float.parseFloat(SellingPrice.getText()),Integer.parseInt(copies.getText()),Integer.parseInt(Threshold.getText()));
        books.add(book);
    }

    public void AddmoreAuthors(){
        authors.add(Authors.getText());
        String allAuthors = addedAuthors.getText()+","+Authors.getText();
        addedAuthors.setText(allAuthors);
        Authors.clear();
    }

    public void addMoreBooks(){
        addBook();
        updateListView();
        ISBN.clear();
        Title.clear();
        Publisher.clear();
        PublicationYear.clear();
        Category.clear();
        SellingPrice.clear();
        copies.clear();
        Threshold.clear();
        addedAuthors.setText(" ");


    }

    public void updateListView(){
        listView.getItems().clear();
        for(Book i : books)
            listView.getItems().add(i.getTitle());
        listView.getItems().removeAll();
    }

    public void Done(){
        HelloApplication.showWindow(b3, "Managerhome.fxml", "Administrator", 950,650);
    }





}
