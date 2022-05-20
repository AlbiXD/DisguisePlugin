package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.lkeehl.tagapi.TagAPI;
import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;

public class UnmaskCommand implements CommandExecutor {

	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();



	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot use this command");
			return true;

		}

		/*
		 * Self Disguise command this is meant to be a command for players to undisguise
		 */
		if (args.length == 0) {
			Player player = (Player) sender;

			// Check if they have a mask

			if (!DisguisePlugin.playerData.containsKey(sender.getName())) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not disguised"));
				return true;
			}

			// Remove the mask
			try {
				api.removeSkin(player.getName());
				api.applySkin(new PlayerWrapper(player));
				TagAPI.removeTag(player);
				DisguisePlugin.playerData.remove(sender.getName());


			} catch (SkinRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have unmasked yourself!"));

			return true;
		}

		/*
		 * This is meant to be used by admins to undsiguise certain players for
		 * debugging reasons
		 */
		if (args.length == 1 && sender.hasPermission("unmask.others")) {
			if (!DisguisePlugin.playerData.containsKey(sender.getName())) {
				sender.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + " is currently not masked!"));

				return true;
			}

			try {
				api.removeSkin(args[0]);
				api.applySkin(new PlayerWrapper(Bukkit.getPlayer(args[0])));
				DisguisePlugin.playerData.remove(sender.getName());
			} catch (SkinRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + args[0] + " successfully unmasked!"));
			Bukkit.getPlayer(args[0])
					.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have been unmasked!"));

		}

		return true;

	}

}
