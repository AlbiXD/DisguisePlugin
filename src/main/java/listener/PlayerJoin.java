package listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.lkeehl.tagapi.api.Tag;
import main.DisguisePlugin;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;

public class PlayerJoin implements Listener {

	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();

	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent e) {

		if (e.getPlayer().isGlowing()) {
			e.getPlayer().setGlowing(false);
		}

		if (DisguisePlugin.playerData.containsKey(e.getPlayer().getName())) {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&aYou are currently disguised as &a&l" + DisguisePlugin.playerData.get(e.getPlayer().getName())));

			try {
				Tag tag = Tag.create(e.getPlayer()); // Create a new Tag

				tag.addTagLine(10).setGetName(pl -> DisguisePlugin.playerData.get(e.getPlayer().getName()));
				tag.giveTag();

				api.setSkin(e.getPlayer().getName(), DisguisePlugin.playerData.get(e.getPlayer().getName()));
				api.applySkin(new PlayerWrapper(e.getPlayer()));

			} catch (SkinRequestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
