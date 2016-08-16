package com.gzjiyi.weixin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ExcutSQL {
	protected static Connection conn;
	
	public Map<String,String> SelectToken(String sql) throws Exception{
		conn=DBConnection.GetConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(); 
		ResultSetMetaData rsmd=rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		
		Map<String,String> token=new HashMap<String,String>();
		while (rs.next()) {
			token=new HashMap<String,String>();
	        for (int j = 1; j <= columnCount; ++j) token.put(rsmd.getColumnName(j),rs.getString(rsmd.getColumnName(j)));
	    }
		DBConnection.closeAll(conn, ps, rs);
		return token;
	}
	
	public boolean execSQL(String sql) throws Exception{
		try{
			conn=DBConnection.GetConn();
			Statement st = conn.createStatement();
			st.execute(sql);
			DBConnection.closeExe(conn, st);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
