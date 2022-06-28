package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import xyz.scyllasrock.ScyUtility.objects.BaseCommand;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguiseCommand extends BaseCommand implements CommandExecutor, TabCompleter {

	public DisguiseCommand(DisguisePlugin plugin) {

		super.addSubCommand(new DisguiseGive());
		super.addSubCommand(new DisguiseConfigReload(plugin));
		super.addSubCommand(new DisguiseHelpCommand());
		super.addSubCommand(new DisguiseClear());
		super.addSubCommand(new DisguiseSet());

	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		/*
		 * Checks if the command arguments is less than 0
		 */

		if (!sender.hasPermission("scydisguise")) {
			sender.sendMessage(
					ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission to run this command!"));
			return true;
		}
		

		if (args.length > 0) {
			for (SubCommand sub : super.getSubCommands()) {
				if (!sender.hasPermission(sub.getPermission())) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&cYou do not have permission to run this command!"));
					return true;
				}

				if (sub.getFirstArgument().equalsIgnoreCase(args[0]))
					return sub.execute(sender, args);
			}

			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCommand does not exist!"));
		}

		/*
		 * Checks if the command arguments is less than 0
		 */
		if (args.length == 0) {
			
			super.getSubCommands().get(2).execute(sender, args);
			return true;
		}

		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			List<String> commands = new ArrayList<String>();
			commands.add("give");
			commands.add("reload");
			commands.add("help");
			commands.add("set");
			commands.add("clear");
			return commands;

		}

		if (args.length > 2) {
			return new ArrayList<String>();
		}
		return null;
	}

}
