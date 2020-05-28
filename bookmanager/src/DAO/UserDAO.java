package DAO;

import java.sql.SQLException;
import java.util.List;
import Entity.User;

/*
 * 用户表的DAO
 */

public interface UserDAO
{
	 /**
     * 导出用户的密码以核实
     * select password from test.manager where manager_id= ?
     */
    List<User> check(long userid)throws SQLException;
    
    /**
     * 导出用户个人信息
     * select * from test.manager where manager_id= ?
     */
    List<User> selectUser(long userid) throws SQLException;
    
    /**
     * 更新用户信息
     *
     * @param userid,userpassword
     * @return
     */
    boolean updateUser(long userid,String userpassword) throws SQLException;
  
    
}
