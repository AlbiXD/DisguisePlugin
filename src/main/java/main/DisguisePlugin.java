package main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import commands.DisguiseCommand;
import commands.UnmaskCommand;
import data.ConfigEnums;
import data.ConfigManager;
import listener.PlayerInventory;
import listener.PlayerJoin;
import listener.PlayerKill;
import listener.PlayerPvPEvent;

public class DisguisePlugin extends JavaPlugin {
	public ConfigManager config;

	public static HashMap<String, String> playerData = new HashMap<>();

	@Override
	public void onEnable() {
		config = new ConfigManager(this, "config.yml");

		System.out.println(config.getConfig().getKeys(true));
		configValues();

		for (String i : config.getConfig().getKeys(true)) {
			if (i.contains("users.")) {
				playerData.put(i.substring(6), null);
			}

		}

		for (String i : playerData.keySet()) {
			if (config.getConfig().contains("users." + i)) {
				playerData.replace(i, null, config.getConfig().getString("users." + i));
			}

		}

		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lDisguise Plugin Enabled"));

		// Events
		Bukkit.getPluginManager().registerEvents(new PlayerInventory(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerKill(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPvPEvent(this), this);

		// Commands
		Bukkit.getPluginCommand("disguise").setExecutor(new DisguiseCommand(this));
		Bukkit.getPluginCommand("unmask").setExecutor(new UnmaskCommand());

	}

	@Override
	public void onDisable() {		
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lDisguise Plugin Disabled"));
		
		
		
		config.getConfig().set("users", null);
		
		for (String key : playerData.keySet()) {

			config.getConfig().set("users." + key, playerData.get(key));

		}
		
		config.saveConfig();
		
			
		
	}

	public void configValues() {
		for (ConfigEnums cfg : ConfigEnums.values()) {
			if (!config.getConfig().contains(cfg.defaults)) {
				config.getConfig().set(cfg.defaults, cfg.values);
			}
			config.saveConfig();
		}
	}

	public static void addPlayer(String player, String disguise) {
		playerData.put(player, disguise);

	}

}
