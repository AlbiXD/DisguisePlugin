package listener;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.lkeehl.tagapi.TagAPI;

import data.ConfigEnums;
import data.ConfigManager;
import item.CustomItem;
import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;

public class PlayerKill implements Listener {
	private Random random = new Random();
	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();
	private ConfigManager config;
	private FileConfiguration fileConfig;

	public PlayerKill(DisguisePlugin plugin) {
		this.config = plugin.config;
		this.fileConfig = config.getConfig();
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.getEntity().setGlowing(false);

		// Unmask on Death
		if (DisguisePlugin.playerData.containsKey(e.getEntity().getName())) {
			try {
				TagAPI.removeTag(e.getEntity());
				api.removeSkin(e.getEntity().getName());
				api.applySkin(new PlayerWrapper(e.getEntity()));
				DisguisePlugin.playerData.remove(e.getEntity().getName());

			} catch (SkinRequestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have lost your mask!"));

		}

		// Will not drop if the player is disguised
		if (DisguisePlugin.playerData.containsKey(e.getEntity().getName()))
			return;
		
		//Will not drop for admins
		if(e.getEntity().hasPermission("disguise.admin")) {
			return;
		}

		// Random Skull Drop
		if (random
				.nextInt((int) Math.round(100 / fileConfig.getDouble(ConfigEnums.player_skull_chance.defaults))) == 1) {
			CustomItem.createPlayerHead(e.getEntity().getName());

			ItemStack item = CustomItem.playerHead;
			e.getDrops().add(item);

		}

	}

}
