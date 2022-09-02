package net.teamdvlpr.dvpayments.plugin.helpers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class LoadVipZero {
    public static File file;
    public static FileConfiguration f;

    public static void loadConfig() {
        file = new  File("plugins/VipZero", "config.yml");
        try {
            f = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§cVipZero não encontrado.");
        }
    }

    public static void loadVips(Player p) {

    }
}
