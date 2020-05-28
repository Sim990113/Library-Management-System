package DAO.impl;


import Entity.User;
import Util.DBUtil;
import DAO.UserDAO;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO
{

	@Override
	public List<User> check(long userid) throws SQLException
	{
		try {
			List<User> ulist = new ArrayList<>();
			Connection conn = DBUtil.getConnection();
            String sql="select password from test.manager where manager_id= "+userid;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	User user = new User(rs.getString(0));
            	ulist.add(user);
            }
            return ulist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUser(long userid) throws SQLException
	{
		try {
            List<User> ulist=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.manager where manager_id ="+userid;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	User user = new User(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	ulist.add(user);
            }
            return ulist;

        } catch (SQLException e) {
            e.printStackTrace();
        }

		return null;
	}

	@Override
	public boolean updateUser(long userid, String userpassword)
			throws SQLException
	{
		String sql = "update test.manager set password = ? where manager_id = ?";

        return DBUtil.executeUpdate(sql,userpassword,userid);
	}

	

}
