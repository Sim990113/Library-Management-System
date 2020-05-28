package DAO.impl;

import java.util.ArrayList;
import java.util.List;

import Entity.Student;
import Entity.UserBook;
import Util.DBUtil;
import DAO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBookDAOImpl implements UserBookDAO
{
	
	//借助工具类  删除
	@Override
	public boolean deleteUserBook(long student_id, long bid) throws SQLException
	{
		String sql = "delete from test.userbook where student_id = ? and bid= ?";
        return DBUtil.executeUpdate(sql,student_id,bid);
	}

	//查询借阅图书
	@Override
	public List<UserBook> selectUserBook() throws SQLException
	{
		try {
            List<UserBook> list=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.userbook";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	UserBook usbook = new UserBook(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	list.add(usbook);
            	
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
	}

	@Override
	public List<UserBook> searchUserBook(String keyword) throws SQLException
	{
		try {
            List<UserBook> slist=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.userbook where student_id like '%"+keyword+"%' or bname like'%"+keyword+"%'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	UserBook ubook = new UserBook(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	slist.add(ubook);
            }
            return slist;

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
	}

	@Override
	public List<UserBook> searchUserBookBybid(String keyword)
			throws SQLException
	{
		try {
            List<UserBook> slist=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.userbook where bid like '%"+keyword+"%' or bname like'%"+keyword+"%'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	UserBook ubook = new UserBook(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	slist.add(ubook);
            }
            return slist;

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
	}

	

}
