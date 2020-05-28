package DAO;


import Entity.UserBook;
import java.sql.SQLException;
import java.util.List;

/**
 * 读者图书DAO
 */

public interface UserBookDAO
{
	/**
     * 读者借阅图书
     *
     * @param ubook
     * @return
     * @throws SQLException
     */
    boolean addUserBook(UserBook ubook) throws SQLException;

    /**
     * 读者及时归还或者缴纳罚金，根据图书id和学生id删除图书
     *
     * @param student_id,bid
     * @return
     */
    boolean deleteUserBook(long student_id ,long bid ) throws SQLException;


    /**
     * 查询读者借阅的所有图书
     *
     * @return
     */
    List<UserBook> selectUserBook(long student_id) throws SQLException;
    
 


}
