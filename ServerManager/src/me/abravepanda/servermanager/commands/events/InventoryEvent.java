package me.abravepanda.servermanager.commands.events;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.commands.methods.HelpBuilder;
import me.abravepanda.servermanager.messages.Messages;
import net.md_5.bungee.api.ChatColor;

public class InventoryEvent implements Listener {

	String prefix = Main.PREFIX;
	HashMap<UUID, String> otherChatClear = new HashMap<UUID, String>();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked(); // The player that clicked
													// the item
		ItemStack clicked = event.getCurrentItem(); // The item that was clicked
		Inventory inventory = event.getInventory(); // The inventory that was
													// clicked in

		if (inventory.getName().equals(prefix + ChatColor.GOLD + "Help Menu")) {
			if (clicked.getType() == Material.BARRIER) {
				event.setCancelled(true);
				p.closeInventory();
			}
			if (clicked.getType() == Material.NETHER_STAR) {
				HelpBuilder.buildHelp(p);
				event.setCancelled(true);
				p.closeInventory();
			}
			if (clicked.getType() == Material.NAME_TAG) {
				event.setCancelled(true);
				p.closeInventory();
				if (p.hasPermission(Permissions.playerChatClear)) {
					otherChatClear.put(p.getUniqueId(), "Other");
					p.sendMessage(prefix + ChatColor.GOLD + "Please Enter Player's Username.");
				} else {
					p.sendMessage(prefix + Messages.NoPermission);
				}
			}
			if (clicked.getType() == Material.EMPTY_MAP) {
				for (int i = 0; i <= 250; i++) {
					p.sendMessage("");
				}
				p.sendMessage(prefix + Messages.SelfToSender.replace("{name}", p.getName()));
				event.setCancelled(true);
				p.closeInventory();
			}
			if (clicked.getType() == Material.EMERALD_BLOCK) {
				if (p.hasPermission(Permissions.fakeJoin)) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " joined the game");
					for (Player ps : Bukkit.getOnlinePlayers()) {
						if (ps.isOp()) {
							p.sendMessage(prefix + ChatColor.RED + p.getName() + " fake joined.");
						}
					}
				} else {
					p.sendMessage(prefix + Messages.NoPermission);
				}
				event.setCancelled(true);
				p.closeInventory();
			}
			if (clicked.getType() == Material.REDSTONE_BLOCK) {
				if (p.hasPermission(Permissions.fakeQuit)) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " left the game");
					for (Player ps : Bukkit.getOnlinePlayers()) {
						if (ps.isOp()) {
							p.sendMessage(prefix + ChatColor.RED + p.getName() + " fake quit.");
						}
					}
				} else {
					p.sendMessage(prefix + Messages.NoPermission);
				}
				event.setCancelled(true);
				p.closeInventory();
			}
			if (clicked.getType() == Material.BOOK_AND_QUILL) {
				if (p.hasPermission(Permissions.globalChatClear)) {

					for (Player ps : Bukkit.getOnlinePlayers()) {

						for (int i = 0; i <= 250; i++) {
							ps.sendMessage("");
						}
						ps.sendMessage(prefix + Messages.GlobalToChat.replace("{name}", p.getName()));
					}
					p.sendMessage(prefix + Messages.GlobalToSender.replace("{name}", p.getName()));
				} else {

					p.sendMessage(prefix + Messages.NoPermission);
				}
				event.setCancelled(true);
				p.closeInventory();
			}
			if (clicked.getType() == Material.SLIME_BALL) {
				event.setCancelled(true);
				p.closeInventory();
				for (Player ps : Bukkit.getOnlinePlayers()) {
					if (p.hasPermission(Permissions.chatOn)) {
						ps.sendMessage(prefix + Messages.ChatEnabled.replace("{name}", p.getName()));
						Main.chatEnabled = true;
					} else {
						p.sendMessage(Messages.NoPermission);
					}
				}
			}
			if (clicked.getType() == Material.FIREBALL) {
				event.setCancelled(true);
				p.closeInventory();
				for (Player ps : Bukkit.getOnlinePlayers()) {
					if (p.hasPermission(Permissions.chatOff)) {
						ps.sendMessage(prefix + Messages.ChatDisabled.replace("{name}", p.getName()));
						Main.chatEnabled = false;
					} else {
						p.sendMessage(Messages.NoPermission);
					}
				}
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (otherChatClear.containsKey(p.getUniqueId()) && otherChatClear.containsValue("Other")) {
			e.setCancelled(true);
			Player target = Bukkit.getPlayerExact(e.getMessage());
			if (target == null) {
				p.sendMessage(prefix + ChatColor.RED + e.getMessage() + " Is Not Online!");
			} else {
				p.sendMessage(
						Messages.PlayerToSender.replace("{name}", p.getName()).replace("{target}", target.getName()));
				for (int i = 0; i <= 250; i++) {
					target.sendMessage("");
				}
				target.sendMessage(prefix
						+ Messages.PlayerToTarget.replace("{name}", p.getName()).replace("{target}", target.getName()));
			}
			otherChatClear.remove(p.getUniqueId());
		}
	}

}
