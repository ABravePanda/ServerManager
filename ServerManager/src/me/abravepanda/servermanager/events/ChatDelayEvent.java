package me.abravepanda.servermanager.events;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.messages.Messages;

public class ChatDelayEvent implements Listener {

	String prefix = Main.PREFIX;
	String warnMessage = Messages.warnMessage;

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		UUID id = event.getPlayer().getUniqueId();
		Player p = event.getPlayer();

		if (!Main.delayMap.containsKey(id)) {
			Main.delayMap.put(id, System.currentTimeMillis());
			return;
		}

		long lastChat = Main.delayMap.get(id);
		long now = System.currentTimeMillis();

		long diff = now - lastChat;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		int time = (int) (TimeUnit.MILLISECONDS.toSeconds(Config.getDelay("chatDelay", 2500)) - seconds);

		if (!(diff >= Config.getDelay("chatDelay", 2500))) {

			if (p.hasPermission(Permissions.overrideChat)) {
				return;
			} else {
				event.setCancelled(true);
			}

			if (warnMessage != null && !warnMessage.isEmpty()) {
				if (Main.chatEnabled == true) {

					if (time == 1) {
						String message = warnMessage.replace("{name}", event.getPlayer().getName())
								.replace("[seconds]", "second").replace("{time}", Integer.toString(time));
						message = ChatColor.translateAlternateColorCodes('&', message);
						event.getPlayer().sendMessage(prefix + message);
					} else if (time > 1 || time == 0) {
						String message = warnMessage.replace("{name}", event.getPlayer().getName())
								.replace("[seconds]", "seconds").replace("{time}", Integer.toString(time));
						message = ChatColor.translateAlternateColorCodes('&', message);
						event.getPlayer().sendMessage(prefix + message);
					}

				} else {
					return;
				}

			}

			return;
		}

		Main.delayMap.put(id, System.currentTimeMillis());
	}

}
