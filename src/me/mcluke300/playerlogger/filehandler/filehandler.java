package me.mcluke300.playerlogger.filehandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import me.mcluke300.playerlogger.playerlogger;
import me.mcluke300.playerlogger.config.getConfig;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class filehandler {
	static String dateformat = getConfig.LogDateFormat();
	static final String DATE_FORMAT_NOW = dateformat;
	playerlogger plugin;

	public filehandler(playerlogger instance) {
		plugin = instance;
	}



	//Login
	public static void logLogin(String playername, String worldname, double x,
			double y, double z, String ip, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();			
			out.println("["+worldname+"]"+playername + " Joined: " +ip+" " + "(" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}}




	//Quit
	public static void logQuit(String playername, String worldname, double x,
			double y, double z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername + " Quit: "+ "(" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}}



	//Chat
	public static void logChat(String playername, String msg, String worldname,
			double x, double y, double z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername + " Said: " + msg + " " + " " + "(" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}



	//Player Death
	public static void logPlayerDeath(String playername, String reason,
			String worldname, double x, double y, double z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername + " Died: " +  "(" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	//Enchantments
	public static void logEnchant(String playername,
			Map<Enchantment, Integer> ench, ItemStack item, int cost,
			String worldname, double x, double y, double z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+getTimestamp()+" ["+playername+"]" +""+item+" "+ench+" Xp Cost:"+cost+ " (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	//Bucket
	public static void logBucket(String playername, String worldname, int x,
			int y, int z, Boolean lava, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		String water = "Water";
		if (lava){water = "Lava";};
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername+" Emptied Bucket of "+water+" (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}



	//Sign
	public static void logSign(String playername, String worldname, int x,
			int y, int z, String[] lines, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername+" Sign: "+"["+lines[0]+"]"+"["+lines[1]+"]"+"["+lines[2]+"]"+"["+lines[3]+"]"+" (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	//Kills
	public static void logKill(String player, String damager, double x,
			double y, double z, String worldname, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			damager = damager.toLowerCase();
			player = player.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + damager + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + damager + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+damager+" Killed "+player+ " (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}

	//KilledBy
	public static void logKilledBy(String player, String damager, double x,
			double y, double z, String worldname, Boolean staff2) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			damager = damager.toLowerCase();
			player = player.toLowerCase();
		}
		File user;
		if (staff2 == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + player + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + player + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+player+" Killed by "+damager+ " (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}

	//Console
	public static void logConsole(String msg) {
		File console = new File("plugins/PlayerLogger/ConsoleLog.properties");
		try {
			FileWriter outfile = new FileWriter(console, true);
			PrintWriter out = new PrintWriter(outfile);
			console.createNewFile();
			out.println("[Console]"+msg+" "+getTimestamp());
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	//Place
	public static void logPlace(String playername, String worldname,
			String blockname, int x, int y, int z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername+" Placed: "+blockname+ " (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	//Break
	public static void logBreak(String playername, String worldname,
			String blockname, int x, int y, int z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername+" Broke: "+blockname+ " (" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	//LogCommand
	public static void logCmd(String playername, String msg, String worldname,
			double x, double y, double z, Boolean staff) {
		Boolean lowercase = getLowercase();
		if (lowercase) {
			playername = playername.toLowerCase();
		}
		File user;
		if (staff == true && getStaff()) {
			user = new File("plugins/PlayerLogger/Staff/" + playername + ".properties");
		} else if (!getOnlyStaff()){
			user = new File("plugins/PlayerLogger/Users/" + playername + ".properties");	
		} else {
			return;
		}
		try {
			FileWriter outfile = new FileWriter(user, true);
			PrintWriter out = new PrintWriter(outfile);
			user.createNewFile();

			out.println("["+worldname+"]"+playername + " Command: " + msg + " " + " " + "(" + (int)x + " " + (int)y + " " + (int)z + ") ("+getTimestamp()+")");
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}}


	private static String getTimestamp() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	public static Boolean getLowercase() {
		if (getConfig.PlayerNamestoLowerCase()) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean getStaff() {
		if (getConfig.SeparateFolderforStaff()) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean getOnlyStaff() {
		if (getConfig.LogOnlyStaff()) {
			return true;
		} else {
			return false;
		}
	}

}
