package me.abravepanda.servermanager.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.updater.Updater;

public class PlayerJoinStatsEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		File playerFile = Main.instance.getPlayerFile(p.getUniqueId());
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

		playerData.set("Stats.TotalLogins", playerData.getInt("Stats.totalLogins") + 1);

		playerData.set("PlayerInfo.Username", p.getName());
		playerData.set("PlayerInfo.UUID", p.getUniqueId().toString());
		try {
			playerData.save(playerFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (playerData.get("MuteSystem.muted") == null) {
			playerData.set("MuteSystem.muted", "False");
			try {
				playerData.save(playerFile);
			} catch (IOException e11) {
				e11.printStackTrace();
			}
		}
		if (playerData.get("MuteSystem.mutedcount") == null) {
			playerData.set("MuteSystem.mutedcount", (int) 0);
			try {
				playerData.save(playerFile);
			} catch (IOException e11) {
				e11.printStackTrace();
			}
		}
		if (playerData.getInt("WarnSystem.WarningsCount") == 0) {
			playerData.set("WarnSystem.WarningsCount", 0);
			try {
				playerData.save(playerFile);
			} catch (IOException e11) {
				e11.printStackTrace();
			}
		}
		if (playerData.get("MessagePeek.Enabled") == null) {
			playerData.set("MessagePeek.Enabled", "False");
			try {
				playerData.save(playerFile);
			} catch (IOException e11) {
				e11.printStackTrace();
			}
		}

		/*
		 * if (p.isOp()) { p.sendMessage(intro +
		 * ChatColor.translateAlternateColorCodes('&',
		 * "&aChatManager Is Running Version: " + version2)); if (ua == true) {
		 * checkUpdate(); final Updater updater = new Updater(this, 16452,
		 * false); p.sendMessage(intro + ChatColor.RED +
		 * "You're Running An Outdated Version (" + version2 +
		 * "), Please Check: " + ChatColor.GOLD +
		 * "https://www.spigotmc.org/resources/chatmanager.16452/ " +
		 * ChatColor.RED + "for newest version: " + ChatColor.GOLD +
		 * updater.getVersion()); } else { p.sendMessage(intro + ChatColor.GREEN
		 * + "No Updates Available!"); }; }
		 */
	}

}
