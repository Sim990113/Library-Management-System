package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.UserDAO;
import DAO.impl.UserDAOImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class con_personal implements Initializable
{	
	@FXML
	private Label Usid;
	
	@FXML
	private Label Uname;
	
	@FXML
	private Label Uid;
	
	@FXML
	private TextField Upassword;
	
	con_login guser  = new con_login();
	UserDAO ud = new UserDAOImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Usid.setText(Long.toString(guser.u.getUserid()));//在界面显示用户学号
            	Uname.setText(guser.u.getUsername());//在界面显示用户姓名
            	Uid.setText(guser.u.getId());//在界面显示用户身份证号
                Upassword.setText(guser.u.getUserpassword());//在界面显示用户密码
                
                
            }
        });
	}
	
	@FXML
	public void edit() {
        //激活密码框为可编辑状态，同时改变样式
        Upassword.setEditable(true);
        Upassword.getStyleClass().add("input-group");
        Upassword.setOnMouseClicked(event -> {
        	Upassword.setText("");
        });
    }
	
	@FXML
	public void save() throws SQLException {
        //获取密码框的值
        String passString = Upassword.getText().trim();
        if(passString.equals("")||passString.length()<6) {
        	Dialog wrong = new Dialog();
        	wrong.setTitle("提示");
        	wrong.setHeaderText("        请输入至少六位字符来重置密码");
        	wrong.getDialogPane().getButtonTypes().add(ButtonType.OK);
        	wrong.showAndWait();
        }
        //更新管理员密码
        else {
        guser.u.setUserpassword(passString);
        ud.updateUser(guser.u.getUserid(), passString);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText("密码修改成功");
        alert.showAndWait();
        }
    }
	

}
