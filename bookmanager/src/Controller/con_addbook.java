package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import DAO.BookDAO;
import DAO.impl.BookDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Entity.*;


public class con_addbook implements Initializable {
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
    
	BookDAO bd = new BookDAOImpl();
	
	private ObservableList<Book> bookdata = FXCollections.observableArrayList();

    public ObservableList<Book> getBookData() {
        return bookdata;
    }

    public void setBookData(ObservableList<Book> bookdata) {
        this.bookdata = bookdata;
    }

    @FXML
    private TextField bookName, bookAuthor, bookType, bookReserve, bookLocation;
    
    @FXML
    private Button add;

    @FXML
    public void add_Book() throws SQLException {
        String name = bookName.getText();
        String author = bookAuthor.getText();
        String type = bookType.getText();
        String reserve = bookReserve.getText();
        String location = bookLocation.getText();
        
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean num = pattern.matcher(reserve).matches();
        if(reserve.equals("")||num==false||name.equals("")||author.equals("")||type.equals("")||location.equals("")) {
        	Dialog wrchar = new Dialog();
        	wrchar.setTitle("提示");
        	wrchar.setHeaderText("请输入正确的信息");
        	wrchar.getDialogPane().getButtonTypes().add(ButtonType.OK);
        	wrchar.showAndWait();
        }
        else 
        {
        int stock = Integer.parseInt(reserve);
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setCategory(type);
        book.setReserve(stock);
        book.setLocation(location);
        int id = bd.getBookID();
        book.setId(id+1);
        this.getBookData().add(book);
        bd.addBook(book);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增图书成功!");
        alert.showAndWait();
        Stage stage = (Stage)add.getScene().getWindow();
        stage.close();
        con_book cb = new con_book();
        
        }
        
    }

	
}
