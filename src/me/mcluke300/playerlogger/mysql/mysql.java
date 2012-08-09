package me.mcluke300.playerlogger.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import me.mcluke300.playerlogger.config.*;

public class mysql {

	//Creating MySQL database
	public void createDatabase() {
		if (getConfig.MySQLEnabled()) {
			Connection connection = null;
			Statement st = null;
			int rs = 0;
			try {
				//Connecting to database
				connection = DriverManager.getConnection("jdbc:mysql://" + getConfig.MySQLServer() + "/" + getConfig.MySQLDatabase() , getConfig.MySQLUser(), getConfig.MySQLPassword());
				st = connection.createStatement();
				//Make table if it does not exist query onEnable
				rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS `playerlogger`( `id` MEDIUMINT NOT NULL AUTO_INCREMENT, `playername` text, `type` text, `time` INT(255), `data` text, `x` MEDIUMINT(255), `y` MEDIUMINT(255), `z` MEDIUMINT(255), `world` text, PRIMARY KEY (`id`))");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print(rs);
			}
			finally {
				try {
					if (st != null) {
						st.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException ex) {
				}
			}
		}
	}


}
