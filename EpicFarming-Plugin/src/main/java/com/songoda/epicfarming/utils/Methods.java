package com.songoda.epicfarming.utils;

import com.songoda.arconix.plugin.Arconix;
import com.songoda.epicfarming.EpicFarmingPlugin;
import com.songoda.epicfarming.farming.ELevel;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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