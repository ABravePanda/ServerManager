package me.abravepanda.servermanager.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;

public class SignEvents implements Listener {

	String prefix = Main.PREFIX;

	// Create
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getLine(1).equalsIgnoreCase("Global") && e.getLine(0).equalsIgnoreCase("[ServerManager]")) {
			if (p.hasPermission(Permissions.createSign)) {
				e.setLine(0, ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
						+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]");
				e.setLine(1, ChatColor.GOLD + "" + ChatColor.BOLD + "Global");
				p.sendMessage(prefix + ChatColor.GOLD + "You have sucessfully made a " + ChatColor.BOLD
						+ "ServerManager Global Sign");
			} else {
				p.sendMessage(prefix + Messages.NoPermission);
			}
		}
		if (e.getLine(1).equalsIgnoreCase("Self") && e.getLine(0).equalsIgnoreCase("[ServerManager]")) {
			if (p.hasPermission(Permissions.createSign)) {
				e.setLine(0, ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
						+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]");
				e.setLine(1, ChatColor.GOLD + "" + ChatColor.BOLD + "Self");
				p.sendMessage(prefix + ChatColor.GOLD + "You have sucessfully made a " + ChatColor.BOLD
						+ "ServerManager Self Sign");
			} else {
				p.sendMessage(prefix + Messages.NoPermission);
			}
		}
		if (e.getLine(1).equalsIgnoreCase("Chat") && e.getLine(0).equalsIgnoreCase("[ServerManager]")
				&& e.getLine(2).equalsIgnoreCase("On")) {
			if (p.hasPermission(Permissions.createSign)) {
				e.setLine(0, ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
						+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]");
				e.setLine(1, ChatColor.GOLD + "" + ChatColor.BOLD + "Chat");
				e.setLine(2, ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				p.sendMessage(prefix + ChatColor.GOLD + "You have sucessfully made a " + ChatColor.BOLD
						+ "ServerManager Chat On Sign");
			} else {
				p.sendMessage(prefix + Messages.NoPermission);

			}
		}
		if (e.getLine(1).equalsIgnoreCase("Chat") && e.getLine(0).equalsIgnoreCase("[ServerManager]")
				&& e.getLine(2).equalsIgnoreCase("Off")) {
			if (p.hasPermission(Permissions.createSign)) {
				e.setLine(0, ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
						+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]");
				e.setLine(1, ChatColor.GOLD + "" + ChatColor.BOLD + "Chat");
				e.setLine(2, ChatColor.RED + "" + ChatColor.BOLD + "Off");
				p.sendMessage(prefix + ChatColor.GOLD + "You have sucessfully made a " + ChatColor.BOLD
						+ "ServerManager Chat Off Sign");
			} else {
				p.sendMessage(prefix + Messages.NoPermission);
			}
		}
	}

	// Using Signs
	@EventHandler
	public void SignClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) { // Checks if person is
															// right clicking on
															// a block
			if (e.getClickedBlock().getState() instanceof Sign) { // This checks
																	// if the
																	// player is
																	// right
																	// clicking
																	// on the
																	// sign and
																	// it'll do
																	// all the
																	// code
																	// below if
																	// it is a
																	// sign.
				Sign sign = (Sign) e.getClickedBlock().getState();
				if (sign.getLine(0)
						.equalsIgnoreCase(ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
								+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]")
						&& sign.getLine(1).equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Global")) {
					if (p.hasPermission(Permissions.globalChatClear)) {
						for (int i = 0; i <= 250; i++) {
							Bukkit.broadcastMessage("");
						}
						String gtochat = Messages.GlobalToChat.replace("{name}", p.getName());
						Bukkit.broadcastMessage(prefix + gtochat);
					} else {
						p.sendMessage(prefix + Messages.NoPermission.replace("{name}", p.getName()));
					}
				}
				if (sign.getLine(0)
						.equalsIgnoreCase(ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
								+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]")
						&& sign.getLine(1).equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Self")) {
					for (int i = 0; i <= 250; i++) {
						p.sendMessage("");
					}
					String ptoself = Messages.SelfToSender.replace("{name}", p.getName());
					p.sendMessage(prefix + ptoself);
				}
				if (sign.getLine(0)
						.equalsIgnoreCase(ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
								+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]")
						&& sign.getLine(1).equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chat")
						&& sign.getLine(2).equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "On")) {
					if (p.hasPermission(Permissions.chatOn)) {
						Main.chatEnabled = true;
						String chaton = Messages.ChatEnabled.replace("{name}", p.getName());
						Bukkit.broadcastMessage(prefix + chaton);
					} else {
						String noperm = Messages.NoPermission.replace("{name}", p.getName());
						p.sendMessage(prefix + noperm);
					}
				}
				if (sign.getLine(0)
						.equalsIgnoreCase(ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD
								+ "ServerManager" + ChatColor.GRAY + ChatColor.BOLD + "]")
						&& sign.getLine(1).equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Chat")
						&& sign.getLine(2).equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Off")) {
					if (p.hasPermission(Permissions.chatOff)) {
						Main.chatEnabled = false;
						String chaton = Messages.ChatDisabled.replace("{name}", p.getName());
						Bukkit.broadcastMessage(prefix + chaton);
					} else {
						String noperm = Messages.NoPermission.replace("{name}", p.getName());
						p.sendMessage(prefix + noperm);
					}
				}
			}
		}
	}
}
