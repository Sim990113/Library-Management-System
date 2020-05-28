package DAO;

import java.sql.SQLException;
import java.util.List;

import Entity.Book;
import Entity.Student;


/**
 * 学生表的DAO
 */
public interface IStudentDAO {

    /**
     * 添加新学生
     * insert  into test.info (student_id,name,sex,password,id,balance) values (?,?,?,?,?,?);
     * 两个以上的参数 全用对象出传参
     * @param  学生对象 里面存放当这需要添加的学生信息
     * @return  boolean  成功返回 true  失败 返回 false
     */
    boolean add(Student s)throws SQLException;

    /**\
     * 根据student_id删除学生
     * delete from test.info where student_id = ？
     */
     boolean  delete(long student_id)throws SQLException;

    /**
     * 根据学生id修改学生信息
     * update test.info set name = ?,sex= ?, password=? , id=? where student_id = ?
     */
    boolean update(Student s)throws SQLException;
    
    
    /**
     * 检查数据库中是否存在此用户
     * select student_id from test.info where student_id= ?
     */
    List<Student> checkrepeat(long student_id)throws SQLException;
    
    /**
     * 查询所有学生
     *
     * @return
     */
    List<Student> selectAllStudents() throws SQLException;
    
    /**
     * 根据关键字查询学生
     *
     * @return
     */
    List<Student> selectStudent(String keyword) throws SQLException;

}