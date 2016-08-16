package com.gzjiyi.weixin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBConnection {
	public static Connection GetConn() throws Exception
	{
	    return getSqlConnByJdbcContext();
	}

	public static Connection getSqlConnByJdbcContext(){
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/default");
			return ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		}
		
	}
	
	public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){  
        try {  
            if(rs!=null){  
                rs.close();  
                rs=null;  
            }  
            if(ps!=null){  
                ps.close();  
                ps=null;  
            }  
            if(conn!=null){  
                conn.close();  
                conn=null;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }

	public static void closeExe(Connection conn,Statement st){  
		try {
			if(st!=null){  
				st.close();
				st=null;  
	        }  
	        if(conn!=null){  
	        	conn.close();  
	        	conn=null;  
	        }  

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
