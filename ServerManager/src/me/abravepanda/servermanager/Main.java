package me.abravepanda.servermanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.abravepanda.servermanager.commands.CommandBroadcast;
import me.abravepanda.servermanager.commands.CommandChat;
import me.abravepanda.servermanager.commands.CommandServerManager;
import me.abravepanda.servermanager.commands.CommandUnmute;
import me.abravepanda.servermanager.commands.CommandGlobalChatClear;
import me.abravepanda.servermanager.commands.CommandMessage;
import me.abravepanda.servermanager.commands.CommandMessagePeek;
import me.abravepanda.servermanager.commands.CommandMute;
import me.abravepanda.servermanager.commands.CommandNameBroadcast;
import me.abravepanda.servermanager.commands.CommandPlayerChatClear;
import me.abravepanda.servermanager.commands.CommandSelfChatClear;
import me.abravepanda.servermanager.commands.events.ChatEvent;
import me.abravepanda.servermanager.commands.events.InventoryEvent;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.dependency.Dependencies;
import me.abravepanda.servermanager.events.ChatDelayEvent;
import me.abravepanda.servermanager.events.ChatFormatEvent;
import me.abravepanda.servermanager.events.NotifyPlayerEvent;
import me.abravepanda.servermanager.events.PlayerChatStaffEvent;
import me.abravepanda.servermanager.events.PlayerJoinStatsEvent;
import me.abravepanda.servermanager.events.PlayerMoveStatsEvent;
import me.abravepanda.servermanager.events.PlayerSwearEvent;
import me.abravepanda.servermanager.events.SignEvents;
import me.abravepanda.servermanager.events.TabCompleteEvent;
import me.abravepanda.servermanager.metrics.Metrics;
import me.abravepanda.servermanager.updater.Updater;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

	public static String PREFIX;

	public static Main instance;

	public static boolean chatEnabled = true;

	public static boolean updateNeeded;

	public static HashMap<UUID, Long> delayMap = new HashMap<>();

	ConsoleCommandSender log = Bukkit.getConsoleSender();

	@Override
	public void onEnable() {

		log.sendMessage("");
		log.sendMessage(ChatColor.AQUA + "----------ServerManager-Start---------");
		log.sendMessage("");

		createConfig();
		instance = this;

		PREFIX = ChatColor.translateAlternateColorCodes('&', Config.getConfigString("Prefix")) + ChatColor.RESET + " ";

		// Commands
		log.sendMessage(ChatColor.YELLOW + "Registering Commands...");

		this.getCommand("msg").setExecutor(new CommandMessage());
		this.getCommand("broadcast").setExecutor(new CommandBroadcast(this));
		this.getCommand("namebroadcast").setExecutor(new CommandNameBroadcast(this));
		this.getCommand("cc").setExecutor(new CommandGlobalChatClear());
		this.getCommand("scc").setExecutor(new CommandSelfChatClear());
		this.getCommand("pcc").setExecutor(new CommandPlayerChatClear());
		this.getCommand("chat").setExecutor(new CommandChat());
		this.getCommand("ServerManager").setExecutor(new CommandServerManager(this));
		this.getCommand("msgpeek").setExecutor(new CommandMessagePeek());
		this.getCommand("mute").setExecutor(new CommandMute(this));
		this.getCommand("unmute").setExecutor(new CommandUnmute(this));

		log.sendMessage(ChatColor.GREEN + "Commands Registered");

		log.sendMessage("");

		// Events
		log.sendMessage(ChatColor.YELLOW + "Registering Events...");

		this.getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
		this.getServer().getPluginManager().registerEvents(new NotifyPlayerEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerSwearEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ChatDelayEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoinStatsEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMoveStatsEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChatStaffEvent(), this);
		this.getServer().getPluginManager().registerEvents(new SignEvents(), this);
		this.getServer().getPluginManager().registerEvents(new ChatFormatEvent(), this);

		log.sendMessage(ChatColor.GREEN + "Events Registered");

		log.sendMessage("");

		log.sendMessage(PREFIX + ChatColor.GREEN + "Plugin Has Been Enabled.");
		log.sendMessage(PREFIX + ChatColor.YELLOW + "Plugin By:" + ChatColor.GOLD + " A_Brave_Panda");

		Metrics metrics = new Metrics(this);

		log.sendMessage("");
		log.sendMessage(ChatColor.AQUA + "----------ServerManager-Dependencies---------");
		log.sendMessage("");

		PluginManager pm = getServer().getPluginManager();

		boolean PexEnabled = Dependencies.pluginEnabled(pm, "PermissionsEx");
		boolean FactionsEnabled = Dependencies.pluginEnabled(pm, "Factions");
		if (PexEnabled == true) {
			log.sendMessage(PREFIX + ChatColor.GREEN + "PermissionsEX Loaded.");
		} else {
			log.sendMessage(PREFIX + ChatColor.RED + "PermissionsEX Not Loaded.");
		}
		if (FactionsEnabled == true) {
			log.sendMessage(PREFIX + ChatColor.GREEN + "Factions Loaded.");
		} else {
			log.sendMessage(PREFIX + ChatColor.RED + "Factions Not Loaded.");
		}

		log.sendMessage("");
		log.sendMessage(ChatColor.AQUA + "----------ServerManager-Updates---------");
		log.sendMessage("");
		this.checkUpdate();
		log.sendMessage("");
		log.sendMessage(ChatColor.AQUA + "----------ServerManager-End---------");
		log.sendMessage("");
	}

	@Override
	public void onDisable() {

	}

	private void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				saveDefaultConfig();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void checkUpdate() {
		final Updater updater = new Updater(this, 16452, false);
		final Updater.UpdateResult result = updater.getResult();
		switch (result) {
		case FAIL_SPIGOT: {
			log.sendMessage(PREFIX + ChatColor.RED + "Couldn't  contact spigot.");
			break;
		}
		case NO_UPDATE: {
			log.sendMessage(PREFIX + ChatColor.GREEN + "Up to date, version: " + this.getDescription().getVersion());
			updateNeeded = false;
			break;
		}
		case FAIL_NOVERSION: {
			log.sendMessage(PREFIX + ChatColor.RED + "No versions available!");
			break;
		}
		case BAD_RESOURCEID: {
			log.sendMessage(PREFIX + ChatColor.RED + "Incorrect resource id!");
			break;
		}
		case UPDATE_AVAILABLE: {
			log.sendMessage(PREFIX + ChatColor.RED + "An update is available! Version: " + updater.getVersion());
			updateNeeded = true;
			break;

		}

		}
	}

	public File getPlayerFile(UUID playerName) {

		File playerFile = new File(getDataFolder() + File.separator + "PlayerData", playerName + ".yml");
		return playerFile;
	}
}
