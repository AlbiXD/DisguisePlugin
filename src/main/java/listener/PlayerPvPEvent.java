package listener;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.lkeehl.tagapi.TagAPI;
import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import tasks.PlayerUnGlow;

public class PlayerPvPEvent implements Listener {

	private DisguisePlugin plugin;
	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();

	public PlayerPvPEvent(DisguisePlugin plugin) {
		this.plugin = plugin;

	}

	@EventHandler
	public void playerPVP(EntityDamageByEntityEvent e) {
		// If attacked is masked then glow for x amount of seconds

		if (!e.getEntity().isGlowing()) {
			if (e.getDamager() instanceof Player) {
				if (e.getEntity() instanceof Player) {
					if (DisguisePlugin.playerData.containsKey(e.getEntity().getName())
							&& ((Player) e.getEntity()).getHealth() >= 10) {
						e.getEntity().setGlowing(true);
						PlayerUnGlow task = new PlayerUnGlow((Player) e.getEntity());
						task.runTaskLater(plugin, 100);

						return;
					}

				}
			}
		}
		if (DisguisePlugin.playerData.containsKey(e.getEntity().getName()) && ((Player) e.getEntity()).getHealth() < 10) {
			api.removeSkin(e.getEntity().getName());

			DisguisePlugin.playerData.remove(e.getEntity().getName());
			
			try {
				api.applySkin(new PlayerWrapper((Player) e.getEntity()));
				TagAPI.removeTag(e.getEntity());
				e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have lost your disguise"));

			} catch (SkinRequestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
