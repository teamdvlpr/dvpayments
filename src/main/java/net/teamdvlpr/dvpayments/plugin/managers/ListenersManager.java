package net.teamdvlpr.dvpayments.plugin.managers;

import net.teamdvlpr.dvpayments.plugin.DvpaymentsPlugin;
import net.teamdvlpr.dvpayments.plugin.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {
  public ListenersManager(DvpaymentsPlugin main) {
    PluginManager manager = main.getServer().getPluginManager();

    manager.registerEvents(new PlayerListener(), main);
  }
}
