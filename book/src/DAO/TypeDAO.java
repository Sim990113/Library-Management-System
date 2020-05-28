package DAO;

import java.sql.SQLException;
import java.util.List;

import Entity.Book;
import Entity.Type;

/**
 * 图书类别DAO
 */
public interface TypeDAO
{
	/**
     * 列出图书类别信息
     *
     * 
     * @return
     */
    List<String> getAllType() throws SQLException;
}
