package me.mcluke300.playerlogger.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.mcluke300.playerlogger.playerlogger;
import me.mcluke300.playerlogger.config.getConfig;
import me.mcluke300.playerlogger.mysql.mysql;

public class playerloggerCommand implements CommandExecutor{

	private playerlogger plugin;

	public playerloggerCommand(playerlogger plugin) {
		this.plugin = plugin;
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
					plugin.reloadConfig();
					getConfig.getValues();
					mysql.createDatabase();
					sender.sendMessage(ChatColor.GREEN+"PlayerLogger Config Reloaded");
				}

			} else if (args.length < 2) {
				return false;
			}
		}
		return false;
	}
}
