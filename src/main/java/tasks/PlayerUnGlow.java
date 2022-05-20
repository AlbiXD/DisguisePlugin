package tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerUnGlow extends BukkitRunnable{

	private Player player;
	
	
	public PlayerUnGlow(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		
		player.setGlowing(false);		
		
		
		
	}

}
