package net.teamdvlpr.dvpayments.plugin.commands.user;

import com.connorlinfoot.titleapi.TitleAPI;
import net.teamdvlpr.dvpayments.plugin.DvpaymentsPlugin;
import net.teamdvlpr.dvpayments.plugin.database.models.User;
import net.teamdvlpr.dvpayments.plugin.database.repositories.UserRepository;
import net.teamdvlpr.dvpayments.plugin.helpers.HasherHelper;
import net.teamdvlpr.dvpayments.plugin.utils.TextUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LoginCommand implements CommandExecutor {
  private static final HashMap<String, Integer> usersWhoTried = new HashMap<String, Integer>();

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("§cComando apenas para jogadores dentro do jogo!");

      return true;
    }

    Player player = (Player) sender;
    String userId = player.getUniqueId().toString();

    User user = UserRepository.findById(userId);

    if (user == null) {
      player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 0.5F);

      sender.sendMessage("§cVocê precisa se registrar primeiro!");

      return true;
    }

    boolean userAlreadyLogged = DvpaymentsPlugin.usersLogged.contains(player.getName());

    if (userAlreadyLogged) {
      player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1.0F, 0.5F);

      sender.sendMessage("§eVocê já está logado!");

      return true;
    }

    String password = args[0];

    boolean verifyPassword = HasherHelper.compare(password, user.password);

    int LIMIT_COUNT = 2;

    if (!verifyPassword) {
      if (usersWhoTried.containsKey(player.getName()) && (usersWhoTried.get(player.getName())).equals(LIMIT_COUNT)) {
        player.kickPlayer("§cVocê excedeu o número limite de " + (LIMIT_COUNT + 1) + " tentativas de login, reconecte e tente novamente.");

        usersWhoTried.remove(player.getName());

        return true;
      }

      if (usersWhoTried.containsKey(player.getName())) {
        usersWhoTried.put(player.getName(), usersWhoTried.get(player.getName()) + 1);
      } else {
        usersWhoTried.put(player.getName(), 1);
      }

      int attempts = usersWhoTried.get(player.getName()) == 1 ? LIMIT_COUNT : LIMIT_COUNT - 1;

      sender.sendMessage("§cSenha incorreta! Você tem mais " + attempts + " tentativas.");

      return true;
    }

    DvpaymentsPlugin.usersLogged.add(player.getName());

    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 0.5F);

    TextUtil.sendBlankMessages(player);

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
