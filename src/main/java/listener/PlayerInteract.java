package listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import com.lkeehl.tagapi.api.Tag;
import main.DisguisePlugin;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;

public class PlayerInteract implements Listener {
	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();
	private ItemStack skull = null;
	private Tag tag;

	@EventHandler
	public void playerInteractEvent(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}

		if (e.getItem().getType() == Material.PLAYER_HEAD && e.getHand().equals(EquipmentSlot.HAND)) {

			if (DisguisePlugin.playerData.containsKey(e.getPlayer().getName())) {
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are already disguised!"));
				return;
			}

			try {
				skull = e.getItem();

				final SkullMeta itemMeta = (SkullMeta) skull.getItemMeta();
				api.setSkin(e.getPlayer().getName(), itemMeta.getOwner());
				api.applySkin(new PlayerWrapper(e.getPlayer()));
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&aYou are currently disguised as &a&l" + itemMeta.getOwner()));

				tag = Tag.create(e.getPlayer()); // Create a new Tag
				tag.addTagLine(10).setGetName(pl -> itemMeta.getOwner());
				tag.giveTag();

				DisguisePlugin.addPlayer(e.getPlayer().getName(), itemMeta.getOwner());

			} catch (SkinRequestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			ItemStack[] items = e.getPlayer().getInventory().getContents();

			e.getPlayer().getInventory().setItemInHand(null);

			e.setCancelled(true);
		}
	}
}
