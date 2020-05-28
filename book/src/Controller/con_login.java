package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import DAO.*;
import DAO.impl.StudentDAOImpl;
import DAO.impl.UserDAOImpl;
import Entity.Student;
import Entity.User;


public class con_login
{	
    Student s =new Student();
    User u = new User();
    int YON=0;
    UserDAO ud = new UserDAOImpl();
    IStudentDAO sd = new StudentDAOImpl();
    
    
    //此类的条件
    public con_login() {
    	
    	
	}
	//创造一个没有参数的与类名相同的公有方法
    
	@FXML
	public void initialize() {// 将用户输入的数据存放起来
		
		
		
		
		
		
	}
	//初始化方法
	
	@FXML
	public TextField Student_IDField;//引用注册时用户输入的ID
	
	@FXML
	public TextField PasswordField;//引用注册时用户输入的密码
	
	@FXML
	public TextField NameField;//引用注册时用户输入的姓名
	
	@FXML
	public TextField IDField;//引用注册时用户输入的ID Number
	
	@FXML
	public TextField accountField;//获取用户输入的账号
	
	@FXML
	public PasswordField passwordField;//获取用户输入的密码
	
	@FXML
	public ToggleGroup toggle;//引入存放单选按钮的组

	@FXML
	public Button ok;//有用的，用来关窗口的，别删
	
	@FXML
	public TextField FaccountField;//忘记密码中输入的Student_ID
	
	@FXML
	public TextField FIDField;//忘记密码中输入的ID_Number
	
	@FXML
	public Button fok;//用来关闭忘记密码界面的按钮
	
	@FXML
	public Button signin;//登录按钮
	
	public void wrong() {//登录报错窗口
		try {
			Parent error = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/error.fxml"));
			Stage errorstage = new Stage();
			errorstage.setTitle("Error");
			
			Scene scenerror = new Scene(error,258,130);
			URL ur = this.getClass().getClassLoader().getResource("css/login.css");
			scenerror.getStylesheets().add(ur.toExternalForm());//加载.css文件
			errorstage.setScene(scenerror);
			errorstage.setResizable(false);//固定窗口大小
			errorstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			errorstage.getIcons().add(new Image("image/warning.png"));//加载窗口图标
			errorstage.show();//显示窗口
			
			Button errorclose = (Button)error.lookup("#errorclose");
			errorclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					errorstage.close();
					
				}
			});//设置按钮鼠标单击事件来关闭窗口
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String sex; //用来存放性别
	
	@FXML
	public void zhuce() {//标签Create Account的单击事件
		try {
			Parent log = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/account.fxml"));
			Stage logstage = new Stage();//创建注册账号这个窗口
			logstage.setTitle("Create Account");
			Scene zhucescene= new Scene(log);
			logstage.setScene(zhucescene);
			URL u = this.getClass().getClassLoader().getResource("css/login.css");
			zhucescene.getStylesheets().add(u.toExternalForm());//加载.css文件
			logstage.setResizable(false);//固定窗口大小
			logstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			logstage.getIcons().add(new Image("image/create.png"));
			logstage.show();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void keep() {//用来监听选择了哪个性别
		toggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{

			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue)
			{
				RadioButton r = (RadioButton)newValue;
				sex=r.getText();
				
			}
		});
	}
	
	public void accountwrong(){//输入信息错误报错窗口
		try {
			Parent aerror = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/accounterror.fxml"));
			Stage aerrorstage = new Stage();
			aerrorstage.setTitle("Error");
			
			Scene sceneaerror = new Scene(aerror,283,140);
			URL ae = this.getClass().getClassLoader().getResource("css/login.css");
			sceneaerror.getStylesheets().add(ae.toExternalForm());//加载.css文件
			aerrorstage.setScene(sceneaerror);
			aerrorstage.setResizable(false);//固定窗口大小
			aerrorstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			aerrorstage.getIcons().add(new Image("image/warning.png"));//加载窗口图标
			aerrorstage.show();//显示窗口
			
			Button aerrorclose = (Button)aerror.lookup("#aerrorclose");
			aerrorclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					aerrorstage.close();
					
				}
			});//设置按钮鼠标单击事件来关闭窗口
			
		}catch(Exception x) {
			x.printStackTrace();
		}
	}
	

	public void charwrong() {//创建账号时字符输入报错窗口
		try {
			Parent cerror = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/charerror.fxml"));
			Stage cerrorstage = new Stage();
			cerrorstage.setTitle("Error");
			
			Scene scenecerror = new Scene(cerror,260,136);
			URL ce = this.getClass().getClassLoader().getResource("css/login.css");
			scenecerror.getStylesheets().add(ce.toExternalForm());//加载.css文件
			cerrorstage.setScene(scenecerror);
			cerrorstage.setResizable(false);//固定窗口大小
			cerrorstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			cerrorstage.getIcons().add(new Image("image/warning.png"));//加载窗口图标
			cerrorstage.show();//显示窗口
			
			Button cerrorclose = (Button)cerror.lookup("#cerrorclose");
			cerrorclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					cerrorstage.close();
					
				}
			});//设置按钮鼠标单击事件来关闭窗口
			
		}catch(Exception x) {
			x.printStackTrace();
		}
	}
	public void repeat() {//创建账号时字符输入报错窗口
		try {
			Parent rerror = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/again.fxml"));
			Stage rerrorstage = new Stage();
			rerrorstage.setTitle("Error");
			
			Scene scenererror = new Scene(rerror,250,130);
			URL ae = this.getClass().getClassLoader().getResource("css/login.css");
			scenererror.getStylesheets().add(ae.toExternalForm());//加载.css文件
			rerrorstage.setScene(scenererror);
			rerrorstage.setResizable(false);//固定窗口大小
			rerrorstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			rerrorstage.getIcons().add(new Image("image/warning.png"));//加载窗口图标
			rerrorstage.show();//显示窗口
			
			Button rerrorclose = (Button)rerror.lookup("#rerrorclose");
			rerrorclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					rerrorstage.close();
					Student_IDField.setText("");
					
				}
			});//设置按钮鼠标单击事件来关闭窗口
			
		}catch(Exception x) {
			x.printStackTrace();
		}
	}
	
	public void finish() {//注册完之后，提示窗口
		try {
			Parent ferror = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/finish.fxml"));
			Stage ferrorstage = new Stage();
			ferrorstage.setTitle("Registration Success!");
			
			Scene sceneferror = new Scene(ferror,258,130);
			URL fe = this.getClass().getClassLoader().getResource("css/login.css");
			sceneferror.getStylesheets().add(fe.toExternalForm());//加载.css文件
			ferrorstage.setScene(sceneferror);
			ferrorstage.setResizable(false);//固定窗口大小
			ferrorstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			ferrorstage.getIcons().add(new Image("image/yes.png"));//加载窗口图标
			ferrorstage.show();//显示窗口
			
			Button ferrorclose = (Button)ferror.lookup("#finishclose");
			ferrorclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					ferrorstage.close();
					
				}
			});//设置按钮鼠标单击事件来关闭窗口
			
		}catch(Exception x) {
			x.printStackTrace();
		}
	}
	
	
	public void charcorrect() throws SQLException {//验证输入信息的正误
		
		String PWD = PasswordField.getText();//存放密码
		
		
		String Name = NameField.getText();//用来存放姓名
		
		
		String SID = Student_IDField.getText();//用来存放Student ID
		
		
		String ID = IDField.getText();//用来存放ID
		
		
		if(SID.equals("")) {//如果用户没有输入学号，则弹出提示窗口
			charwrong();
		}
		else 
		{
		List<Student> student = sd.checkrepeat(Long.parseLong(SID));//通过用户输入的学号来与数据库中的信息进行对比，看是否重复，重复则弹出提示窗口
		if(student.size()!=0)
		{
			repeat();
		}
		else
		{//判断用户输入信息的格式是否符合标准
		
		if(PWD.equals("")||Name.equals("")||sex.equals(null)||ID.equals("")) 
		{	
			if(SID.length()!=15&&PWD.length()<6)
			{
				Student_IDField.setText("");
				PasswordField.setText("");
				charwrong();
			}
			else if(SID.length()!=15)
			{
				Student_IDField.setText("");
				charwrong();
			}
			else if(PWD.length()<6)
			{
				PasswordField.setText("");
				charwrong();
			}
			else if(ID.length()!=18)
			{
				IDField.setText("");
				charwrong();
			}
			else 
			{
				accountwrong();
			}
		}
		else if(SID.length()!=15&&PWD.length()<6&&ID.length()!=18)
		{
			Student_IDField.setText("");
			PasswordField.setText("");
			IDField.setText("");
			charwrong();
		}
		else if(SID.length()!=15&&PWD.length()<6)
		{
			Student_IDField.setText("");
			PasswordField.setText("");
			charwrong();
		}
		else if(SID.length()!=15&&ID.length()!=18)
		{
			Student_IDField.setText("");
			IDField.setText("");
			charwrong();
		}
		else if(ID.length()!=18&&PWD.length()<6)
		{
			IDField.setText("");
			PasswordField.setText("");
			charwrong();
		}
		
		else if(SID.length()!=15)
		{
			Student_IDField.setText("");
			charwrong();
		}
		//这里是设置，如果输入到文本框中的内容不等于15个则不符合创建要求，并且会报错，清除文本框中的内容，重新输入
		else if(PWD.length()<6)
		{
			PasswordField.setText("");
			charwrong();
		}
		//这里是设置，如果输入到文本框中的内容小于6个则不符合创建要求，并且会报错，清除文本框中的内容，重新输入
		else if(ID.length()!=18) {
			IDField.setText("");
			charwrong();
		}
		//这里是设置，如果输入到文本框中的内容不等于18个则不符合创建要求，并且会报错，清除文本框中的内容，重新输入
		else{
			s.setPassword(PWD);//载入注册时输入的密码
			
			s.setName(Name);//载入注册时输入的名字
			
			s.setStudent_id(Long.parseLong(SID));//载入注册时输入的sid
			
			s.setId(ID);//载入注册时输入的ID
			
			s.setSex(sex);//载入注册时选择的性别
			
			s.setBalance(0);//注册时设置账户余额初始值为0
			
			sd.add(s);//将用户输入的信息导入到数据库
			YON=1;
		
			Stage logstage = (Stage)ok.getScene().getWindow();
			logstage.close();//关闭登录界面
			finish();//跳转主功能界面
        
		}
		}
		}
	}
	
	
	@FXML
	public void login() {//设置登录按钮单击事件
		try
		{
			
			
			if(accountField.getText().equals("")||passwordField.getText().equals("")) {
				wrong();
			}//用户如果没填账号和密码，会报错
			
			
			else 
			{
			Long UID = Long.parseLong(accountField.getText());//用来存放用户登录输入的账号
			
			String UPD = passwordField.getText();//用来存放用户登录输入的密码
			
			List<User> user = ud.selectUser(UID);
			
			if(user.size()==0) 
			{
				wrong();//如果用户输入的账号在数据库中不存在，则弹出提示窗口
			}
			else 
			{
			u.setUsername(user.get(0).getUsername());
			u.setUsersex(user.get(0).getUsersex());
			u.setId(user.get(0).getId());
			u.setUserid(user.get(0).getUserid());
			u.setUserpassword(user.get(0).getUserpassword());

			
			String PW = null;
			
			
				PW=u.getUserpassword();
				//获取数据库的数据来核实账号密码
				//密码错误则弹出报错窗口
				if(!PW.equals(UPD)) 
				{	
					wrong();
				}
				//登录成功后的操作
				else if(PW.equals(UPD)) {
					
					Stage primarystage = (Stage)signin.getScene().getWindow();
			        primarystage.close();//关闭登录界面窗口
			        Stage rmainstage = new Stage();
					
					BorderPane rmain = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main_i.fxml"));
					rmainstage.setTitle("图书管理系统");
					Scene rmainscene= new Scene(rmain,1600,900);
					rmainstage.setScene(rmainscene);
					URL r = this.getClass().getClassLoader().getResource("css/style.css");
					rmainscene.getStylesheets().add(r.toExternalForm());//加载.css文件
					rmainstage.getIcons().add(new Image("image/logo.png"));
					rmainstage.show();
					rmainstage.setOnCloseRequest(new EventHandler<WindowEvent>()
					{

						@Override
						public void handle(WindowEvent event)
						{
							System.exit(0);//当关闭窗口时程序终止
							
						}
					});
					
					
				}
			}
			
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	@FXML
	public void OK() {//注册完毕后按钮OK单击事件
		
		try {
			charcorrect();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void forget() {//标签忘记密码的单击事件
		try {
			Parent forget = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/forget.fxml"));
			Stage forstage = new Stage();//创建注册账号这个窗口
			forstage.setTitle("Forget Password");
			Scene forscene= new Scene(forget);
			forstage.setScene(forscene);
			URL ur = this.getClass().getClassLoader().getResource("css/login.css");
			forscene.getStylesheets().add(ur.toExternalForm());//加载.css文件
			forstage.setResizable(false);//固定窗口大小
			forstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			forstage.getIcons().add(new Image("image/forget.png"));
			forstage.show();
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resets() {//重置密码成功弹窗
		try {
			Parent rserror = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/resetend.fxml"));
			Stage rserrorstage = new Stage();
			rserrorstage.setTitle("Reset Password Success!");
			
			Scene scenerserror = new Scene(rserror,258,130);
			URL fe = this.getClass().getClassLoader().getResource("css/login.css");
			scenerserror.getStylesheets().add(fe.toExternalForm());//加载.css文件
			rserrorstage.setScene(scenerserror);
			rserrorstage.setResizable(false);//固定窗口大小
			rserrorstage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			rserrorstage.getIcons().add(new Image("image/yes.png"));//加载窗口图标
			rserrorstage.show();//显示窗口
			
			Button rserrorclose = (Button)rserror.lookup("#resetclose");
			rserrorclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent event)
				{
					rserrorstage.close();
					
				}
			});//设置按钮鼠标单击事件来关闭窗口
			
		}catch(Exception x) {
			x.printStackTrace();
		}
	}
	
	
	@FXML
	public void FOK() throws SQLException {//忘记密码界面中的OK按钮的单击事件
		String FSID = FaccountField.getText();//引入忘记密码界面中输入的账号字符串
		String FID = FIDField.getText();//引入忘记密码界面中输入的身份证号字符串
		String FDBSID = null;//用来存储数据库中特定的student id
		String FDBID = null;//用来存储数据库中特定的id
		
		if(FSID.equals("")||FID.equals("")) {
			charwrong();
		}
		else if(FSID.length()!=15&&FID.length()!=6) {//对用户输入的字符进行检查
			FaccountField.setText("");
			FIDField.setText("");
			charwrong();
		}
		else if(FSID.length()!=15) {
			FaccountField.setText("");
			charwrong();
		}
		else if(FID.length()!=6) {
			FIDField.setText("");
			charwrong();
		}
		else {
			List<User> fuser = ud.selectUser(Long.parseLong(FSID));
			if(fuser.size()==0) {
				accountwrong();
				FaccountField.setText("");
			}
			else {
			User fu = new User(fuser.get(0).getUserid(),
							   fuser.get(0).getUsername(),
							   fuser.get(0).getUsersex(),
							   fuser.get(0).getUserpassword(),
							   fuser.get(0).getId(),
							   fuser.get(0).getUserbalance());
			
			FDBSID = Long.toString(fu.getUserid());
			FDBID = fu.getId().toString();
			String l6=FDBID.substring(FDBID.length()-6,FDBID.length());
			if(FDBID==null||!l6.equals(FID)) {
				accountwrong();
				FIDField.setText("");
			}
			else {
				ud.updateUser(Long.parseLong(FSID), l6);
				resets();
				Stage forstage = (Stage)fok.getScene().getWindow();
				forstage.close();
			}
		}
		}
	}
}
