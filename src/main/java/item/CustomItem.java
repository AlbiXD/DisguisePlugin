package item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomItem {
	public static ItemStack playerHead;


	@SuppressWarnings("deprecation")
	public static void createPlayerHead(String player) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwner(player);
		itemMeta.setDisplayName(player + "'s Skull!");
		item.setItemMeta(itemMeta);
		playerHead = item;

	}
}
