package data;

public enum ConfigEnums {
	

	
	glowing_cooldown("glowing_cooldown", 5), player_skull_chance("player_skull_chance", 25), item("item", "GOLD_BLOCK"), price("amount", 1);
	
	public final String defaults;
	public final Object values;	
	

	ConfigEnums(String defaults, Object value) {
		this.values = value;
		this.defaults = defaults;
	
	
	}

}
