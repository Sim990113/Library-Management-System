package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ManagerDAO;
import Entity.Book;
import Entity.Manager;
import Entity.Student;
import Entity.User;
import Util.DBUtil;

public class ManagerDAOImpl implements ManagerDAO
{

	@Override
	public boolean add(Manager m) throws SQLException
	{
		 String sql = "insert into test.manager (manager_id,name,sex,password,id) values (?,?,?,?,?)";
	        return DBUtil.executeUpdate(sql,m.getManager_id(),m.getName(),m.getSex(),m.getPassword(),m.getId());
	}

	

	@Override
	public List<Manager> selectAllManagers() throws SQLException
	{
		try {
            List<Manager> list=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.manager order by manager_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	Manager manager = new Manager(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	list.add(manager);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
	}



	@Override
	public List<Manager> check(long manager_id) throws SQLException
	{
		try {
			List<Manager> mlist = new ArrayList<>();
			Connection conn = DBUtil.getConnection();
            String sql="select password from test.manager where manager_id= "+manager_id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	Manager manager = new Manager(rs.getString(0));
            	mlist.add(manager);
            }
            return mlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Manager> selectManager(long manager_id) throws SQLException
	{
		try {
            List<Manager> mlist=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select * from test.manager where manager_id ="+manager_id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	Manager manager = new Manager(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            	mlist.add(manager);
            }
            return mlist;

        } catch (SQLException e) {
            e.printStackTrace();
        }

		return null;
	}



	@Override
	public boolean updateManager(long manager_id, String managerpassword)
			throws SQLException
	{
		String sql = "update test.manager set password = ? where manager_id = ?";

        return DBUtil.executeUpdate(sql,manager_id,managerpassword);
	}



	@Override
	public List<Manager> checkrepeat(long manager_id) throws SQLException
	{
		try {
			List<Manager> mlist = new ArrayList<>();
			Connection conn = DBUtil.getConnection();
            String sql="select manager_id from test.manager where manager_id= "+manager_id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	Manager manager = new Manager(rs.getLong(1));
            	mlist.add(manager);
            }
            return mlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
