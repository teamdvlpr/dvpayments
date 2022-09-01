package net.teamdvlpr.dvpayments.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class DvpaymentsPlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    if (!(new File(getDataFolder(), "config.yml")).exists()) saveDefaultConfig();

    getLogger().log(Level.WARNING, "Plugin Enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().log(Level.WARNING, "Plugin Disabled.");
  }
}
