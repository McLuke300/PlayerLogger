package me.mcluke300.playerlogger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class playerlogger extends JavaPlugin {

	//Making Directory
	File subdir = new File("plugins/PlayerLogger/Users");
	File subdir2 = new File("plugins/PlayerLogger/Staff");
	//Plugin
	public static playerlogger plugin;
	//Date Format
	static final String DATE_FORMAT_NOW = "MM-dd-yyyy HH:mm:ss";

	@Override
	public void onEnable() {
		if (!subdir.exists()) {
			subdir.mkdir();
		}
		plugin = this;
		LoadConfiguration();
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLoggerListener(this), this);
		
		//Creating the folders
		if (plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
			if (!subdir2.exists()) {
				subdir2.mkdir();
			}
		}
		
		//Metrics
		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}
		System.out.println(this+" has been Enabled");
		
		//Creating MySQL database
		if (plugin.getConfig().getBoolean("MySQL.Enabled")) {
			Connection connection = null;
			Statement st = null;
			int rs = 0;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + getConfig().getString("MySQL.Server") + "/" + getConfig().getString("MySQL.Database"), getConfig().getString("MySQL.User"), getConfig().getString("MySQL.Password"));
				st = connection.createStatement();
				rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS `playerlogger`( `id` MEDIUMINT NOT NULL AUTO_INCREMENT, `playername` text, `type` text, `time` INT(255), `data` text, `x` MEDIUMINT(255), `y` MEDIUMINT(255), `z` MEDIUMINT(255), `world` text, PRIMARY KEY (`id`))");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				}}}}

	
	@Override
	public void onDisable() {
		//Do Nothing
	}

	//The Config Defaults
	public void LoadConfiguration() {
		//BlackList
		List<String> words = new ArrayList<String>();
		words.add("7");
		words.add("46");
		words.add("57");

		//Commands not to log
		List<String> cmds = new ArrayList<String>();
		cmds.add("/login");
		cmds.add("/changepassword");
		cmds.add("/register");


		//Paths
		String path1 = "Log.PlayerJoins";
		String path2 = "Log.PlayerQuit";
		String path3 = "Log.PlayerChat";
		String path4 = "Log.PlayerCommands";
		String path5 = "Log.PlayerDeaths";
		String path6 = "Log.PlayerEnchants";
		String path8 = "Log.PlayerBucketPlace";
		String path12 = "Log.PlayerSignText";
		String path7 = "Log.Pvp";
		String path9 = "Log.ConsoleCommands";
		String path10 = "BlackList.LogBlackListedBlocks";
		String path11 = "BlackList.Blocks";
		String path13 = "Commands.BlackListCommands";
		String path14 = "Commands.CommandsToBlock";
		String path15 = "Log.SeparateFolderforStaff";
		String path16 = "Log.PlayerNamestoLowerCase";
		String path22 = "File.LogToFiles";
		String path23 = "Log.LogOnlyStaff";
		String path17 = "MySQL.Enabled";
		String path18 = "MySQL.Server";
		String path19 = "MySQL.Database";
		String path20 = "MySQL.User";
		String path21 = "MySQL.Password";

		//Defaults
		getConfig().addDefault(path22, true);
		getConfig().addDefault(path1, true);
		getConfig().addDefault(path2, true);
		getConfig().addDefault(path3, true);
		getConfig().addDefault(path4, true);
		getConfig().addDefault(path5, true);
		getConfig().addDefault(path6, true);
		getConfig().addDefault(path7, true);
		getConfig().addDefault(path8, true);
		getConfig().addDefault(path9, true);
		getConfig().addDefault(path10, true);
		getConfig().addDefault(path11, words);
		getConfig().addDefault(path12, true);
		getConfig().addDefault(path13, false);
		getConfig().addDefault(path14, cmds);
		getConfig().addDefault(path15, true);
		getConfig().addDefault(path16, false);
		getConfig().addDefault(path23, false);
		getConfig().addDefault(path17, false);
		getConfig().addDefault(path18, "Server Address eg.Localhost");
		getConfig().addDefault(path19, "Place Database name here");
		getConfig().addDefault(path20, "Place User of MySQL Database here");
		getConfig().addDefault(path21, "Place User password here");

		//Copy Defaults
		getConfig().options().copyDefaults(true);
		saveConfig();

	}

	//Reload Command
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("playerlogger")){
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED+"Usage: /playerlogger reload");
				return false;
			} else if (args.length == 1){

				//Reload
				if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("PlayerLogger.reload")) {
					getConfig();
					reloadConfig();
					getServer().getPluginManager().disablePlugin(plugin);
					getServer().getPluginManager().enablePlugin(plugin);
					sender.sendMessage(ChatColor.GREEN+"PlayerLogger Config Reloaded");
				}

			} else if (args.length < 2) {
				return false;
			}
		}
		return false;
	}


	//MySQL
	public static void addData(String playername, String type, String data, double x, double y, double z, String worldname, Boolean staff) {
		PreparedStatement pst = null;
		Connection con = null;
		long time = System.currentTimeMillis()/1000; //Unix time
		//Checking if they should be logged
		if (staff && plugin.getConfig().getBoolean("Log.LogOnlyStaff") || !plugin.getConfig().getBoolean("Log.LogOnlyStaff")) {



			try {
				con = DriverManager.getConnection("jdbc:mysql://" + plugin.getConfig().getString("MySQL.Server") + "/" + plugin.getConfig().getString("MySQL.Database"), plugin.getConfig().getString("MySQL.User"), plugin.getConfig().getString("MySQL.Password"));

				String database = "playerlogger";
				//Prepared statment
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
