package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import data.ConfigEnums;
import item.CustomItem;
import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguiseGive extends SubCommand {

	private boolean buyMode = false;
	private DisguisePlugin plugin = DisguisePlugin.getPlugin();
	
	
	private FileConfiguration config = plugin.config.getConfig();

	public DisguiseGive() {
		// super(baseCommand, firstArgument, aliases, permission, usage, description);

		super("scydisguise", "give", new ArrayList<String>(), "scydisguise.give", "",
				"gives a player the specified skull");

	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("scydisguise.purchase")) {

			// Checks if it is a player or console
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer's can only use this command"));
				return true;
			}
			

			if (args.length == 1) {
				buyMode = true;

				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&7To purchase one's skull they must pay a price of &e1 gold block"));

				BaseComponent[] button = new ComponentBuilder("                    ")
						.append(ChatColor.translateAlternateColorCodes('&', " &a&l[CONFIRM]"))
						.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/scydisguise give confirm"))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO CONFIRM"))))
						.append(ChatColor.translateAlternateColorCodes('&', " &4&l[DECLINE]"))
						.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/scydisguise give decline"))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								new Text(ChatColor.translateAlternateColorCodes('&', "&4&lCLICK TO DECLINE"))))
						.create();
				sender.spigot().sendMessage(button);

				return true;
			}

			// scydisguise give confirm - Confirms the purchase however terminates if you do
			// not have enough gold
			if (args[1].equals("confirm") && buyMode) {

				ItemStack[] items = ((Player) sender).getInventory().getContents();
				for (int i = 0; i < items.length; i++) {
					if (items[i] != null && items[i].getType().equals(Material.valueOf(config.getString(ConfigEnums.item.defaults))) && items[i].getAmount() >= config.getInt(ConfigEnums.price.defaults)) {

						sender.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&aYou successfuly purchased your head!"));
						CustomItem.createPlayerHead(sender.getName());
						((Player) sender).getInventory().addItem(CustomItem.playerHead);
						((Player) sender).getInventory().setItem(i,
								new ItemStack(Material.GOLD_BLOCK, items[i].getAmount() - 2));

						buyMode = false;

						return true;
					}

				}

				buyMode = false;
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&cYou do not have enough to complete transcation!"));
				return true;
			}

			// scydisguise give decline - declines the attempt at purchasing sckull
			if (args[1].equals("decline") && buyMode) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe transaction was denied!"));
				buyMode = false;
				return true;
			}

			// Stuck in the buymode until either confirmed or declined

			if (buyMode) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&7To purchase one's skull they must pay a price of &e1 gold block"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"                      &a&l[CONFIRM] &4&l[DECLINE]"));
				return true;
			}

		}

		// Admin permissino to give skulls
		if (!sender.hasPermission("scydisguise.admin.give")) {
			return true;

		}

		Player player = (Player) sender;

		player.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&aYou Have Received &a&l" + args[1] + "'s &aHead"));

		CustomItem.createPlayerHead(args[1]);

		System.out.println(CustomItem.playerHead.getItemMeta().getPersistentDataContainer().getKeys());

		player.getInventory().addItem(CustomItem.playerHead);

		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		return null;
	}

}
