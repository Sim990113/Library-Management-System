package Controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import DAO.*;
import DAO.impl.*;
import Util.*;
import Entity.*;

public class con_userbook
{	
	
	con_book cb = new con_book();
	UserBookDAO ubd = new UserBookDAOImpl();
	con_login cl = new con_login();
	UserDAO ud = new UserDAOImpl();
	
	public con_userbook() {
		
	}
	
	
	//表格中的编辑列
    private TableColumn<UserBook, UserBook> delCol = new TableColumn<>("操作");

    //布局文件中的表格视图对象，用来显示数据库中读取的所有用户图书信息
  	@FXML 
  	public TableView<UserBook> ubooktable;
  	
  	//图书模型数据集合，可以实时相应数据变化，无需刷新
  	private ObservableList<UserBook> ubookdata = FXCollections.observableArrayList();
  	
  	//布局文件中的输入文本框对象，用来输入搜索关键词
  	@FXML
  	public TextField keywordsField;
  	
  	
  	@FXML
	public void initialize() throws SQLException{
  		allubooks();
  	}
  	
  	public void allubooks() throws SQLException{
        
  		//水平方向不显示滚动条，表格的列宽会均匀分布
  		ubooktable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
  		
  		//调用查询读者借阅的图书的方法
  		List<UserBook> ubookList = ubd.selectUserBook();
  		ubookdata.addAll(ubookList);
  		ubooktable.setItems(ubookdata);
  		
  		
  		//归还列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<UserBook, UserBook>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");
            
            @Override
            protected void updateItem(UserBook userbook, boolean empty) {
                super.updateItem(userbook, empty);
                if (userbook == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
               
                    //点击了确认按钮，执行扣除罚款操作，同时移除一行模型数据
                        
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("确定删除《" + userbook.getBname()+"》吗？");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        ubookdata.remove(userbook);
                        prompt();
                        try
						{
							ubd.deleteUserBook(cl.u.getUserid(), userbook.getBid());
						} catch (SQLException e)
						{
							e.printStackTrace();
						}
                        
                    }
                	
                });
            }
        });
        //将归还列加入图书表格
        ubooktable.getColumns().add(delCol);
  	}
  	public void prompt() {
  		Dialog prompt = new Dialog();
		prompt.setTitle("提示");
		prompt.setHeaderText("删除成功!");
		prompt.getDialogPane().getButtonTypes().add(ButtonType.OK);
		prompt.showAndWait();
  	}
  	
  	@FXML
	public void search() throws SQLException {
		
		//获得输入的查询关键字
		String keyword = keywordsField.getText().trim();
		int j =0;
		List<UserBook> keyinfo=null;
		if(keyword.length()<15)
		{
		keyinfo = ubd.searchUserBookBybid(keyword);
		}
		else 
		{
		keyinfo = ubd.searchUserBook(keyword);
		}
		int sure = keyinfo.size();
		if(sure==0) {//如果数据库没有此信息,则提示没有此信息
			Dialog noinfo = new Dialog();
			noinfo.setTitle("提示");
			noinfo.setHeaderText("对不起，没有与'"+keyword+"'相关的信息");
			noinfo.getDialogPane().getButtonTypes().add(ButtonType.OK);
			noinfo.showAndWait();
		}
		else {
			ubooktable.getItems().removeAll(ubookdata);//移除表中所有内容
			while(j<keyinfo.size()) {
				UserBook ubook = new UserBook(keyinfo.get(j).getStudent_id(),keyinfo.get(j).getBid(),keyinfo.get(j).getBname(),keyinfo.get(j).getDate(),keyinfo.get(j).getDeadline()); 
				ubookdata.add(ubook);
				j++;
			}
			ubooktable.setItems(ubookdata);
			ubooktable.refresh();
		}
		
		
	}
}
