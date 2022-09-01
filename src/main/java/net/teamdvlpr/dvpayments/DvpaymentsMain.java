package net.teamdvlpr.dvpayments;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class DvpaymentsMain extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().log(Level.WARNING, "Plugin Started.");
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
