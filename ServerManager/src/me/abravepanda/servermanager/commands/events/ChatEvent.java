package me.abravepanda.servermanager.commands.events;

import java.io.File;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;

public class ChatEvent implements Listener {

	String prefix = Main.PREFIX;

	private Main plugin;

	public ChatEvent(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		UUID id = e.getPlayer().getUniqueId();

		File playerFile = plugin.getPlayerFile(id);
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

		if (!p.hasPermission(Permissions.overrideChat)) {
			if (playerData.get("MuteSystem.muted").equals("True")) {
				e.setCancelled(true);
				p.sendMessage(prefix + Messages.muted.replace("{name}", p.getName()));
			}
		}

		if (Main.chatEnabled == false) {
			if (!p.hasPermission(Permissions.overrideChat)) {
				e.setCancelled(true);
				p.sendMessage(Messages.ChatsOff.replace("{name}", p.getName()));
			}
		}

	}

}
