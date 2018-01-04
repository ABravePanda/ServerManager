package me.abravepanda.servermanager.commands.methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;

public class HelpBuilder {

	public static String prefix = Main.PREFIX;

	public static String Helphelp = ChatColor.AQUA + "/ServerManager help" + ChatColor.GOLD + " Show help menu.";
	public static String HelpVersion = ChatColor.AQUA + "/ServerManager version" + ChatColor.GOLD
			+ " Show plugin version.";
	public static String HelpMenu = ChatColor.AQUA + "/ServerManager menu" + ChatColor.GOLD
			+ " Show ServerManager GUI.";
	public static String HelpInfo = ChatColor.AQUA + "/ServerManager info" + ChatColor.GOLD
			+ " Learn more about ServerManager";
	public static String HelpLeave = ChatColor.AQUA + "/ServerManager leave" + ChatColor.GOLD + " Fake Leave.";
	public static String HelpJoin = ChatColor.AQUA + "/ServerManager join" + ChatColor.GOLD + " Fake Join.";
	public static String HelpCC = ChatColor.AQUA + "/cc" + ChatColor.GOLD + " Clear global chat.";
	public static String HelpPCC = ChatColor.AQUA + "/pcc [player]" + ChatColor.GOLD + " Clear a player's chat.";
	public static String HelpSCC = ChatColor.AQUA + "/scc" + ChatColor.GOLD + " Clear your own chat.";
	public static String HelpOn = ChatColor.AQUA + "/chat on" + ChatColor.GOLD + " Enable chat.";
	public static String HelpOff = ChatColor.AQUA + "/chat off" + ChatColor.GOLD + " Disable chat.";
	public static String HelpBC = ChatColor.AQUA + "/broadcast [msg]" + ChatColor.GOLD + " Broadcast a message.";
	public static String HelpNBC = ChatColor.AQUA + "/namebroadcast [msg]" + ChatColor.GOLD + " Broadcast a message.";
	public static String HelpMute = ChatColor.AQUA + "/mute [player]" + ChatColor.GOLD + " Mute someone."
			+ ChatColor.RED + " TEMP REMOVED";
	public static String HelpUnmute = ChatColor.AQUA + "/unmute [player]" + ChatColor.GOLD + " Unmute someone."
			+ ChatColor.RED + " TEMP REMOVED";
	public static String HelpMsgPeek = ChatColor.AQUA + "/msgpeek [on|off]" + ChatColor.GOLD
			+ " Enable/Disable Message Peek.";
	public static String HelpWarn = ChatColor.AQUA + "/warn [player] [reason]" + ChatColor.GOLD + " Warn someone."
			+ ChatColor.RED + " TEMP REMOVED";
	public static String HelpWarns = ChatColor.AQUA + "/warns <player>" + ChatColor.GOLD + " Check someone's warnings."
			+ ChatColor.RED + " TEMP REMOVED";
	public static String HelpDelWarn = ChatColor.AQUA + "/delwarn [player]" + ChatColor.GOLD
			+ " Remove someone's warnings." + ChatColor.RED + " TEMP REMOVED";

	public static void buildHelp(Player p) {
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&m&l-----------------------------------"));
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l     [ServerManager Help]"));
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(prefix + Helphelp);
		p.sendMessage(prefix + HelpVersion);
		if (p.hasPermission(Permissions.useGUI)) {
			p.sendMessage(prefix + HelpMenu);
		} else {
		}
		p.sendMessage(prefix + HelpInfo);
		if (p.hasPermission(Permissions.fakeJoin)) {
			p.sendMessage(prefix + HelpJoin);
		} else {
		}
		if (p.hasPermission(Permissions.fakeQuit)) {
			p.sendMessage(prefix + HelpLeave);
		} else {
		}
		if (p.hasPermission(Permissions.globalChatClear)) {
			p.sendMessage(prefix + HelpCC);
		} else {
		}
		if (p.hasPermission(Permissions.playerChatClear)) {
			p.sendMessage(prefix + HelpPCC);
		} else {
		}
		p.sendMessage(prefix + HelpSCC);
		if (p.hasPermission(Permissions.chatOn)) {
			p.sendMessage(prefix + HelpOn);
		} else {
		}
		if (p.hasPermission(Permissions.chatOff)) {
			p.sendMessage(prefix + HelpOff);
		} else {
		}
		if (p.hasPermission(Permissions.broadcast)) {
			p.sendMessage(prefix + HelpBC);
		} else {
		}
		if (p.hasPermission(Permissions.nameBroadcast)) {
			p.sendMessage(prefix + HelpNBC);
		} else {
		}
		if (p.hasPermission("mute.ServerManager")) {
			p.sendMessage(prefix + HelpMute);
		} else {
		}
		if (p.hasPermission("unmute.ServerManager")) {
			p.sendMessage(prefix + HelpUnmute);
		} else {
		}
		if (p.hasPermission(Permissions.messagePeek)) {
			p.sendMessage(prefix + HelpMsgPeek);
		} else {
		}
		if (p.hasPermission("warn.ServerManager")) {
			p.sendMessage(prefix + HelpWarn);
		} else {
		}
		if (p.hasPermission("checkwarns.ServerManager")) {
			p.sendMessage(prefix + HelpWarns);
		} else {
		}
		if (p.hasPermission("delwarn.ServerManager")) {
			p.sendMessage(prefix + HelpDelWarn);
		} else {
		}
		p.sendMessage(" ");
		p.sendMessage(ChatColor.RED + "       " + ChatColor.BOLD + ChatColor.UNDERLINE + "Key:");
		p.sendMessage("");
		p.sendMessage(ChatColor.AQUA + "          " + ChatColor.BOLD + "Aqua:" + ChatColor.GREEN + " Command.");
		p.sendMessage(ChatColor.GOLD + "          " + ChatColor.BOLD + "Gold:" + ChatColor.GREEN + " Description.");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&m&l-----------------------------------"));
	}
}
