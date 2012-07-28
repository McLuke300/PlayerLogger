PlayerLogger
============

Version: 2.3
CraftBukkit Build 1.2.5-R4.0 [Tested]

Info
Info This plugin lets you store every block place, block break, chat msg, command, enchant, kill, sign text etc. So, if you need proof of something just look them up! You can turn on and off what you actually want or don't want to log, in the main configuration file! The main developer is Kuuichi. I am just continuing and adding stuff. I am obligated to continue this plugin. The forum is http://forums.bukkit.org/threads/admn-playerlogger-v0-7-logs-of-your-players-1-2-5-r0-1.58959/ and has been accepted.



 
Features:
Chat Logging
IP Logging ( Implemented by Mcluke300 )
Join Logging
Quit Logging ( Implemented by Mcluke300 )
Command Logging
Death Logging
Console Logging ( Implemented by Mcluke300 )
Blacklist Block Logging ( Implemented by Mcluke300 )
Sign Text Logging ( Implemented by Mcluke300 )
Pvp Logging ( Implemented by Mcluke300 )
Enchantment Loggin ( Implemented by Mcluke300 )
Commands not to log ( Implemented by Mcluke300 )
Bucket Logging ( Implemented by Mcluke300 )
Metrics



Log Example
[World One]McLuke300 joined: 192.168.1.101 (-27 64 220) (06-26-2012 20:03:48)

[World One]McLuke300 Killed TestyWestie (-18 64 220) (06-26-2012 19:37:30)

[World One]McLuke300 said: Example chat (-21 64 220) (06-26-2012 20:02:06)

[World One]McLuke300 command: /home (-20 64 219) (06-26-2012 20:02:08)

[World One]McLuke300 Sign: [McLukes][Home][][] (-22 64 222) (06-26-2012 20:02:22)

[World One]McLuke300 command: /give McLuke300 46 1 (-22 64 223) (06-26-2012 20:02:26)

[World One]McLuke300 Placed: TNT (-26 64 218) (06-26-2012 20:02:28)

[World One]McLuke300 Placed: TNT (-26 64 219) (06-26-2012 20:02:29)

[World One]McLuke300 command: /god (-32 64 212) (06-26-2012 20:02:36)

[World One]McLuke300 command: /suicide (-26 64 216) (06-26-2012 20:02:39)

[World One]McLuke300 Died. (-26 64 216) (06-26-2012 20:02:40)

[World One]06-26-2012 20:03:21 [McLuke300]ItemStack{STONE_SWORD x 1} {Enchantment[16, DAMAGE_ALL]=1} Xp Cost:3

[World One]McLuke300 Emptied Bucket of Water (-26 63 224) (06-26-2012 20:08:53)

[World One]McLuke300 Emptied Bucket of Lava (-26 63 221) (06-26-2012 20:08:54)

[World One]McLuke300 quit: 06-26-2012 20:03:45)

[Console]kick McLuke300 05-27 03:46:42



Config
Log:
//Log Joins
  PlayerJoins: true
// Log Player quitting
  PlayerQuit: true 
//Log Chat
  PlayerChat: true
//Log all Player commands
  PlayerCommands: true 
//Log Player deaths
  PlayerDeaths: true 
//Log enchantments
  PlayerEnchants: true 
//Log Pvp
  Pvp: true 
 //Log Buckets Placed
  PlayerBucketPlace: true 
 //Log Console commands
  ConsoleCommands: true
//Log Player sign text
  PlayerSignText: true 
//If this is true it will make the folder Playerlogger/Staff and all players with the Playerlogger.staff permission will be in there not Users
  SeparateFolderforStaff: true
//False by default- Useful for offline servers where players can change capitalization in name and have a different file. All file names are in lowercase
  PlayerNamestoLowerCase: false
BlackList:
  LogBlackListedBlocks: true //Enables logging of blacklisted blocks
  Blocks:
  - '7' //Bedrock Block id
  - '46'
  - '57'
  - '*' //This will log all block place and breaks
Commands:
  BlackListCommands: true //Enables Command Blacklisting (eg.wont log)
  CommandsToBlock:
  - /login //Wont log /login
  - /changepassword
  - /register
  
  
  
Changelog
Version 0.1

Update of plugin
Version 0.2

Added coordinates
Version 0.3

Updated to 1.1-R4
Fixed listeners to implements Listener
Version 0.4

Updated to 1.1-R6
Version 0.5

Updated to 1.1-R7
Version 0.6

Updated to 1.2.3-R0.1
Version 0.7

Updated to 1.2.4-R0.1
Error fixes
Version 0.8

Updated to 1.2.5-R0.1
Rounded coordinates
Version 0.9

Updates made by Mcluke300 ( will add soon )
Version 1.0

Added Console logging in Playerlogger/ConsoleCommand
Fixed Could not load (Javs fault for adding me in authors in pluign.yml)
Updated source in jar
Updated to 1.2.5-R3.0
Version 2.0

All updated by McLuke300
Complete rewrite of plugin
New config (Make sure to delete old config!)
Block logging readded with blacklists
Bucket placed logging
Sign text now logged
Player commands can be blacklisted eg. /login could be not logged
Logs Player vs Player kills
Added Metrics to plugin
Added option to disable Console logging
Cleaner Code and more organized
Version 2.1

All updated by McLuke300
Fixed no logging error caused by the Time Format written incorrectly
Added all block logging simply add * to the blacklist of blocks
Version 2.2

All updated by McLuke300
It now wont give file not found errors as it will load or create new one ever event
Command blacklisting fixed so it checks the first bit of command and will stop it from being logged.
Commands now log when blacklistcommands is false (Bug fix)(Reported by vk222u)
Version set to right version in plugin.yml
Version 2.2.1

All updated by McLuke300 Bug Fix
Blocks logging that aren't in the blacklist fixed
Version 2.3

All updated by McLuke300 Bug Fix:
When "*" Block breaks not logging fixed. New Features:
New Permission Playerlogger.staff so that those players go in a separate folder Playerlogger/staff/McLuke300.properties (So you can separately log staff and players for easy access if a staff member has abused the commands. This can also be used for any group not just staff.)
Added ability for all file names to be in lowercase, option in config. (Useful for offline servers where players can change capitalization in name and have a different file.)
Added ability for Staff in separate files to be turned off in config.