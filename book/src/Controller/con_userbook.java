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
  	
  	
  	@FXML
	public void initialize() throws SQLException{
  		allubooks();
  	}
  	
  	public void allubooks() throws SQLException{
        
  		//水平方向不显示滚动条，表格的列宽会均匀分布
  		ubooktable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
  		
  		//调用查询读者借阅的图书的方法
  		List<UserBook> ubookList = ubd.selectUserBook(cl.u.getUserid());
  		ubookdata.addAll(ubookList);
  		ubooktable.setItems(ubookdata);
  		
  		Date date = new Date();//获取当前时间
  		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  		String now = formatter.format(date);
  		
  		
  		//归还列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<UserBook, UserBook>() {
            private final Button deleteButton = ComponentUtil.getButton("归还", "green-theme");
            
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
                	//计算时间差来计算罚款金额
                	if(userbook.getDeadline().compareTo(now)<0) {
              			
                		
              			Date deadline =null;
						try
						{
							deadline = formatter.parse(userbook.getDeadline());//将截止日期的字符串类型转换为date类型
						} catch (ParseException e)
						{
							e.printStackTrace();
						}
              			
						long nowdate = date.getTime();
              			long deadlinedate = deadline.getTime();
              			
              			int days = (int)((nowdate-deadlinedate) / (1000 * 60* 60 * 24));
              			
              			double fine = days * 0.3;
              			
              			double balance=cl.u.getUserbalance()-fine;
              			
              			if(balance<0) 
              			{
              				Dialog nomoney = new Dialog();
              				nomoney.setTitle("提示");
              				nomoney.setHeaderText("余额不足，请及时到管理处充值");
              				nomoney.getDialogPane().getButtonTypes().add(ButtonType.OK);
              				nomoney.showAndWait();
              			}
              			
              			else {
              			Alert pay = new Alert(Alert.AlertType.CONFIRMATION);
              			pay.setTitle("提示对话框");
              			pay.setHeaderText("您借阅的《" + userbook.getBname()+"》已超时"+days+"天，请缴纳罚款"+fine+"元来归还");
                        Optional<ButtonType> re = pay.showAndWait();
                        //点击了确认按钮，执行扣除罚款操作，同时移除一行模型数据
                        if (re.get() == ButtonType.OK) {
                            ubookdata.remove(userbook);
                            prompt();
                            try
    						{
    							ud.updateUserbalance(cl.u.getUserid(), balance);
                            	ubd.deleteUserBook(cl.u.getUserid(), userbook.getBid());
                            	cl.u.setUserbalance(balance);
    						} catch (SQLException e)
    						{
    							e.printStackTrace();
    						}
                            
                        }
                	}
              		}
                	else {
                	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("确定归还《" + userbook.getBname()+"》吗？");
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
		prompt.setHeaderText("归还成功!");
		prompt.getDialogPane().getButtonTypes().add(ButtonType.OK);
		prompt.showAndWait();
  	}
}
