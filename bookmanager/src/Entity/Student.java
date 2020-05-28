package Entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/*
   	学生实体类 ---- table student
*/
public class Student
{	
	/* 成员变量  属性 */
    private  final SimpleLongProperty student_id= new SimpleLongProperty();
    private  final SimpleStringProperty name = new SimpleStringProperty();
    private  final SimpleStringProperty sex = new SimpleStringProperty();
    private  final SimpleStringProperty password = new SimpleStringProperty();
    private  final SimpleStringProperty id = new SimpleStringProperty();
    private  final SimpleDoubleProperty balance = new SimpleDoubleProperty();
    
    /*构造函数*/
    public Student() {

    }
    public Student(long student_id, String name, String sex, String password, String id ,double balance) {
        setStudent_id(student_id);
        setName(name);
        setSex(sex);
        setPassword(password);
        setId(id);
        setBalance(balance);
    }
    public Student(long student_id, String name, String sex, String password, String id) {
    	setStudent_id(student_id);
        setName(name);
        setSex(sex);
        setPassword(password);
        setId(id);
    }
    public Student(long student_id, String name, String sex,  String id,double balance) {
    	setStudent_id(student_id);
        setName(name);
        setSex(sex);
        setId(id);
        setBalance(balance);
    }
    public Student(long student_id) {
    	setStudent_id(student_id);
    }
    
    
   
    /*set和get*/
    
	public String getId()
	{
		return id.get();
	}
	public void setId(String id)
	{
		this.id.set(id);;
	}
	
	public SimpleStringProperty idProperty() {
		return id;
	}
	
	
	public long getStudent_id()
	{
		return student_id.get();
	}
	public void setStudent_id(long student_id)
	{
		this.student_id.set(student_id);;
	}
	public SimpleLongProperty student_idProperty() {
		return student_id;
	}
	
	public String getName()
	{
		return name.get();
	}
	public void setName(String name)
	{
		this.name.set(name);;
	}
	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public String getSex()
	{
		return sex.get();
	}
	public void setSex(String sex)
	{
		this.sex.set(sex);;
	}
	public SimpleStringProperty sexProperty() {
		return sex;
	}
	
	
	public String getPassword()
	{
		return password.get();
	}
	public void setPassword(String password)
	{
		this.password.set(password);;
	}
	public SimpleStringProperty passwordProperty() {
		return password;
	}
	
	
	
	 public double getBalance()
	{
		return balance.get();
	}
	public void setBalance(double balance)
	{
		this.balance.set(balance);;
	}
	public SimpleDoubleProperty balanceProperty() {
		return balance;
	}
	
	    
	
}
