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
     * select password from test.info where student_id= ?
     */
    List<User> check(long student_id)throws SQLException;
    
    /**
     * 导出用户个人信息
     * select * from test.info where student_id= ?
     */
    List<User> selectUser(long student_id) throws SQLException;
    
    /**
     * 更新用户信息
     *
     * @param user,userpassword
     * @return 
     * @return
     */
     boolean updateUser(long userid,String userpassword) throws SQLException;
    
    /**
     * 读者缴纳罚金，根据学号扣除相应的余额
     *
     * @param student_id,bid
     * @return
     */
    boolean updateUserbalance(long userid ,double userbalance ) throws SQLException;
    
}
