package DAO;

import java.sql.SQLException;
import java.util.List;

import Entity.Book;
import Entity.Manager;
import Entity.Student;
import Entity.User;

public interface ManagerDAO
{
	/**
     * 添加新管理员
     * insert  into test.manager (manager_id,name,sex,password,id) values (?,?,?,?,?);
     * 两个以上的参数 全用对象出传参
     * @param  学生对象 里面存放当这需要添加的学生信息
     * @return  boolean  成功返回 true  失败 返回 false
     */
    boolean add(Manager m)throws SQLException;
    
    /**
     * 查询所有管理员
     *
     * @return
     */
    List<Manager> selectAllManagers() throws SQLException;
    
    /**
     * 导出用户的密码以核实
     * select password from test.manager where manager_id= ?
     */
    List<Manager> check(long manager_id)throws SQLException;
    
    /**
     * 导出用户个人信息
     * select * from test.manager where manager_id= ?
     */
    List<Manager> selectManager(long manager_id) throws SQLException;
    
    /**
     * 更新用户信息
     *
     * @param manager_id,managerpassword
     * @return
     */
    boolean updateManager(long manager_id,String managerpassword) throws SQLException;
    
    /**
     * 检查数据库中是否存在此用户
     * select manager_id from test.manager where manager_id= ?
     */
    List<Manager> checkrepeat(long manager_id)throws SQLException;
}
