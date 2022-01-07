package db2023.gui;

import db2023.backEnd.Book;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class ModifyExistingBooks {
    @FXML
    public RadioButton rb1;
    public RadioButton rb2;
    public Label l1;
    public Label l2;
    public ChoiceBox<String> c1, c2;

    private int srcIndex, destIndex;
    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Book> filrtedBooks = new ArrayList<Book>();


    private String errorMessage;

       private void fillChoiceBox(int bool) {
           //load all books
           for (Book book : books) {
               if (bool==1) {
                   c1.getItems().add(String.valueOf(book.getISBN()));
                   c2.getItems().add(book.getTitle());
               }
               else {
                   c1.getItems().add(String.valueOf(book.getCategory()));
                   c2.getItems().add("P:  "+book.getPublisher());
                   ArrayList<String> authors ;
                   authors=book.getAuthor();
                   for (String author : authors){
                       c2.getItems().add("A:  "+author);
                   }
               }
           }

       }

    public void createListeners() {
        c1.setOnAction(e -> srcIndex = c1.getSelectionModel().getSelectedIndex());
        c2.setOnAction(e -> destIndex = c2.getSelectionModel().getSelectedIndex());
    }

    public void radioSelect(){
        if(rb1.isSelected()) {
            l1.setText("ISBN");
            l2.setText("Title");
            fillChoiceBox(1);

        }
        else if(rb2.isSelected()) {
            l1.setText("Category");
            l2.setText("author/publisher");
            fillChoiceBox(2);
        }
        else{
            setErrorMessage("Select your gender please!");
            throw new RuntimeException();
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
