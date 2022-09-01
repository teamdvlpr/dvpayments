package net.teamdvlpr.dvpayments.plugin.utils;

import org.bukkit.entity.Player;

public class TextUtil {
  public static void sendBlankMessages(Player player) {
    for (byte b1 = 0; b1 < 100; b1++)
      player.sendMessage("");
  }
}
