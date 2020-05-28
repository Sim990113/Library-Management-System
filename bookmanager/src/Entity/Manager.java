package Entity;

/*
	学生实体类 ---- table student
*/
public class Manager
{	
/* 成员变量  属性 */
private  long  manager_id;
private  String  name;
private  String  sex;
private  String  password;
private  String  id;

/*构造函数*/
public Manager() {

}
public Manager(long manager_id, String name, String sex, String password, String id) {
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.password=password;
    this.manager_id=manager_id;
}

public Manager(String password) {
	this.password=password;
}

public Manager(long manager_id) {
	this.manager_id=manager_id;
}

/*set和get*/
public long getManager_id()
{
	return manager_id;
}
public void setManager_id(long manager_id)
{
	this.manager_id = manager_id;
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
public String getId()
{
	return id;
}
public void setId(String id)
{
	this.id = id;
}
}

