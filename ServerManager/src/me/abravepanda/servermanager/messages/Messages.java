package me.abravepanda.servermanager.messages;

import java.util.List;

import org.bukkit.ChatColor;

import me.abravepanda.servermanager.configmethods.Config;

public class Messages {

	// Main
	public static String Prefix = ChatColor.translateAlternateColorCodes('&', Config.getConfigString("Prefix"))
			+ ChatColor.RESET + " ";
	public static String NoPermission = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("NoPermission")) + ChatColor.RESET;

	// Message
	public static String NotOnlineMessage = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("MessageFormatNotOnline")) + ChatColor.RESET;
	public static String MessageToTarget = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("MessageFormatTo")) + ChatColor.RESET;
	public static String MessageToSender = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("MessageFormatFrom")) + ChatColor.RESET;
	public static String MessageToAdmin = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("MessageFormatToAdmin")) + ChatColor.RESET;

	// Broadcast
	public static String NoMessage = ChatColor.translateAlternateColorCodes('&', Config.getConfigString("NoMessage"))
			+ ChatColor.RESET;
	public static String Color = ChatColor.translateAlternateColorCodes('&', Config.getConfigString("Color"))
			+ ChatColor.RESET;

	// Name Broadcast
	public static String NamePart = ChatColor.translateAlternateColorCodes('&', Config.getConfigString("NamePart"))
			+ ChatColor.RESET;

	// GlobalChatClear
	public static String GlobalToChat = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("GlobalToChat")) + ChatColor.RESET;
	public static String GlobalToSender = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("GlobalToSender")) + ChatColor.RESET;

	// SelfChatClear
	public static String SelfToSender = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("SelfToSender")) + ChatColor.RESET;

	// PlayerChatClear
	public static String PlayerToSender = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("PlayerToSender")) + ChatColor.RESET;
	public static String PlayerToTarget = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("PlayerToTarget")) + ChatColor.RESET;

	// Chat
	public static String ChatEnabled = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("ChatEnabledMessage")) + ChatColor.RESET;
	public static String ChatDisabled = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("ChatDisabledMessage")) + ChatColor.RESET;
	public static String ChatsOff = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("ChatOffMessage")) + ChatColor.RESET;

	// Notify
	public static String notifyMessage = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("NotifyMessageChat")) + ChatColor.RESET;

	// Swear
	public static List<String> swears = Config.getConfigStringList("Swears");
	public static String dontSwear = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("NoSwearMessage")) + ChatColor.RESET;
	public static String dontSwearAdmin = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("NoSwearAdmin")) + ChatColor.RESET;

	// Chat Delay
	public static String warnMessage = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("warnMessage")) + ChatColor.RESET;

	// StaffChat
	public static String staffChatFormat = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("StaffChatFormat")) + ChatColor.RESET;
	public static String staffHelpFormat = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("StaffHelpFormat")) + ChatColor.RESET;
	public static String staffHelpSuccess = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("StaffHelpSuccess")) + ChatColor.RESET;

	// Format
	public static String customFormat = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("ChatFormat"));

	// Mute
	public static String muteToStaff = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("MuteToStaff"));
	public static String muteToPlayer = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("MuteToPlayer"));

	// UnMute
	public static String unmuteToStaff = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("UnmuteToStaff"));
	public static String unmuteToPlayer = ChatColor.translateAlternateColorCodes('&',
			Config.getConfigString("UnmuteToPlayer"));

	// Mute Event
	public static String muted = ChatColor.translateAlternateColorCodes('&', Config.getConfigString("TalkDenyMessage"));
}
