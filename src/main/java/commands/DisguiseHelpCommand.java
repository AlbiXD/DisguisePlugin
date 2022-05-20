package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguiseHelpCommand extends SubCommand {


	public DisguiseHelpCommand() {
		super("disguise", "help", new ArrayList<String>(), "disguise.help", "/disguise help", "it shows commands");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{	
		sender.sendMessage(DisguisePlugin.playerData.toString());

		BaseComponent[] helpCMD = new ComponentBuilder(
				ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/disguise &ahelp"))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disguise help")).append(ChatColor
								.translateAlternateColorCodes('&', "&7 help Lists all Disguise Help commands!"))
						.create();

		BaseComponent[] give = new ComponentBuilder(
				ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/disguise &agive <player>"))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/disguise give <player>"))
						.append(ChatColor.translateAlternateColorCodes('&', " &7 gives a mask of the player!"))
						.create();

		BaseComponent[] unmask = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/unmask"))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
				.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/unmask"))
				.append(ChatColor.translateAlternateColorCodes('&', " &7unmasks yourself!")).create();

		BaseComponent[] unmaskPlayer = new ComponentBuilder(
				ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/disguise &ahelp"))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/unmask <player>"))
						.append(ChatColor.translateAlternateColorCodes('&', " &7unmasks someone else!")).create();

		sender.spigot().sendMessage(helpCMD);
		sender.spigot().sendMessage(give);
		sender.spigot().sendMessage(unmask);
		sender.spigot().sendMessage(unmaskPlayer);

		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
