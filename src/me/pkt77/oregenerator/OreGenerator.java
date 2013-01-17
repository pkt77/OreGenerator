package me.pkt77.oregenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class OreGenerator extends JavaPlugin {
	public final Logger log = Logger.getLogger("Minecraft");

	/*CHANGELOG :
	 * V1.0 :
	 * First Release - ores had a random chance to spawn (all the same chance)
	 * V2.0 :
	 * Added /og reload command with - og.reload permission
	 * Added World configuration
	 * Added Percentage calculation for each ore (configurable)
	 */

	@Override
	public void onEnable() {
		new Listeners(this);
		getCommand("og").setExecutor(new Commands(this));
		if (!getDataFolder().exists()) {
			getConfig().options().copyDefaults(true);
			getConfig().set("Worlds", addWorlds());
			saveConfig();
		}
	}

	public List<String> addWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}
}