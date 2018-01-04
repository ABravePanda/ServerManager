package me.abravepanda.servermanager.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.abravepanda.servermanager.Main;

public class PlayerMoveStatsEvent implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		File playerFile = Main.instance.getPlayerFile(p.getUniqueId());
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

		Location l = p.getLocation();
		int x = (int) l.getX();
		int y = (int) l.getY();
		int z = (int) l.getZ();
		String world = l.getWorld().getName();

		playerData.set("PlayerInfo.Location.X", x);
		playerData.set("PlayerInfo.Location.Y", y);
		playerData.set("PlayerInfo.Location.Z", z);
		playerData.set("PlayerInfo.Location.World", world);
		playerData.set("PlayerInfo.Stats.Gamemode", p.getGameMode().toString().toLowerCase());
		playerData.set("PlayerInfo.Stats.Health", p.getHealth());
		playerData.set("PlayerInfo.Stats.Hunger", p.getFoodLevel());
		playerData.set("PlayerInfo.Stats.Level", p.getLevel());
		try {
			playerData.save(playerFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
