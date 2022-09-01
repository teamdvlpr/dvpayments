package net.teamdvlpr.dvpayments.plugin.commands;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("§cComando apenas para jogadores dentro do jogo!");

      return true;
    }

    Player player = (Player) sender;

    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 0.5F);

    TitleAPI.sendTitle(player, "§a§lAutenticado!", "Bom jogo :D", 20, 100, 20);

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
