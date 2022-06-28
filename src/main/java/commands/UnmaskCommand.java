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


}
