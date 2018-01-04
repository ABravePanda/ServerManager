package me.abravepanda.servermanager.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.dependency.Dependencies;
import me.abravepanda.servermanager.messages.Messages;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatFormatEvent implements Listener {

	@EventHandler
	public void format(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();

		String pn = p.getName();
		if (Config.getConfigBoolean("ChatFormatEnabled") == true) {
			boolean FactionsEnabled = Dependencies.pluginEnabled(Main.instance.getServer().getPluginManager(),
					"Factions");
			boolean PEXEnabled = Dependencies.pluginEnabled(Main.instance.getServer().getPluginManager(),
					"PermissionsEx");
			if (FactionsEnabled == true && PEXEnabled == false) {
				MPlayer mplayer = MPlayer.get(p);
				Faction faction = mplayer.getFaction();
				String fn = faction.getName();
				String format = Messages.customFormat.replace("{name}", p.getName())
						.replace("{message}", event.getMessage()).replace("{faction}", fn);
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
				event.setFormat(format);
			}
			if (FactionsEnabled == false && PEXEnabled == true) {
				PermissionUser user = PermissionsEx.getUser(p);
				String prefix = user.getPrefix(p.getWorld().toString());
				String fixedprefix = ChatColor.translateAlternateColorCodes('&', prefix);
				String format = Messages.customFormat.replace("{name}", p.getName())
						.replace("{message}", event.getMessage()).replace("{prefix}", fixedprefix);
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
				event.setFormat(format);

			}
			if (FactionsEnabled == true && PEXEnabled == true) {
				PermissionUser user = PermissionsEx.getUser(p);
				String prefix = user.getPrefix(p.getWorld().toString());
				String fixedprefix = ChatColor.translateAlternateColorCodes('&', prefix);
				MPlayer mplayer = MPlayer.get(p);
				Faction faction = mplayer.getFaction();
				String fn = faction.getName();
				String format = Messages.customFormat.replace("{name}", p.getName())
						.replace("{message}", event.getMessage()).replace("{prefix}", fixedprefix)
						.replace("{faction}", fn);
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
				event.setFormat(format);

			}
		}
	}

}
