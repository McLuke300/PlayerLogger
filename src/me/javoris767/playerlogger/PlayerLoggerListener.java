package me.javoris767.playerlogger;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerLoggerListener implements Listener{
	playerlogger plugin;

	public PlayerLoggerListener(playerlogger instance) {
		plugin = instance;
	}


	//Player Join
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerLoginEvent event) {
		if(event.getResult() == PlayerLoginEvent.Result.ALLOWED) {
			Player player = event.getPlayer();
			World world = player.getWorld();
			String worldname = world.getName();
			String playername = player.getName();
			String ip = "Error";
			Boolean staff;
			Boolean lowercase;
			ip = event.getAddress().getHostAddress();
			double x = (int) Math.floor(player.getLocation().getX());
			double y = (int) Math.floor(player.getLocation().getY());
			double z = (int) Math.floor(player.getLocation().getZ());
			if (plugin.getConfig().getBoolean("Log.PlayerJoins")) {
				if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
					staff = true;
				} else {
					staff = false;
				}
				if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
					lowercase = true;
				} else {
					lowercase = false;
				}
				filehandler.logLogin(playername, worldname, x, y, z, ip, staff, lowercase);
			}}}

	//Player Quit
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		String worldname = world.getName();
		String playername = player.getName();
		Boolean staff;
		Boolean lowercase;
		double x = (int) Math.floor(player.getLocation().getX());
		double y = (int) Math.floor(player.getLocation().getY());
		double z = (int) Math.floor(player.getLocation().getZ());
		if (plugin.getConfig().getBoolean("Log.PlayerQuit")) {
			if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
				staff = true;
			} else {
				staff = false;
			}
			if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
				lowercase = true;
			} else {
				lowercase = false;
			}
			filehandler.logQuit(playername, worldname, x, y, z, staff, lowercase);
		}}

	//Player Chat
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		String worldname = world.getName();
		String playername = player.getName();
		String msg = event.getMessage();
		Boolean staff;
		Boolean lowercase;
		double x = (int) Math.floor(player.getLocation().getX());
		double y = (int) Math.floor(player.getLocation().getY());
		double z = (int) Math.floor(player.getLocation().getZ());
		if (plugin.getConfig().getBoolean("Log.PlayerChat")) {
			if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
				staff = true;
			} else {
				staff = false;
			}
			if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
				lowercase = true;
			} else {
				lowercase = false;
			}
			filehandler.logChat(playername, msg, worldname, x, y, z, staff, lowercase);
		}}


	//Player Command
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCmd(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		String worldname = world.getName();
		String playername = player.getName();
		String msg = event.getMessage();
		String msg2[] = event.getMessage().split(" "); 
		Boolean staff;
		Boolean lowercase;
		Boolean log = false;
		double x = (int) Math.floor(player.getLocation().getX());
		double y = (int) Math.floor(player.getLocation().getY());
		double z = (int) Math.floor(player.getLocation().getZ());
		if (plugin.getConfig().getBoolean("Log.PlayerCommands")) {
			if (plugin.getConfig().getBoolean("Commands.BlackListCommands")) {
				for (String m : plugin.getConfig().getStringList("Commands.CommandsToBlock")) {
					m = m.toString().toLowerCase();
					if (msg2[0].equalsIgnoreCase(m)) {
						log = true;
					 break;
					}}
					if (!log) {
						if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
							staff = true;
						} else {
							staff = false;
						}
						if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
							lowercase = true;
						} else {
							lowercase = false;
						}
						filehandler.logCmd(playername, msg, worldname, x, y, z, staff, lowercase);
					}
			}else {
				if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
					staff = true;
				} else {
					staff = false;
				}
				if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
					lowercase = true;
				} else {
					lowercase = false;
				}
				filehandler.logCmd(playername, msg, worldname, x, y, z, staff, lowercase);
				return;
			}}}

	//Player Deaths
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeath(EntityDeathEvent event){
		Entity ent = event.getEntity();
		if(ent instanceof Player){
			Player player = (Player)event.getEntity();
			World world = player.getWorld();
			String worldname = world.getName();
			String playername = player.getName();
			Boolean staff;
			Boolean lowercase;
			String reason = event.getEventName();
			double x = (int) Math.floor(player.getLocation().getX());
			double y = (int) Math.floor(player.getLocation().getY());
			double z = (int) Math.floor(player.getLocation().getZ());
			if (plugin.getConfig().getBoolean("Log.PlayerDeaths")) {
				if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
					staff = true;
				} else {
					staff = false;
				}
				if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
					lowercase = true;
				} else {
					lowercase = false;
				}
				filehandler.logPlayerDeath(playername, reason, worldname, x, y, z, staff, lowercase);
			}}}

	//Player Enchant
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEnchant(EnchantItemEvent event){
		Player player = (Player)event.getEnchanter();
		String playername = player.getName();
		World world = player.getWorld();
		Boolean staff;
		Boolean lowercase;
		String worldname = world.getName();
		Map<Enchantment, Integer> ench = event.getEnchantsToAdd();
		ItemStack item = event.getItem();
		int cost = event.getExpLevelCost();
		if (plugin.getConfig().getBoolean("Log.PlayerEnchants")) {
			if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
				staff = true;
			} else {
				staff = false;
			}
			if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
				lowercase = true;
			} else {
				lowercase = false;
			}
			filehandler.logEnchant(playername, ench, item, cost, worldname, staff, lowercase);
		}}

	//Player Bucket
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBucket(PlayerBucketEmptyEvent event){
		Player player = event.getPlayer();
		String playername = player.getName();
		World world = player.getWorld();
		Boolean lava = false;
		String worldname = world.getName();
		int x;
		int y;
		Boolean staff;
		Boolean lowercase;
		int z;
		if (plugin.getConfig().getBoolean("Log.PlayerSignText")) {
			if (event.isCancelled() == false) {
				if (event.getBucket() != null && event.getBucket()==Material.LAVA_BUCKET) {
					lava = true;
					x = event.getBlockClicked().getLocation().getBlockX();
					y = event.getBlockClicked().getLocation().getBlockY();
					z = event.getBlockClicked().getLocation().getBlockZ();
					if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
						staff = true;
					} else {
						staff = false;
					}
					if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
						lowercase = true;
					} else {
						lowercase = false;
					}
					filehandler.logBucket(playername, worldname, x, y, z, lava, staff, lowercase);
				}else if (event.getBucket() != null && event.getBucket()==Material.WATER_BUCKET) {
					lava = false;
					x = event.getBlockClicked().getLocation().getBlockX();
					y = event.getBlockClicked().getLocation().getBlockY();
					z = event.getBlockClicked().getLocation().getBlockZ();
					if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
						staff = true;
					} else {
						staff = false;
					}
					if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
						lowercase = true;
					} else {
						lowercase = false;
					}
					filehandler.logBucket(playername, worldname, x, y, z, lava, staff, lowercase);
				}}}}


	//Player Sign Change event
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSign(SignChangeEvent event){
		Player player = event.getPlayer();
		String playername = player.getName();
		World world = player.getWorld();
		Boolean staff;
		Boolean lowercase;
		String[] lines = event.getLines();
		String worldname = world.getName();
		int x = event.getBlock().getLocation().getBlockX();
		int y = event.getBlock().getLocation().getBlockY();
		int z = event.getBlock().getLocation().getBlockZ();
		if (event.isCancelled() == false) {
			if (plugin.getConfig().getBoolean("Log.PlayerSignText")) {
				if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
					staff = true;
				} else {
					staff = false;
				}
				if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
					lowercase = true;
				} else {
					lowercase = false;
				}
				filehandler.logSign(playername, worldname, x, y, z, lines, staff, lowercase);
			}}}


	//PlayerPvp
	@EventHandler
	public void onEntityDeath1(org.bukkit.event.entity.EntityDeathEvent event) {
		org.bukkit.entity.Entity ply = event.getEntity();
		if(event.getEntity().getLastDamageCause() instanceof org.bukkit.event.entity.EntityDamageByEntityEvent){
			org.bukkit.entity.Entity dmgr = ((org.bukkit.event.entity.EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
			if(ply instanceof Player){            
				if(dmgr instanceof Player){
					String worldname = ply.getWorld().getName();
					String player = ((Player) ply).getName();
					String damager = ((Player) dmgr).getName();
					Boolean staff;
					Boolean lowercase;
					double x = Math.floor(ply.getLocation().getX());
					double y = Math.floor(ply.getLocation().getY());
					double z = Math.floor(ply.getLocation().getZ());
					if (plugin.getConfig().getBoolean("Log.Pvp")) {
						if (((Player) dmgr).hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
							staff = true;
						} else {
							staff = false;
						}
						if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
							lowercase = true;
						} else {
							lowercase = false;
						}
						filehandler.logKill(player, damager, x, y, z, worldname, staff, lowercase);
					}}}}}


	//Console Logger
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsoleCommand(ServerCommandEvent event) {
		String msg = event.getCommand();
		if (plugin.getConfig().getBoolean("Log.ConsoleCommands")) {
			filehandler.logConsole(msg);
		}}


	//BlockPlace
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		String playername = player.getName();
		World world = player.getWorld();
		String worldname = world.getName();
		Boolean staff;
		Boolean lowercase;
		int x = event.getBlock().getLocation().getBlockX();
		int y = event.getBlock().getLocation().getBlockY();
		int z = event.getBlock().getLocation().getBlockZ();
		String blockid = "" + event.getBlock().getTypeId();
		Boolean log = false;
		if (event.isCancelled() == false) {
			if(plugin.getConfig().getBoolean("BlackList.LogBlackListedBlocks")) {
				for (String m : plugin.getConfig().getStringList("BlackList.Blocks")) {
					m = m.toString().toLowerCase();
					if (blockid.equals(m)) {
						log = true;
					 break;
					}}
					if (log) {
					String blockname = event.getBlock().getType().toString();
					blockname = blockname.replaceAll("_", " ");
					if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
						staff = true;
					} else {
						staff = false;
					}
					if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
						lowercase = true;
					} else {
						lowercase = false;
					}
					filehandler.logPlace(playername, worldname, blockname, x, y, z, staff, lowercase);
				}}else if (plugin.getConfig().getStringList("BlackList.Blocks").toString().contains("*")) {
					String blockname = event.getBlock().getType().toString();
					blockname = blockname.replaceAll("_", " ");
					if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
						staff = true;
					} else {
						staff = false;
					}
					if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
						lowercase = true;
					} else {
						lowercase = false;
					}
					filehandler.logPlace(playername, worldname, blockname, x, y, z, staff, lowercase);
				}}}


	//BlockBreak
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		String playername = player.getName();
		World world = player.getWorld();
		String worldname = world.getName();
		Boolean staff;
		Boolean lowercase;
		int x = event.getBlock().getLocation().getBlockX();
		int y = event.getBlock().getLocation().getBlockY();
		int z = event.getBlock().getLocation().getBlockZ();
		String blockid = "" + event.getBlock().getTypeId();
		Boolean log = false;
		if (event.isCancelled() == false) {
			if(plugin.getConfig().getBoolean("BlackList.LogBlackListedBlocks")) {
				for (String m : plugin.getConfig().getStringList("BlackList.Blocks")) {
					m = m.toString().toLowerCase();
					if (blockid.equalsIgnoreCase(m)) {
						log = true;
					 break;
					}}
					if (log) {
					String blockname = event.getBlock().getType().toString();
					blockname = blockname.replaceAll("_", " ");
					if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
						staff = true;
					} else {
						staff = false;
					}
					if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
						lowercase = true;
					} else {
						lowercase = false;
					}
					filehandler.logBreak(playername, worldname, blockname, x, y, z, staff, lowercase);
				}}else if (plugin.getConfig().getStringList("BlackList.Blocks").toString().contains("*")) {
					String blockname = event.getBlock().getType().toString();
					blockname = blockname.replaceAll("_", " ");
					if (player.hasPermission("PlayerLogger.staff") && plugin.getConfig().getBoolean("Log.SeparateFolderforStaff")) {
						staff = true;
					} else {
						staff = false;
					}
					if (plugin.getConfig().getBoolean("Log.PlayerNamestoLowerCase")) {
						lowercase = true;
					} else {
						lowercase = false;
					}
					filehandler.logBreak(playername, worldname, blockname, x, y, z, staff, lowercase);
				}}}

}
