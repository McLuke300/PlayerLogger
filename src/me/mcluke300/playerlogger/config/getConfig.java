package me.mcluke300.playerlogger.config;

import java.util.List;

import me.mcluke300.playerlogger.playerlogger;

public class getConfig {
		playerlogger plugin;

		public getConfig(playerlogger instance) {
			plugin = instance;
		}
	
	//The Public booleans
	private static boolean logFilesEnabled;
	
	private static boolean PlayerJoins;
	private static boolean PlayerQuit;
	private static boolean PlayerChat;
	private static boolean PlayerCommands;
	private static boolean PlayerDeaths;
	private static boolean PlayerEnchants;
	private static boolean PlayerPvp;
	private static boolean PlayerBucketPlace;
	private static boolean PlayerSignText;
	
	private static boolean ConsoleCommands;
	private static boolean SeparateFolderforStaff;
	private static boolean PlayerNamestoLowerCase;
	private static boolean LogOnlyStaff;
	
	private static boolean LogBlackListedBlocks;
	private static List<String> Blocks;
	
	private static boolean BlackListCommands;
	private static boolean BlackListCommandsMySQL;
	private static List<String> CommandsToBlock;
	
	private static boolean MySQLEnabled;
	private static String MySQLServer;
	private static String MySQLDatabase;
	private static String MySQLUser;
	private static String MySQLPassword;
	
	//Called OnEnable and on Reload to save the values
	public static void getValues() {
		logFilesEnabled=playerlogger.plugin.getConfig().getBoolean("File.LogToFiles");
		MySQLEnabled=playerlogger.plugin.getConfig().getBoolean("MySQL.Enabled");
		
		PlayerJoins=playerlogger.plugin.getConfig().getBoolean("Log.PlayerJoins");
		PlayerQuit=playerlogger.plugin.getConfig().getBoolean("Log.PlayerQuit");
		PlayerChat=playerlogger.plugin.getConfig().getBoolean("Log.PlayerChat");
		PlayerCommands=playerlogger.plugin.getConfig().getBoolean("Log.PlayerCommands");
		PlayerDeaths=playerlogger.plugin.getConfig().getBoolean("Log.PlayerDeaths");
		PlayerEnchants=playerlogger.plugin.getConfig().getBoolean("Log.PlayerEnchants");
		PlayerPvp=playerlogger.plugin.getConfig().getBoolean("Log.Pvp");
		PlayerBucketPlace=playerlogger.plugin.getConfig().getBoolean("Log.PlayerBucketPlace");
		PlayerSignText=playerlogger.plugin.getConfig().getBoolean("Log.PlayerSignText");
		
		ConsoleCommands=playerlogger.plugin.getConfig().getBoolean("Log.ConsoleCommands");
		SeparateFolderforStaff=playerlogger.plugin.getConfig().getBoolean("Log.SeparateFolderforStaff");
		PlayerNamestoLowerCase=playerlogger.plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase");
		LogOnlyStaff=playerlogger.plugin.getConfig().getBoolean("Log.LogOnlyStaff");
		
		LogBlackListedBlocks=playerlogger.plugin.getConfig().getBoolean("BlackList.LogBlackListedBlocks");
		Blocks=playerlogger.plugin.getConfig().getStringList("BlackList.Blocks");
		
		BlackListCommands=playerlogger.plugin.getConfig().getBoolean("Commands.BlackListCommands");
		BlackListCommandsMySQL=playerlogger.plugin.getConfig().getBoolean("Commands.BlackListCommandsForMySQL");
		CommandsToBlock=playerlogger.plugin.getConfig().getStringList("Commands.CommandsToBlock");
		
		MySQLServer=playerlogger.plugin.getConfig().getString("MySQL.Server");
		MySQLDatabase=playerlogger.plugin.getConfig().getString("MySQL.Database");
		MySQLUser=playerlogger.plugin.getConfig().getString("MySQL.User");
		MySQLPassword=playerlogger.plugin.getConfig().getString("MySQL.Password");
	}

	//Return values
	public static boolean logFilesEnabled() {
		return logFilesEnabled;
	}
	public static boolean PlayerJoins() {
		return PlayerJoins;
	}
	public static boolean PlayerQuit() {
		return PlayerQuit;
	}
	public static boolean PlayerChat() {
		return PlayerChat;
	}
	public static boolean PlayerCommands() {
		return PlayerCommands;
	}
	public static boolean PlayerDeaths() {
		return PlayerDeaths;
	}
	public static boolean PlayerEnchants() {
		return PlayerEnchants;
	}
	public static boolean PlayerPvp() {
		return PlayerPvp;
	}
	public static boolean PlayerBucketPlace() {
		return PlayerBucketPlace;
	}
	public static boolean PlayerSignText() {
		return PlayerSignText;
	}
	public static boolean ConsoleCommands() {
		return ConsoleCommands;
	}
	public static boolean SeparateFolderforStaff() {
		return SeparateFolderforStaff;
	}
	public static boolean PlayerNamestoLowerCase() {
		return PlayerNamestoLowerCase;
	}
	public static boolean LogOnlyStaff() {
		return LogOnlyStaff;
	}
	public static boolean LogBlackListedBlocks() {
		return LogBlackListedBlocks;
	}
	public static List<String> Blocks() {
		return Blocks;
	}
	public static boolean BlackListCommands() {
		return BlackListCommands;
	}
	public static boolean BlackListCommandsMySQL() {
		return BlackListCommandsMySQL;
	}
	public static List<String> CommandsToBlock() {
		return CommandsToBlock;
	}
	public static boolean MySQLEnabled() {
		return MySQLEnabled;
	}
	public static String MySQLServer() {
		return MySQLServer;
	}
	public static String MySQLDatabase() {
		return MySQLDatabase;
	}
	public static String MySQLUser() {
		return MySQLUser;
	}
	public static String MySQLPassword() {
		return MySQLPassword;
	}
	
	
}
