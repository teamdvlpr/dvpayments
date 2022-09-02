package net.teamdvlpr.dvpayments.plugin.managers;

import net.teamdvlpr.dvpayments.plugin.DvpaymentsPlugin;
import net.teamdvlpr.dvpayments.plugin.commands.security.EmailCommand;
import net.teamdvlpr.dvpayments.plugin.commands.security.PrimeCommand;
import net.teamdvlpr.dvpayments.plugin.commands.security.VerifyCommand;
import net.teamdvlpr.dvpayments.plugin.commands.user.LoginCommand;
import net.teamdvlpr.dvpayments.plugin.commands.user.RegisterCommand;

public class CommandsManager {
  public CommandsManager(DvpaymentsPlugin main) {
    LoginCommand loginCommand = new LoginCommand();
    RegisterCommand registerCommand = new RegisterCommand();
    EmailCommand emailCommand = new EmailCommand();
    VerifyCommand verifyCommand = new VerifyCommand();
    PrimeCommand primeCommand = new PrimeCommand();

    main.getCommand("login").setExecutor(loginCommand);
    main.getCommand("register").setExecutor(registerCommand);
    // main.getCommand("email").setExecutor(emailCommand);
    // main.getCommand("verify").setExecutor(verifyCommand);
    // main.getCommand("prime").setExecutor(primeCommand);
  }
}
