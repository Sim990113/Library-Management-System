package Entity;

import javafx.beans.property.SimpleStringProperty;

public class Type
{
	/* 成员变量  属性 */
	private  final SimpleStringProperty category = new SimpleStringProperty();
	
	/*构造函数*/
	public Type() {

	}
	
	public Type(String category) {
		setCategory(category);
	}
	
	/*set和get*/
	public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
}
