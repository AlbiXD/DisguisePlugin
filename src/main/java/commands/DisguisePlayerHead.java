package commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import item.CustomItem;
import net.md_5.bungee.api.ChatColor;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguisePlayerHead extends SubCommand {

	public DisguisePlayerHead() {
		// super(baseCommand, firstArgument, aliases, permission, usage, description);

		super("disguise", "give", new ArrayList<String>(), "disguise.head", "", "gives a player the specified skull");

	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {

		// Checks if it is a player or console
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer's can only use this command"));
			return true;
		}

		Player player = (Player) sender;

		player.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&aYou Have Recieved &a&l" + args[1] + "'s &aHead"));

		CustomItem.createPlayerHead(args[1]);


		player.getInventory().addItem(CustomItem.playerHead);

		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		return null;
	}

}
