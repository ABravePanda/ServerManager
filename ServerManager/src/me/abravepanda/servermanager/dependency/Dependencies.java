package me.abravepanda.servermanager.dependency;

import org.bukkit.plugin.PluginManager;

public class Dependencies {

	public static boolean pluginEnabled(PluginManager pm, String pluginName) {
		if (pm.getPlugin(pluginName) != null) {
			return true;
		}
		return false;
	}

}
