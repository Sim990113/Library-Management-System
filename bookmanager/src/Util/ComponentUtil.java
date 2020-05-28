package Util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ComponentUtil {

    //根据传入的文字和主题返回一个按钮
    public static Button getButton(String text, String theme) {
        Button button = new Button(text);
        button.getStyleClass().add(theme);
        return button;
    }
    
  //根据传入的文字和主题返回一个标签
    public static Label getLabel(String text) {
        Label label = new Label(text);
//        label.getStyleClass().add(theme);
        return label;
    }
}
