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
	public boolean execute(CommandSender sender, String[] args) {

		BaseComponent[] helpCMD = new ComponentBuilder(
				ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/scydisguise &ahelp")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/scydisguise help"))
						.append(ChatColor.GRAY + " displays list of commands").event((ClickEvent) null).event((HoverEvent) null).create();
		
		BaseComponent[] giveCMD = new ComponentBuilder(
				ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/scydisguise &agive")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/scydisguise give"))
						.append(ChatColor.GRAY + " ability to purchase your own skull").event((ClickEvent) null).event((HoverEvent) null).create();

		BaseComponent[] unmaskCMD = new ComponentBuilder(
				ChatColor.translateAlternateColorCodes('&', "  &a-&a&l/scyunmask")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new Text(ChatColor.translateAlternateColorCodes('&', "&a&lCLICK TO COPY"))))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/scyunmask"))
						.append(ChatColor.GRAY + " removes the current disguise").event((ClickEvent) null).event((HoverEvent) null).create();
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7A list of the commands..."));
		sender.spigot().sendMessage(helpCMD);
		sender.spigot().sendMessage(giveCMD);
		sender.spigot().sendMessage(unmaskCMD);


		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
