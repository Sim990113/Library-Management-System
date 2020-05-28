package DAO.impl;

import java.util.ArrayList;
import java.util.List;

import Entity.Book;
import Util.DBUtil;
import DAO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAOImpl implements BookDAO {

	   //借助工具类  增加数据
	    @Override
	    public boolean addBook(Book b) {

	        String sql = "insert into test.book (id,name,author,category,reserve,location) values (?,?,?,?,?,?)";
	        return DBUtil.executeUpdate(sql,b.getId(),b.getName(),b.getAuthor(),b.getCategory(),b.getReserve(),b.getLocation());

	    }
	    //借助工具类  删除
	    public boolean deleteBook(long id) {
	        String sql = "delete from test.book where id = ?";
	        return DBUtil.executeUpdate(sql,id);

	    }
	     //借助工具类   修改数据
	   
	    public boolean updateBook(Book b) {
	        String sql = "update test.book set name=?,author=?,category=?,reserve=?,location=? where id = ?";

	        return DBUtil.executeUpdate(sql,b.getName(),b.getAuthor(),b.getCategory(),b.getReserve(),b.getLocation(),b.getId());
	    }
	  //查询所有 
	    @Override
	    public List<Book> selectAllBooks() {
	           try {
	                List<Book> list=new ArrayList<>();
	                Connection conn = DBUtil.getConnection();
	                String sql="select * from test.book order by id";
	                PreparedStatement ps = conn.prepareStatement(sql);
	                ResultSet rs = ps.executeQuery();
	                while(rs.next()){
	                	Book book = new Book(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
	                	list.add(book);
	                }
	                return list;

	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	           return null;
	        }
		@Override
		public List<Book> getBook(String keyword)
		{
			try {
				List<Book> blist = new ArrayList<>();
				Connection conn = DBUtil.getConnection();
                String sql="select * from test.book where name like '%"+keyword+"%' or author like '%"+keyword+"%'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                	Book book = new Book(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                	blist.add(book);
                }
                return blist;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		public boolean borrowBook(int reserve,long id) throws SQLException
		{
			String sql = "update test.book set reserve = ? where id = ? ";
			return DBUtil.executeUpdate(sql,reserve,id);
		}
		@Override
		public List<Book> getBooksByType(String category)
		{
			try {
				List<Book> gtlist = new ArrayList<>();
				Connection conn = DBUtil.getConnection();
                String sql="select * from test.book where category= '"+category+"'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                	Book gtbook = new Book(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                	gtlist.add(gtbook);
                }
                return gtlist;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
}