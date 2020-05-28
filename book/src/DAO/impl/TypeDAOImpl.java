package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.TypeDAO;
import Entity.Type;
import Util.DBUtil;

public class TypeDAOImpl implements TypeDAO
{
	@Override
	public List<String> getAllType()
	{
		try {
            List<String> tlist=new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            String sql="select distinct category from test.book";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	String tbook = new String(rs.getString(1));
            	tlist.add(tbook);
            }
            return tlist;

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}
}
