package Controller;


import java.io.IOException;
import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class con_main_interface
{
	con_login guser  = new con_login();
	public con_main_interface() {
		
	}
	//创造一个没有参数的与类名相同的公有方法
	
	@FXML
	public Button logout;//引用读者界面注销按钮
	
	@FXML
	public Label timeLabel;//引用时间标签
	
	@FXML
	public Label adminName;//引用用户的名字
		
	@FXML
	public StackPane mainContainer;//数据面板
	
	@FXML
	public void initialize(){
		//启一个线程，用来同步获取系统时间

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //获取系统当前时间
                    LocalDateTime now = LocalDateTime.now();
                    //格式化时间
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
                    String timeString = dateTimeFormatter.format(now);
                    //启一个UI线程
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //将格式化后的日期时间显示在标签上
                            timeLabel.setText(timeString);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println("中断异常");
                    }
                }
            }
        }).start();
        
        try {
        	 AnchorPane anchorPane =FXMLLoader.load(getClass().getClassLoader().getResource("fxml/book.fxml"));
        	 mainContainer.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }        
		
        adminName.setText("Hello,"+guser.u.getUsername()+" !");//获取登录用户的姓名
        
        
	}

	private void switchView(String fileName) throws Exception {//切换界面
        //清空原有内容
        mainContainer.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/" + fileName)).load();
        mainContainer.getChildren().add(anchorPane);
    }
	
	@FXML
	public void viewbook() throws Exception {//切换到浏览图书界面
		switchView("book.fxml");
	}
	
	@FXML
	public void listPersonal() throws Exception {//切换到浏览个人信息界面
		switchView("personal.fxml");
	}
	
	@FXML
	public void listBorrow() throws Exception {//切换到浏览借阅信息界面
		
		switchView("userbook.fxml");
	}
	
	@FXML
	public void listStudent() throws Exception {//切换到浏览借阅信息界面
		
		switchView("students.fxml");
	}
	
	
	
	@FXML
	public void LOGOUT() {//确认是否注销
		
		try {
			Parent ensure = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ensureout.fxml"));
			Stage ensurestage = new Stage();
			ensurestage.setTitle("Prompt!");
			Scene scenerserror = new Scene(ensure,258,130);
			URL en = this.getClass().getClassLoader().getResource("css/style.css");
			scenerserror.getStylesheets().add(en.toExternalForm());//加载.css文件
			ensurestage.setScene(scenerserror);
			ensurestage.setResizable(false);//固定窗口大小
			ensurestage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			ensurestage.getIcons().add(new Image("image/ensureout.png"));//加载窗口图标
			ensurestage.show();//显示窗口
			
			Button yesclose = (Button)ensure.lookup("#yesclose");
			yesclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent yevent)
				{
					ensurestage.close();
					Stage rmainstage = (Stage)logout.getScene().getWindow();
					rmainstage.close();//关闭读者界面
					ensureout();
				}
			});
			Button noclose = (Button)ensure.lookup("#noclose");
			noclose.setOnMouseClicked(new EventHandler<MouseEvent>()
			{

				@Override
				public void handle(MouseEvent nevent)
				{
					ensurestage.close();
				}
			});
			
		}catch(Exception x) {
			x.printStackTrace();
		}
		
	}
	
	public void ensureout() {//创建登录界面以注销返回
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
			Stage primarystage = new Stage();//创建注册账号这个窗口
			primarystage.setTitle("图书管理系统");
			Scene scene= new Scene(login,1600,900);
			primarystage.setScene(scene);
			URL u = this.getClass().getClassLoader().getResource("css/style.css");
			scene.getStylesheets().add(u.toExternalForm());//加载.css文件
			primarystage.setResizable(false);//固定窗口大小
			primarystage.initModality(Modality.APPLICATION_MODAL);//模态窗口
			primarystage.getIcons().add(new Image("image/logo.png"));
			primarystage.show();
			primarystage.setOnCloseRequest(new EventHandler<WindowEvent>()
			{

				@Override
				public void handle(WindowEvent event)
				{
					System.exit(0);
					
				}
			});
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
