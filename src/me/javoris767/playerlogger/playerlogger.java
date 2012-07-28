package me.javoris767.playerlogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class playerlogger extends JavaPlugin {
	//Listener
	//private final PlayerLoggerListener slistener = new PlayerLoggerListener();
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
		if (plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
			if (!subdir2.exists()) {
				subdir2.mkdir();
			}
		}
	//	Bukkit.getServer().getPluginManager().registerEvents(slistener, this);
		try {
		    MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		System.out.println(this+" has been Enabled");

	}
	@Override
	public void onDisable() {
		System.out.println(this+" has been Disabled");
	}

	//The Config Defaults
	public void LoadConfiguration() {
		//BlackList
		List<String> words = new ArrayList<String>();
		words.add("7");
		words.add("46");
		words.add("57");

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

		//Defaults
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

		//Copy Defaults
		getConfig().options().copyDefaults(true);
		saveConfig();

	}
	
	//Reload Command

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("playerlogger")){
			if (args.length == 0) {
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
	

}
