package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import de.jensd.fx.glyphs.*;





public class Main extends Application {

    
	
	public void start(Stage primaryStage) {
		
		try {
			//加载fxml文件，进行页面的显示
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(fxmlLoader.getClassLoader().getResource("fxml/login.fxml"));
	        AnchorPane an = (AnchorPane)fxmlLoader.load();//加载窗口界面
			Scene scene = new Scene(an);
			URL url = this.getClass().getClassLoader().getResource("css/style.css");
			scene.getStylesheets().add(url.toExternalForm());//加载.css文件
			primaryStage.setTitle("图书管理系统");//设置窗口名称
			primaryStage.getIcons().add(new Image("image/logo.png"));//设置Logo
			primaryStage.setHeight(900);
			primaryStage.setWidth(1600);//设置窗口大小
			primaryStage.setScene(scene);//将scene放到stage上面
			primaryStage.show();//显示窗口
		} catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
	    launch(args);
	}
	
		
}
	

