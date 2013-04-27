package me.pkt77.oregenerator;

import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class Listeners implements Listener {
	private OreGenerator _og;

	public Listeners(OreGenerator og) {
		_og = og;
		_og.getServer().getPluginManager().registerEvents(this, og);
	}

	@EventHandler
	public void onFromTo(BlockFromToEvent event) {
		int id = event.getBlock().getTypeId();
		if (id >= 8 && id <= 11) {
			Block b = event.getToBlock();
			int toid = b.getTypeId();
			if (toid == 0) {
				if (generatesCobble(id, b)) {
					List<String> worlds = _og.getConfig().getStringList("Worlds");
					if (worlds.contains(event.getBlock().getLocation().getWorld().getName())) {
						Random pick = new Random();
						int chance = 0;
						for (int counter = 1; counter <= 1; counter++) {
							chance = 1 + pick.nextInt(100);
						}

						double coal = _og.getConfig().getDouble("Chances.Coal");
						double iron = _og.getConfig().getDouble("Chances.Iron");
						double gold = _og.getConfig().getDouble("Chances.Gold");
						double redstone = _og.getConfig().getDouble("Chances.Redstone");
						double lapis = _og.getConfig().getDouble("Chances.Lapis");
						double emerald = _og.getConfig().getDouble("Chances.Emerald");
						double diamond = _og.getConfig().getDouble("Chances.Diamond");

						if (chance > 0 && chance <= coal) {
							b.setType(Material.COAL_ORE);
						}
						if (chance > coal && chance <= iron) {
							b.setType(Material.IRON_ORE);
						}
						if (chance > iron && chance <= gold) {
							b.setType(Material.GOLD_ORE);
						}
						if (chance > gold && chance <= redstone) {
							b.setType(Material.REDSTONE_ORE);
						}
						if (chance > redstone && chance <= lapis) {
							b.setType(Material.LAPIS_ORE);
						}
						if (chance > lapis && chance <= emerald) {
							b.setType(Material.EMERALD_ORE);
						}
						if (chance > emerald && chance <= diamond) {
							b.setType(Material.DIAMOND_ORE);
						}
					}
				}
			}
		}
	}

	private final BlockFace[] faces = new BlockFace[] { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };

	public boolean generatesCobble(int id, Block b) {
		int mirrorID1 = (id == 8 || id == 9 ? 10 : 8);
		int mirrorID2 = (id == 8 || id == 9 ? 11 : 9);
		for (BlockFace face : faces) {
			Block r = b.getRelative(face, 1);
			if (r.getTypeId() == mirrorID1 || r.getTypeId() == mirrorID2) {
				return true;
			}
		}
		return false;
	}
}
