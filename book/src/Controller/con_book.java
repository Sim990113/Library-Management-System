package Controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
	public void search() throws SQLException {//根据关键字查找图书方法
		
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
		
		//获取系统当前日期
        Date date = new Date();
        //格式化时间
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        //设置当前日期
        String time= formatter.format(date);
        
        //设置截止日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        String deadline = formatter.format(cal.getTime());
        
		
		//水平方向不显示滚动条,表格的列宽会均匀分布
		booktable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//编辑列的相关设置
		editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editCol.setCellFactory(param -> new TableCell<Book, Book>() { 
        	//通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton("借阅", "blue-theme");

            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                
                
                //点击借阅按钮,弹出窗口,确认是否借阅
                editButton.setOnAction(event -> {
                	if(book.getReserve()==0) {
    					Dialog nobook = new Dialog();
                        nobook.setTitle("提示");
                        nobook.setHeaderText("库存为0!");
                        nobook.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        nobook.showAndWait();
                       
    				}
                	else {
                	Dialog dialog = new Dialog();
                    dialog.setTitle("确认订阅");
                    dialog.setHeaderText("               是否确认借阅《" + book.getName()+"》?");
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    Button ok = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
                    
                    
                    ok.setOnAction(new EventHandler<ActionEvent>()
					{
						
						@Override
						public void handle(ActionEvent event)
						{
							Dialog sure = new Dialog();
							sure.setTitle("提示");
							sure.setHeaderText("               成功借阅  《" + book.getName()+"》 ");
							sure.getDialogPane().getButtonTypes().add(ButtonType.OK);
							sure.showAndWait();
							
							//更新用户借阅图书
							ub.setStudent_id(cl.u.getUserid());
							ub.setBid(book.getId());
							ub.setBname(book.getName());
							ub.setDate(time);
							ub.setDeadline(deadline);
						
							//更新读者借阅图书数据
				
							
							UserBook ubook = new UserBook(ub.getStudent_id(),ub.getBid(),ub.getBname(),ub.getDate(),ub.getDeadline());
							
							try
							{
								ubd.addUserBook(ubook);
							} catch (SQLException e1)
							{
								e1.printStackTrace();
							}
							
							//更新书库数据
							try
							{	
								book.setReserve(book.getReserve()-1);
								bd.borrowBook(book.getReserve(),ub.getBid());
								booktable.refresh();
							} catch (SQLException e)
							{
								e.printStackTrace();
							}
								
						}
						
					});
                    
                    dialog.showAndWait();
                	}
                });
            }
        });
        //将编辑列加入图书表格
        booktable.getColumns().add(editCol);
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

	
}
