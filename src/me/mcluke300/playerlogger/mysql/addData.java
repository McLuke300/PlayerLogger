package me.mcluke300.playerlogger.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import me.mcluke300.playerlogger.config.*;

public class addData {

	//MySQL
	public static void add(String playername, String type, String data, double x, double y, double z, String worldname, Boolean staff) {
		PreparedStatement pst = null;
		Connection con = null;
		long time = System.currentTimeMillis()/1000; //Unix time
		//Checking if they should be logged
		if (staff && getConfig.LogOnlyStaff() || !getConfig.LogOnlyStaff()) {


			try {
				con = DriverManager.getConnection("jdbc:mysql://" + getConfig.MySQLServer() + "/" + getConfig.MySQLDatabase() , getConfig.MySQLUser(), getConfig.MySQLPassword());

				String database = "playerlogger";
				//Prepared statement
				pst = con.prepareStatement("INSERT INTO " +database+"(playername, type, time, data, x, y, z, world) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				//Values
				pst.setString(1, playername);
				pst.setString(2, type);
				pst.setLong(3, time);
				pst.setString(4, data);
				pst.setDouble(5, x);
				pst.setDouble(6, y);
				pst.setDouble(7, z);
				pst.setString(8, worldname);
				//Do the MySQL query
				pst.executeUpdate();
			} catch (SQLException ex) {
				System.out.print(ex);
			}
		}	
	}
}
