package me.mcluke300.playerlogger.config;

import java.util.ArrayList;
import java.util.List;

import me.mcluke300.playerlogger.playerlogger;

public class config {
	playerlogger plugin;

	public config(playerlogger instance) {
		plugin = instance;
	}

	
	public static void LoadConfiguration() {
		
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
		String path25 = "Log.DateFormat";
		String path10 = "BlackList.LogBlackListedBlocks";
		String path11 = "BlackList.Blocks";
		String path13 = "Commands.BlackListCommands";
		String path24 = "Commands.BlackListCommandsForMySQL";
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
		playerlogger.plugin.getConfig().addDefault(path22, true);
		playerlogger.plugin.getConfig().addDefault(path1, true);
		playerlogger.plugin.getConfig().addDefault(path2, true);
		playerlogger.plugin.getConfig().addDefault(path3, true);
		playerlogger.plugin.getConfig().addDefault(path4, true);
		playerlogger.plugin.getConfig().addDefault(path5, true);
		playerlogger.plugin.getConfig().addDefault(path6, true);
		playerlogger.plugin.getConfig().addDefault(path7, true);
		playerlogger.plugin.getConfig().addDefault(path8, true);
		playerlogger.plugin.getConfig().addDefault(path9, true);
		playerlogger.plugin.getConfig().addDefault(path25, "MM-dd-yyyy HH:mm:ss");
		playerlogger.plugin.getConfig().addDefault(path10, true);
		playerlogger.plugin.getConfig().addDefault(path11, words);
		playerlogger.plugin.getConfig().addDefault(path12, true);
		playerlogger.plugin.getConfig().addDefault(path13, false);
		playerlogger.plugin.getConfig().addDefault(path24, false);
		playerlogger.plugin.getConfig().addDefault(path14, cmds);
		playerlogger.plugin.getConfig().addDefault(path15, true);
		playerlogger.plugin.getConfig().addDefault(path16, false);
		playerlogger.plugin.getConfig().addDefault(path23, false);
		playerlogger.plugin.getConfig().addDefault(path17, false);
		playerlogger.plugin.getConfig().addDefault(path18, "Server Address eg.Localhost");
		playerlogger.plugin.getConfig().addDefault(path19, "Place Database name here");
		playerlogger.plugin.getConfig().addDefault(path20, "Place User of MySQL Database here");
		playerlogger.plugin.getConfig().addDefault(path21, "Place User password here");

		//Copy Defaults
		playerlogger.plugin.getConfig().options().copyDefaults(true);
		playerlogger.plugin.saveConfig();

	}
	
}
