package me.abravepanda.servermanager.commands.methods;

public class StringBuilderClass {

	public static String message(String[] args) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			builder.append(args[i]);
			builder.append(" ");
		}
		return builder.toString().trim();
	}

}
