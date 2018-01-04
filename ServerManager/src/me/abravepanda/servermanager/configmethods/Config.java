package me.abravepanda.servermanager.configmethods;

import java.io.IOException;
import java.util.List;

import me.abravepanda.servermanager.Main;

public class Config {

	public static void setConfigLine(String path, String newString) throws IOException {
		Main.instance.getConfig().set(path, newString);
		Main.instance.getConfig().save("config.yml");
	}

	public static String getConfigString(String path) {
		String string = Main.instance.getConfig().getString(path);
		return string;
	}

	public static List<String> getConfigStringList(String path) {
		List<String> string = Main.instance.getConfig().getStringList(path);
		return string;
	}

	public static int getConfigInt(String path) {
		int Int = Main.instance.getConfig().getInt(path);
		return Int;
	}

	public static Boolean getConfigBoolean(String path) {
		Boolean theboolean = Main.instance.getConfig().getBoolean(path);
		return theboolean;
	}

	public static int getDelay(String path, int def) {
		int chatDelay = Main.instance.getConfig().getInt(path, def);
		return chatDelay;
	}

}
