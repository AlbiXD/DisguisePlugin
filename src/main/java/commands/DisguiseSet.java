package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lkeehl.tagapi.api.Tag;

import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguiseSet extends SubCommand {
	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();
	private Tag tag;

	public DisguiseSet() {
		super("scydisguise", "set", new ArrayList<String>(), "scydisguise.admin.set", "", "");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String[] args) {

		if(args.length < 3) {
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInsufficient Argumetns"));
			return true;
		}
		
		
		if (!Bukkit.getOnlinePlayers().contains(Bukkit.getOfflinePlayer(args[1]))) {
			sender.sendMessage("Player does not exist");
			return true;
		}

		try {
			api.setSkin(args[1], args[2]);
			api.applySkin(new PlayerWrapper(Bukkit.getPlayer(args[1])));
			tag = Tag.create(Bukkit.getPlayer(args[1])); // Create a new Tag
			tag.addTagLine(10).setGetName(pl -> args[2]);
			tag.giveTag();
			DisguisePlugin.addPlayer(args[1], args[2]);
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l" + args[1] + "&a has been successfully disguised!"));
			
			Bukkit.getPlayer(args[1]).sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are currently disguised as &a&l" + args[2]));

			
			
			
		} catch (SkinRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		return null;
	}

}
