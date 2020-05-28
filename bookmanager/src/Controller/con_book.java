package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import DAO.*;
import DAO.impl.*;
import Util.*;
import Entity.*;



public class con_book
{
	Book b = new Book();
	BookDAO bd = new BookDAOImpl();
	UserBook ub = new UserBook();
	con_login cl = new con_login();
	UserBookDAO ubd = new UserBookDAOImpl(); 
	TypeDAO td = new TypeDAOImpl();
	
	
	public con_book() {
	
	}
	
	//表格中的编辑列
    private TableColumn<Book, Book> editCol = new TableColumn<>("操作");
    
    //表格中的删除列
    private TableColumn<Book, Book> delCol = new TableColumn<>("操作");
	
    //布局文件中的表格视图对象，用来显示数据库中读取的所有图书信息
	@FXML 
	public TableView<Book> booktable;
	
	//布局文件中的输入文本框对象，用来输入搜索关键词
	@FXML
	public TextField keywordsField;
	
	//布局文件中的下拉框组件对象，用来显示数据库中读取的所有图书类别
	@FXML
	public ComboBox<String> typeComboBox;
	
	//图书模型数据集合，可以实时相应数据变化，无需刷新
	private ObservableList<Book> bookdata = FXCollections.observableArrayList();
	
    //图书类型模型数据集合
    private ObservableList<String> typedata = FXCollections.observableArrayList();
   
    
	@FXML
	public void initialize() throws SQLException {//初始化图书浏览功能中的各个组件
        
		
		//调用显示所有图书的方法
		allbooks();
		
		//调用显示所有图书类型的方法
		allType();
		
		
        
	}
	@FXML
	public void search() throws SQLException {
		
		//获得输入的查询关键字
		String keyword = keywordsField.getText().trim();
		int j =0;
		List<Book> keyBook = bd.getBook(keyword);
		int sure = keyBook.size();
		if(sure==0) {//如果书库没有此书,则提示没有此书
			Dialog nob = new Dialog();
			nob.setTitle("提示");
			nob.setHeaderText("对不起，没有与'"+keyword+"'相关的图书");
			nob.getDialogPane().getButtonTypes().add(ButtonType.OK);
			nob.showAndWait();
		}
		else {
			booktable.getItems().removeAll(bookdata);//移除表中所有内容
			while(j<keyBook.size()) {
				Book boo = new Book(keyBook.get(j).getId(),
						keyBook.get(j).getName(),
						keyBook.get(j).getAuthor(),
						keyBook.get(j).getCategory(),
						keyBook.get(j).getReserve(),
						keyBook.get(j).getLocation()); 
				bookdata.add(boo);
				j++;
			}
			booktable.setItems(bookdata);
			booktable.refresh();
		}
		
	}
	
	public void allbooks() throws SQLException {//图书表格初始化方法
		List<Book> allBook = bd.selectAllBooks();
		bookdata.addAll(allBook);
		booktable.setItems(bookdata);
		
		//水平方向不显示滚动条,表格的列宽会均匀分布
		booktable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//编辑列的相关设置
		 editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	        editCol.setCellFactory(param -> new TableCell<Book, Book>() {
	            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
	            private final Button editButton = ComponentUtil.getButton("编辑", "blue-theme");

	            @Override
	            protected void updateItem(Book book, boolean empty) {
	                super.updateItem(book, empty);
	                if (book == null) {
	                    setGraphic(null);
	                    return;
	                }
	                setGraphic(editButton);
	                //点击编辑按钮，弹出窗口，输入需要修改的图书价格
	                editButton.setOnAction(event -> {
	                    TextInputDialog dialog = new TextInputDialog("请输入库存量");
	                    dialog.setTitle("图书修改界面");
	                    dialog.setHeaderText("书名：《" + book.getName()+"》");
	                    dialog.setContentText("请输入新的库存:");
	                    Optional<String> result = dialog.showAndWait();
	                    //确认输入了内容，避免NPE
	                    if (result.isPresent()) {
	                        //获取输入的新价格并转化成Int数据
	                        String reserve = result.get();
	                        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	                        boolean num = pattern.matcher(reserve).matches();
	                        if(reserve.equals("")||num==false) {
	                        	Dialog wrchar = new Dialog();
	                        	wrchar.setTitle("提示");
	                        	wrchar.setHeaderText("请输入正确的库存量");
	                        	wrchar.getDialogPane().getButtonTypes().add(ButtonType.OK);
	                        	wrchar.showAndWait();
	                        }
	                        else
	                        {
	                        book.setReserve(Integer.parseInt(reserve));
	                        //更新图书信息
	                        try
							{
								bd.updateBook(book);
							} catch (SQLException e)
							{
								e.printStackTrace();
							}
	                        
	                        //更新提示
	                        Dialog res = new Dialog();
	                        res.setTitle("提示");
	                        res.setHeaderText("               《" + book.getName()+"》 库存量更新成功");
	                        res.getDialogPane().getButtonTypes().add(ButtonType.OK);
	                    	}
	                    }
	                });
	            }
	        });
        //将编辑列加入图书表格
        booktable.getColumns().add(editCol);
        
        //删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<Book, Book>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");
            
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("确定删除《" + book.getName()+"》吗？");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        bookdata.remove(book);
                        prompt();
                        try
						{
							bd.deleteBook(book.getId());
						} catch (SQLException e)
						{
							e.printStackTrace();
						}
                        
                    }
                	
                });
            }
        });
        //将归还列加入图书表格
        booktable.getColumns().add(delCol);
	}
	
	public void allType() throws SQLException {//图书类别下拉框初始化方法
		//类别集合，存放数据库类别表查询结果
	    List<String> alltype = td.getAllType();
	    typedata.addAll(alltype);
	    typeComboBox.setItems(typedata);
	    
	  //下拉框选择事件监听，根据选择不同的类别，图书表格中会过滤出该类别的图书
        typeComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    // System.out.println(newValue.getId() + "," + newValue.getTypeName());
                    //移除掉之前的数据
                    booktable.getItems().removeAll(bookdata);
                    //根据选中的类别查询该类别所有图书
                    try
					{
						List<Book> booklist = bd.getBooksByType(newValue);
						bookdata.addAll(booklist);
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
                    //重新显示数据
                    booktable.setItems(bookdata);
                    booktable.refresh();
                }
        );
	}
	public void prompt() {
  		Dialog prompt = new Dialog();
		prompt.setTitle("提示");
		prompt.setHeaderText("删除成功!");
		prompt.getDialogPane().getButtonTypes().add(ButtonType.OK);
		prompt.showAndWait();
  	}
	
	@FXML
	public void addbook() throws IOException{
		Stage addBookStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_book.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        con_addbook addboo = fxmlLoader.getController();
        addboo.setBookData(bookdata);
        addBookStage.setTitle("新增图书界面");
        addBookStage.getIcons().add(new Image("image/addbook.png"));
        //界面大小不可变
        addBookStage.setResizable(false);
        addBookStage.setScene(scene);
        addBookStage.show();
	}
	
	@FXML
	public void refresh() {
		booktable.refresh();
	}
	
	
	
}
