package Entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/*
	图书实体类 ---- table book
*/
public class Book
{	
	/* 成员变量  属性 */
	private  final SimpleLongProperty id=new SimpleLongProperty();
	private  final SimpleStringProperty name = new SimpleStringProperty();
	private  final SimpleStringProperty author = new SimpleStringProperty();
	private  final SimpleStringProperty category = new SimpleStringProperty();
	private  final SimpleIntegerProperty reserve = new SimpleIntegerProperty();
	private  final SimpleStringProperty location = new SimpleStringProperty();
	

	/*构造函数*/
	public Book() {

	}
	public Book(long id, String name, String author,String category, int reserve, String location) {
		setId(id);
		setName(name);
		setAuthor(author);
		setCategory(category);
		setReserve(reserve);
		setLocation(location);
	}
	
	public Book(String category) {
		setCategory(category);
	}

	/*set和get*/
	public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }
    public void setId(long id) {
        this.id.set(id);
    }
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }
    
    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
    
    public int getReserve() {
        return reserve.get();
    }

    public SimpleIntegerProperty reserveProperty() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve.set(reserve);
    }
    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
	
}
