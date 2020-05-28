package Entity;
/*
 *  用户实体类 ----table user
 */


public class User
{
	/* 成员变量  属性 */
	private static long userid;
	private static String userpassword;
	private static String id;
	private static String username;
	private static String usersex;
	
	

	/*构造函数*/
    public User() {

    }
    
    public User(long userid) {
    	this.userid=userid;
    }
    
    public User(String userpassword) {
    	this.userpassword=userpassword;
    }
    
    public User(long userid, String username, String usersex, String userpassword, String id) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.usersex=usersex;
        this.userpassword=userpassword;
    }

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsersex()
	{
		return usersex;
	}

	public void setUsersex(String usersex)
	{
		this.usersex = usersex;
	}

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public String getUserpassword()
	{
		return userpassword;
	}

	public void setUserpassword(String userpassword)
	{
		this.userpassword = userpassword;
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
