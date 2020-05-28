package DAO.impl;

import java.util.ArrayList;
import java.util.List;

import Entity.UserBook;
import Util.DBUtil;
import DAO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBookDAOImpl implements UserBookDAO
{
	//借助工具类  增加数据
	@Override
	public boolean addUserBook(UserBook ubook) throws SQLException
	{
		String sql = "insert into test.userbook (student_id,bid,bname,date,deadline) values (?,?,?,?,?)";
        return DBUtil.executeUpdate(sql,ubook.getStudent_id(),ubook.getBid(),ubook.getBname(),ubook.getDate(),ubook.getDeadline());
	}
	
	//借助工具类  删除
	@Override
	public boolean deleteUserBook(long student_id, long bid) throws SQLException
	{
		String sql = "delete from test.userbook where student_id = ? and bid= ?";
        return DBUtil.executeUpdate(sql,student_id,bid);
	}

	//查询借阅图书
	@Override
	public List<UserBook> selectUserBook(long student_id) throws SQLException
	{
		try {
            List<UserBook> list=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.userbook where student_id = "+student_id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	UserBook usbook = new UserBook(rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	list.add(usbook);
            	
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
	}
	

}
