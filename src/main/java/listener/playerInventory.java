package listener;

import java.util.ArrayList;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.lkeehl.tagapi.api.Tag;
import main.DisguisePlugin;
import net.md_5.bungee.api.ChatColor;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;

public class PlayerInventory implements Listener {

	private SkinsRestorerAPI api = SkinsRestorerAPI.getApi();
	private ItemStack skull = null;
	private Tag tag;

	List<PlayerInfoData> pd = new ArrayList<PlayerInfoData>();


	@SuppressWarnings("deprecation")
	@EventHandler
	public void playerInventoryEvent(InventoryClickEvent e) {

		ItemStack[] armorContents = e.getWhoClicked().getInventory().getArmorContents();

		if (armorContents[3] == null && e.getCursor().getType().equals(Material.PLAYER_HEAD)
				&& e.getSlotType().equals(SlotType.ARMOR)) {
			e.getWhoClicked().getInventory().setHelmet(new ItemStack(Material.AIR));
			skull = e.getCursor();

			e.setCursor(null);
			


			try {

				final SkullMeta itemMeta = (SkullMeta) skull.getItemMeta();
				api.setSkin(e.getWhoClicked().getName(), itemMeta.getOwner());
				api.applySkin(new PlayerWrapper(e.getWhoClicked()));
				e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&aYou are currently disguised as &a&l" + itemMeta.getOwner()));

				tag = Tag.create(e.getWhoClicked()); // Create a new Tag
				tag.addTagLine(10).setGetName(pl -> itemMeta.getOwner());
				tag.giveTag();
				
				DisguisePlugin.addPlayer(e.getWhoClicked().getName(), itemMeta.getOwner());
				
			} catch (SkinRequestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	

}
