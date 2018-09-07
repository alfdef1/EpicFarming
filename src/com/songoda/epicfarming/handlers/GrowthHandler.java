package com.songoda.epicfarming.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.material.Crops;

import com.songoda.epicfarming.EpicFarmingPlugin;
import com.songoda.epicfarming.farming.Crop;

public class GrowthHandler {

	public GrowthHandler(EpicFarmingPlugin instance) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(EpicFarmingPlugin.getInstance(), this::growthRunner, 0, instance.getConfig().getInt("Main.Growth Tick Speed"));
		Bukkit.getScheduler().scheduleSyncRepeatingTask(EpicFarmingPlugin.getInstance(), this::clear, 0, instance.getConfig().getInt("Main.Clear Tick Speed"));
	}

	Map<Location, Crop> liveCrops = new HashMap<>();

	private static final Random random = new Random();

	private void growthRunner() {

		for (Crop crop : liveCrops.values()) {

			if (!(crop.getLocation().getBlock().getState().getData() instanceof Crops))
				continue;

			//ToDO: This should be in config.
			int cap = (int) Math.ceil(60 / crop.getFarm().getLevel().getSpeedMultiplier()) - crop.getTicksLived();
			if (cap > 2) {
				int rand = random.nextInt(cap) + 1;

				crop.setTicksLived(crop.getTicksLived() + 1);
				if (rand != cap - 1 && crop.getTicksLived() != cap / 2)
					continue;

			}

			BlockState cropState = crop.getLocation().getBlock().getState();
			Crops cropData = (Crops) cropState.getData();

			Material material = crop.getLocation().getBlock().getType();

			switch (cropData.getState()) {
				case SEEDED:
					cropData.setState(CropState.GERMINATED);
					break;
				case GERMINATED:
					cropData.setState(CropState.VERY_SMALL);
					break;
				case VERY_SMALL:
					cropData.setState(CropState.SMALL);
					break;
				case SMALL:
					cropData.setState(CropState.MEDIUM);
					break;
				case MEDIUM:
					cropData.setState(CropState.TALL);
					break;
				case TALL:
					cropData.setState(CropState.VERY_TALL);
					break;
				case VERY_TALL:
					cropData.setState(CropState.RIPE);
					break;
				case RIPE:
					break;
			}
			cropState.setData(cropData);
			cropState.update();
			crop.setTicksLived(1);

		}
	}

	private void clear() {
		liveCrops.clear();
	}
}
