package net.teamdvlpr.dvpayments.plugin.commands.user;

import com.connorlinfoot.titleapi.TitleAPI;
import net.teamdvlpr.dvpayments.plugin.DvpaymentsPlugin;
import net.teamdvlpr.dvpayments.plugin.database.models.User;
import net.teamdvlpr.dvpayments.plugin.database.repositories.IpRepository;
import net.teamdvlpr.dvpayments.plugin.database.repositories.UserRepository;
import net.teamdvlpr.dvpayments.plugin.helpers.HasherHelper;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class RegisterCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("§cComando apenas para jogadores dentro do jogo!");

      return true;
    }

    Player player = (Player) sender;
    String userId = player.getUniqueId().toString();

    User user = UserRepository.findById(userId);

    if (user != null) {
      player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 0.5F);

      sender.sendMessage("§cVocê já está registrado!");

      return true;
    }

    String ip = player.getAddress().getHostString();

    boolean ipAlreadyRegistered = IpRepository.findOne(ip);

    if (ipAlreadyRegistered) {
      player.kickPlayer("§cLimite de contas criadas neste ip excedido!");

      return true;
    }

    if (!(args.length == 2)) {
      sender.sendMessage("§eFalta argumentos! /registrar <senha> <senha>");

      player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 0.5F);

      return true;
    }

    String password = args[0];
    String confirmPassword = args[1];

    if (!Objects.equals(confirmPassword, password)) {
      sender.sendMessage("§cAs senhas não coincidem.");

      player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 0.5F);

      return true;
    }

    int MIN_PASSWORD_SIZE = 4;

    if (password.length() < MIN_PASSWORD_SIZE || confirmPassword.length() < MIN_PASSWORD_SIZE) {
      sender.sendMessage("§fSua senha é §c§lFRACA§f tente outra!");

      return true;
    }

    String nickname = player.getName();
    String encryptedPassword = HasherHelper.generate(password);

    IpRepository.add(ip);
    UserRepository.create(userId, nickname, encryptedPassword);

    DvpaymentsPlugin.usersLogged.add(player.getName());

    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 0.5F);

    TitleAPI.sendTitle(player, "§a§lRegistrado!", "Bom jogo :D", 20, 100, 20);

    sender.sendMessage(new String[]{
      "",
      "§aRegistrado com sucesso!",
      "",
      "§6§lDICA§f: Nunca passe sua senha para ninguém.",
      ""
    });

    if (password.length() >= 5) {
      sender.sendMessage("§fSua senha é §e§lMÉDIA§f! Muito bom.");

      return true;
    }

    if (password.length() >= 8 && password.matches("[0-9]")) {
      sender.sendMessage("§fSua senha é §a§lFORTE§f! Bom jogo :D");

      return  true;
    }

    return true;
  }
}
