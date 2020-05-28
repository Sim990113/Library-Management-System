package DAO.impl;

import Entity.Manager;
import Entity.Student;
import Util.DBUtil;
import DAO.IStudentDAO;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements IStudentDAO {

	   //借助工具类  增加数据
	    @Override
	    public boolean add(Student s) {

	        String sql = "insert into test.info (student_id,name,sex,password,id,balance) values (?,?,?,?,?,?)";
	        return DBUtil.executeUpdate(sql,s.getStudent_id(),s.getName(),s.getSex(),s.getPassword(),s.getId(),s.getBalance());

	    }
	//借助工具类  删除
	    public boolean delete(long student_id) {
	        String sql = "delete from test.info where student_id = ?";

	        return DBUtil.executeUpdate(sql,student_id);

	    }
	     //借助工具类   修改数据
	    @Override
	    public boolean update(Student s) {
	        String sql = "update test.info set name=?,sex=?,password=?,id=?,balance = ? where student_id = ?";

	        return DBUtil.executeUpdate(sql,s.getName(),s.getSex(),s.getPassword(),s.getId(),s.getBalance(),s.getStudent_id());
	    }
	  //查询所有 
	    public List<Student> getAllStu() {
	           try {
	                List<Student> list=new ArrayList<>();
	                Connection conn = DBUtil.getConnection();
	                String sql="select * from test.info";
	                PreparedStatement ps = conn.prepareStatement(sql);
	                ResultSet rs = ps.executeQuery();
	                while(rs.next()){
	                	Student dat = new Student(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
	                	list.add(dat);
	                }
	                return list;

	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	           return null;
	        }
		@Override
		public List<Student> checkrepeat(long student_id)
		{
			try {
				List<Student> slist = new ArrayList<>();
				Connection conn = DBUtil.getConnection();
                String sql="select student_id from test.info where student_id= "+student_id;
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                	Student stu = new Student(rs.getLong(0));
                	slist.add(stu);
                }
                return slist;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		@Override
		public List<Student> selectAllStudents() throws SQLException
		{
			try {
	            List<Student> slist=new ArrayList<>();
	            Connection conn = DBUtil.getConnection();
	            String sql="select * from test.info";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	            	Student stu = new Student(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getDouble(6));
	            	slist.add(stu);
	            }
	            return slist;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	       return null;
		}
		@Override
		public List<Student> selectStudent(String keyword) throws SQLException
		{
			try {
	            List<Student> slist=new ArrayList<>();
	            Connection conn = DBUtil.getConnection();
	            String sql="select * from test.info where student_id like '%"+keyword+"%' or name like'%"+keyword+"%' or id like '%"+keyword+"%'";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	            	Student stu = new Student(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getDouble(6));
	            	slist.add(stu);
	            }
	            return slist;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	       return null;
		}
}
