package me.mcluke300.playerlogger;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.mcluke300.playerlogger.commands.playerloggerCommand;
import me.mcluke300.playerlogger.config.*;
import me.mcluke300.playerlogger.listeners.PListener;

public class playerlogger extends JavaPlugin {

	//Making Directory
	File subdir = new File("plugins/PlayerLogger/Users");
	File subdir2 = new File("plugins/PlayerLogger/Staff");
	
	//Plugin
	public static playerlogger plugin;
	
	//Command
	private playerloggerCommand executor;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		//Creating the folders
		if (!subdir.exists()) {
			subdir.mkdir();
		}
		if (plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
			if (!subdir2.exists()) {
				subdir2.mkdir();
			}
		}
		
		config.LoadConfiguration();
		getConfig.getValues();
		
		//Registering Listeners
		Bukkit.getServer().getPluginManager().registerEvents(new PListener(this), this);
		
		//Commands
		executor = new playerloggerCommand(this);
		getCommand("playerlogger").setExecutor(executor);
		
		//Metrics
		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}
		
}

	
	@Override
	public void onDisable() {
		//Do Nothing
	}

}
