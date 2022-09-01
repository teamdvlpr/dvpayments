package net.teamdvlpr.dvpayments.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LoginCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    sender.sendMessage(new String[]{
      "",
      "§aVocê logou com sucesso!",
      "",
      "§6§lDICA§f: Nunca passe sua senha para ninguém.",
      ""
    });

    return true;
  }
}
