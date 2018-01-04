package me.abravepanda.servermanager.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.messages.Messages;

public class NotifyPlayerEvent implements Listener {

	String prefix = Main.PREFIX;

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player p = event.getPlayer();

		if (Config.getConfigBoolean("NotifyMessageEnabled") == true) {
			for (Player ps : Bukkit.getOnlinePlayers()) {
				String player = ps.getName();
				Player pp = Bukkit.getServer().getPlayer(player);

				if (message.toLowerCase().contains(player.toLowerCase())) {

					String nm = ChatColor.translateAlternateColorCodes('&', Messages.notifyMessage
							.replace("{sendername}", p.getName()).replace("{recievername}", pp.getName()));
					pp.sendMessage(prefix + nm);
					pp.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0f, 2.0f);
				}

			}
		}

	}
}
