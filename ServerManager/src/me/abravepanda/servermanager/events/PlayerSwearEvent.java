package me.abravepanda.servermanager.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.messages.Messages;

public class PlayerSwearEvent implements Listener {

	String prefix = Main.PREFIX;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (Config.getConfigBoolean("AntiSwearEnabled") == true) {

			if (!e.getPlayer().hasPermission(Permissions.swearAllow)) {
				for (String word : e.getMessage().split(" ")) {
					if (Messages.swears.contains(word)) {

						e.setCancelled(true);
						String ns = ChatColor.translateAlternateColorCodes('&',
								Messages.dontSwear.replace("{name}", e.getPlayer().getName()).replace("{word}", word));
						String op = ChatColor.translateAlternateColorCodes('&', Messages.dontSwearAdmin
								.replace("{name}", e.getPlayer().getName()).replace("{word}", word));
						e.getPlayer().sendMessage(prefix + ns);
						for (Player ps : Bukkit.getOnlinePlayers()) {
							if (ps.hasPermission(Permissions.swearSee)) {
								ps.sendMessage(prefix + op);
							}
						}
					}
				}
			}
		}
	}

}
