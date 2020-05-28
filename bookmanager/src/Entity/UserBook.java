package Entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/*
	读者图书实体类 ---- table Userbook
*/
public class UserBook
{
	/* 成员变量  属性 */
	private  final SimpleLongProperty student_id=new SimpleLongProperty();
	private  final SimpleStringProperty bname = new SimpleStringProperty();
	private  final SimpleLongProperty bid = new SimpleLongProperty();
	private  final SimpleStringProperty date = new SimpleStringProperty();
	private  final SimpleStringProperty deadline = new SimpleStringProperty();	
	
	/*构造函数*/
	public UserBook() {

	}
	
	public UserBook(long student_id, long bid,String bname,String date,String deadline)
	{
		setStudent_id(student_id);
		setBname(bname);
		setBid(bid);
		setDate(date);
		setDeadline(deadline);
	}
	
	//重载这个类型
	public UserBook(long bid,String bname,String date,String deadline)
	{
		setBname(bname);
		setBid(bid);
		setDate(date);
		setDeadline(deadline);
	}
	
	/*set和get*/
	public long getStudent_id() {
        return student_id.get();
    }

    public SimpleLongProperty student_idProperty() {
        return student_id;
    }
    public void setStudent_id(long student_id) {
        this.student_id.set(student_id);
    }
    
    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }
    public void setDate(String date) {
        this.date.set(date);
    }
    
    
    public String getBname() {
        return bname.get();
    }

    public SimpleStringProperty bnameProperty() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname.set(bname);
    }
    
    public long getBid() {
        return bid.get();
    }

    public SimpleLongProperty bidProperty() {
        return bid;
    }
    public void setBid(long bid) {
        this.bid.set(bid);
    }
    
    public String getDeadline() {
        return deadline.get();
    }

    public SimpleStringProperty deadlineProperty() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline.set(deadline);
    }
    public String toString() {
    	return bid+"shit"+bname;
    }
    
}
