package Entity;
/*
   	学生实体类 ---- table student
*/
public class Student
{	
	/* 成员变量  属性 */
    private  long  student_id;
    private  String  name;
    private  String  sex;
    private  String  password;
    private  String  id;
    private  double balance;
    
    /*构造函数*/
    public Student() {

    }
    public Student(long student_id, String name, String sex, String password, String id ,double balance) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password=password;
        this.student_id=student_id;
        this.balance=balance;
    }
    public Student(long student_id, String name, String sex, String password, String id) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password=password;
        this.student_id=student_id;
    }
    
   
    /*set和get*/
    
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public long getStudent_id()
	{
		return student_id;
	}
	public void setStudent_id(long student_id)
	{
		this.student_id = student_id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	 public double getBalance()
	{
		return balance;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	public Student(long student_id) 
	{
	   	this.student_id=student_id;
	}
	    
	
}
