package commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lkeehl.tagapi.TagAPI;

import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import xyz.scyllasrock.ScyUtility.objects.SubCommand;

public class DisguiseClear extends SubCommand {

	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();

	public DisguiseClear() {

		super("scydisguise", "clear", new ArrayList<String>(), "scydisguise.admin.clear", "clears players disguise", "");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if(args.length < 2) {
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInsufficient Argumetns"));
			return true;
		}
		if (!DisguisePlugin.playerData.containsKey(args[1])) {

			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l" +
					args[1] + " &cis currently not disguised or player does not exist!"));
			return true;
		}

		try {
			api.removeSkin(args[1]);
			api.applySkin(new PlayerWrapper(Bukkit.getPlayer(args[1])));
            DisguisePlugin.playerData.remove(args[1]);
			TagAPI.removeTag(Bukkit.getPlayer(args[1]));
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully unmasked &a&l" +
					args[1]));

		} catch (SkinRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public List<String> getTabCompletions(Player player, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
