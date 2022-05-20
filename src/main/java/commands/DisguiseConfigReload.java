package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguiseConfigReload extends SubCommand {
	private DisguisePlugin plugin;

	public DisguiseConfigReload(DisguisePlugin plugin) {
		super("disguise", "reload", new ArrayList<String>(), "disguise.reload", "/disguise reload",
				"reloads the configuration");
		// TODO Auto-generated constructor stub
		this.plugin = plugin;

	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		plugin.config.reloadConfig();

		plugin.configValues();

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConfig has successfully reloaded!"));

		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
