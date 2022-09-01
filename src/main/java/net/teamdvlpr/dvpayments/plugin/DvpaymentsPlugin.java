package net.teamdvlpr.dvpayments.plugin;

import net.teamdvlpr.dvpayments.plugin.managers.CommandsManager;
import net.teamdvlpr.dvpayments.plugin.managers.ListenersManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class DvpaymentsPlugin extends JavaPlugin {
  public static DvpaymentsPlugin instance;

  public static DvpaymentsPlugin getInstance() {
    return instance;
  }

  public static ArrayList usersLogged = new ArrayList();

  public DvpaymentsPlugin() {
    DvpaymentsPlugin.instance = this;
  }

  @Override
  public void onEnable() {
    if (!(new File(getDataFolder(), "config.yml")).exists()) saveDefaultConfig();

    new ListenersManager(this);
    new CommandsManager(this);
  }

  @Override
  public void onDisable() {

  }
}
