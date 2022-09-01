package net.teamdvlpr.dvpayments.plugin;

import net.teamdvlpr.dvpayments.plugin.managers.CommandsManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DvpaymentsPlugin extends JavaPlugin {
  public static DvpaymentsPlugin instance;

  public static DvpaymentsPlugin getInstance() {
    return instance;
  }

  public DvpaymentsPlugin() {
    DvpaymentsPlugin.instance = this;
  }

  @Override
  public void onEnable() {
    if (!(new File(getDataFolder(), "config.yml")).exists()) saveDefaultConfig();


    new CommandsManager(this);
  }

  @Override
  public void onDisable() {

  }
}
