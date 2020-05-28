package DAO;


import Entity.Book;
import java.sql.SQLException;
import java.util.List;

/**
 * 图书DAO
 */
public interface BookDAO {

    /**
     * 新增图书，返回自增主键
     *
     * @param book
     * @return
     * @throws SQLException
     */
    boolean addBook(Book book) throws SQLException;

    /**
     * 根据id删除图书
     *
     * @param id
     * @return
     */
    boolean deleteBook(long id) throws SQLException;

    /**
     * 更新图书信息
     *
     * @param book
     * @return
     */
    boolean updateBook(Book book) throws SQLException;


    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> selectAllBooks() throws SQLException;

    /**
     * 借阅图书更新信息
     *
     * @param reserve
     * @return
     */
    boolean borrowBook(int reserve,long id) throws SQLException;
    
    /**
     * 根据关键字查询图书信息
     *
     * @param keyword
     * @return
     */
    List<Book> getBook(String keyword) throws SQLException;
    
    
    /**
     * 根据图书类型查询图书信息
     *
     * @param category
     * @return
     */
    List<Book> getBooksByType(String category) throws SQLException;
    
    /**
     * 查询图书id
     *
     * 
     * @return
     */
    int getBookID() throws SQLException;

}
