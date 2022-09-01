package net.teamdvlpr.dvpayments.plugin.managers;

import net.teamdvlpr.dvpayments.plugin.DvpaymentsPlugin;
import net.teamdvlpr.dvpayments.plugin.commands.LoginCommand;

public class CommandsManager {
  public CommandsManager(DvpaymentsPlugin main) {
    LoginCommand login = new LoginCommand();

    main.getCommand("login").setExecutor(login);
  }
}
