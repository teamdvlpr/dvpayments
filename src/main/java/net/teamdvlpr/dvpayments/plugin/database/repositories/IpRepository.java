package net.teamdvlpr.dvpayments.plugin.database.repositories;

import net.teamdvlpr.dvpayments.plugin.managers.DatabaseManager;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IpRepository {
  private static Connection connection() {
    return DatabaseManager.getConnection();
  }

  public static void add(String ip) {
    try {
      PreparedStatement preparedStatement = connection().prepareStatement("INSERT INTO ips (ip) VALUES (?);");

      preparedStatement.setString(1, ip);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      Bukkit.getConsoleSender().sendMessage("§4[ZKSecurity/MySQL] There was an error adding the user's ip.");

      e.printStackTrace();
    }
  }

  public static boolean findOne(String ip) {
    try {
      PreparedStatement preparedStatement = connection().prepareStatement("SELECT ip FROM ips WHERE ip='" + ip + "';");

      return preparedStatement.executeQuery().next();
    } catch (SQLException e) {
      Bukkit.getConsoleSender().sendMessage("§4[ZKSecurity/MySQL] There was an error getting the ip.");

      e.printStackTrace();
    }

    return false;
  }
}
