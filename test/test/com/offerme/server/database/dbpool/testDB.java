package test.com.offerme.server.database.dbpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import com.offerme.server.database.dbpool.DataPoolManager;


public class testDB {
	
	public static void main(String[] args) {
		
		try
		{
			Connection m_myConn = DataPoolManager.getConnection("OfferMe");
			Statement sm = m_myConn.createStatement();
			String sql = "select current_date";
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(rs.getString(1));
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
		}
	}
	
}
