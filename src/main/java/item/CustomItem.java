package item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import main.DisguisePlugin;

public class CustomItem {

	public static PersistentDataContainer data;

	public static ItemStack playerHead;

	@SuppressWarnings("deprecation")
	public static void createPlayerHead(String player) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwner(player);
		itemMeta.setDisplayName(player + "'s Skull!");
		data = itemMeta.getPersistentDataContainer();
		data.set(new NamespacedKey(DisguisePlugin.getPlugin(), "mask"), PersistentDataType.STRING, "test");
		item.setItemMeta(itemMeta);
		playerHead = item;

	}
}
