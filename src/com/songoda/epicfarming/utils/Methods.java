package com.songoda.epicfarming.utils;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.songoda.arconix.plugin.Arconix;
import com.songoda.epicfarming.EpicFarmingPlugin;

public class Methods {

	public static ItemStack getGlass() {
		try {
			EpicFarmingPlugin plugin = EpicFarmingPlugin.pl();
			return Arconix.pl().getApi().getGUI().getGlass(plugin.getConfig().getBoolean("settings.Rainbow-Glass"), plugin.getConfig().getInt("Interfaces.Glass Type 1"));
		} catch (Exception ex) {
			Debugger.runReport(ex);
		}
		return null;
	}

	public static ItemStack getBackgroundGlass(boolean type) {
		try {
			EpicFarmingPlugin plugin = EpicFarmingPlugin.pl();
			if (type)
				return Arconix.pl().getApi().getGUI().getGlass(false, plugin.getConfig().getInt("Interfaces.Glass Type 2"));
			else
				return Arconix.pl().getApi().getGUI().getGlass(false, plugin.getConfig().getInt("Interfaces.Glass Type 3"));
		} catch (Exception ex) {
			Debugger.runReport(ex);
		}
		return null;
	}

	public static void doParticles(Player p, Location location) {
		try {
			EpicFarmingPlugin instance = EpicFarmingPlugin.getInstance();
			location.setX(location.getX() + .5);
			location.setY(location.getY() + .5);
			location.setZ(location.getZ() + .5);
			p.getWorld().spigot().playEffect(location, Effect.valueOf(instance.getConfig().getString("Main.Upgrade Particle Type")), 0, 0, 0.2F, 0.2F, 0.2F, 0.1F, 200, 50);
		} catch (Exception e) {
			Debugger.runReport(e);
		}
	}

	public static String formatName(int level, boolean full) {
		try {
			String name = EpicFarmingPlugin.getInstance().getLocale().getMessage("general.nametag.farm", level);

			String info = "";
			if (full) {
				info += Arconix.pl().getApi().format().convertToInvisibleString(level + ":");
			}

			return info + Arconix.pl().getApi().format().formatText(name);
		} catch (Exception ex) {
			Debugger.runReport(ex);
		}
		return null;
	}
}