package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import DAO.*;
import DAO.impl.*;
import Util.*;
import Entity.*;

public class con_studenttable
{	
	
	con_book cb = new con_book();
	IStudentDAO isd = new StudentDAOImpl();
	con_login cl = new con_login();
	UserDAO ud = new UserDAOImpl();
	
	public con_studenttable() {
		
	}
	
	//表格中的编辑列
    private TableColumn<Student, Student> editCol = new TableColumn<>("操作");
	
	//表格中的删除列
    private TableColumn<Student, Student> delCol = new TableColumn<>("操作");
    
  //布局文件中的输入文本框对象，用来输入搜索关键词
  	@FXML
  	public TextField keywordsField;

    //布局文件中的表格视图对象，用来显示数据库中读取的所有用户图书信息
  	@FXML 
  	private TableView<Student> stutable;
  	
  	//图书模型数据集合，可以实时相应数据变化，无需刷新
  	private ObservableList<Student> studentdata = FXCollections.observableArrayList();
  	
  	String sex;//用来存储性别
  	
  	
  	@FXML
	public void initialize() throws SQLException{
  		allstudents();
  	}
  	
  	public void allstudents() throws SQLException{
        //调用查询读者借阅的图书的方法
  		List<Student> studentList = isd.selectAllStudents();
  		studentdata.addAll(studentList);
  		stutable.setItems(studentdata);
  		
  		//水平方向不显示滚动条，表格的列宽会均匀分布
  		stutable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
  		
  	//编辑列的相关设置
		 editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	        editCol.setCellFactory(param -> new TableCell<Student, Student>() {
	            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
	            private final Button editButton = ComponentUtil.getButton("编辑", "blue-theme");

	            @Override
	            protected void updateItem(Student student, boolean empty) {
	                super.updateItem(student, empty);
	                if (student == null) {
	                    setGraphic(null);
	                    return;
	                }
	                setGraphic(editButton);
	                //点击编辑按钮，弹出窗口
	                editButton.setOnAction(event -> {
	                	
	                	TextInputDialog bala = new TextInputDialog("请输入存入金额");
	                	bala.setTitle("读者信息修改界面");
	                	bala.setHeaderText("   存入金额到" + student.getName()+"的账户");
	                	bala.setContentText("请输入存入的金额:");
	                    Optional<String> result = bala.showAndWait();
	                    //确认输入了内容，避免NPE
	                    if (result.isPresent()) {
	                        //获取输入的新价格并转化成Double数据
	                        String balan = result.get();
	                        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	                        boolean b = pattern.matcher(balan).matches();
	                        if(balan.equals("")||b==false) {
	                        	Dialog wrchar = new Dialog();
	                        	wrchar.setTitle("提示");
	                        	wrchar.setHeaderText("请输入正确的金额");
	                        	wrchar.getDialogPane().getButtonTypes().add(ButtonType.OK);
	                        	wrchar.showAndWait();
	                        }
	                        else {
	                        student.setBalance(Double.parseDouble(balan)+student.getBalance());
	                        //更新图书信息
	                        try
							{
								isd.update(student);
							} catch (SQLException e)
							{
								e.printStackTrace();
							}
	                        
	                        //更新提示
	                        Dialog res = new Dialog();
	                        res.setTitle("提示");
	                        res.setHeaderText("已成功存入" + student.getName()+"的账户"+balan+"元");
	                        res.getDialogPane().getButtonTypes().add(ButtonType.OK);
	                        res.showAndWait();
	                    }
	                    }
	                    
	                });
	            }
	        });
	        //将编辑列加入图书表格
       //将编辑列加入图书表格
	        stutable.getColumns().add(editCol);
  		
  		
  		//删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<Student, Student>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");
            
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (student == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
               
                    //点击了确认按钮，同时移除一行模型数据
                        
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("确定删除" + student.getName()+"的信息吗？");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                    	studentdata.remove(student);
                        prompt();
                        try
						{
							isd.delete(student.getStudent_id());
						} catch (SQLException e)
						{
							e.printStackTrace();
						}
                        
                    }
                	
                });
            }
        });
        //将归还列加入图书表格
        stutable.getColumns().add(delCol);
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
		List<Student> keyStudent = isd.selectStudent(keyword);
		int sure = keyStudent.size();
		if(sure==0) {//如果数据库没有此学生,则提示没有此学生
			Dialog nob = new Dialog();
			nob.setTitle("提示");
			nob.setHeaderText("对不起，没有与'"+keyword+"'相关的用户");
			nob.getDialogPane().getButtonTypes().add(ButtonType.OK);
			nob.showAndWait();
		}
		else {
			stutable.getItems().removeAll(studentdata);//移除表中所有内容
			while(j<keyStudent.size()) {
				Student stu = new Student(keyStudent.get(j).getStudent_id(),
										  keyStudent.get(j).getName(),
										  keyStudent.get(j).getSex(),
										  keyStudent.get(j).getId(),
										  keyStudent.get(j).getBalance()); 
				studentdata.add(stu);
				j++;
			}
			stutable.setItems(studentdata);
			stutable.refresh();
		}
  	}
}