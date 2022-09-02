package net.teamdvlpr.dvpayments.plugin.database.repositories;

import net.teamdvlpr.dvpayments.plugin.database.models.User;
import net.teamdvlpr.dvpayments.plugin.managers.DatabaseManager;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
  private static Connection connection() {
    return DatabaseManager.getConnection();
  }
  public static void create(String id, String nickname, String password) {
    try {
      PreparedStatement preparedStatement = connection().prepareStatement("INSERT INTO users (id,nickname,password) VALUES (?,?,?);");

      preparedStatement.setString(1, id);
      preparedStatement.setString(2, nickname);
      preparedStatement.setString(3, password);

      preparedStatement.executeUpdate();
    } catch (Exception e) {
      Bukkit.getConsoleSender().sendMessage("§4[DvPayments/MySQL] There was an error creating a user.");

      e.printStackTrace();
    }
  }

  public static User findById(String id) {
    User user = new User();

    try {
      PreparedStatement preparedStatement = connection().prepareStatement("SELECT * FROM users WHERE id='" + id + "';");
      ResultSet resultSet;

      if ((resultSet = preparedStatement.executeQuery()).next()) {
        user.setId(resultSet.getString("id"));
        user.setNickname(resultSet.getString("nickname"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setVerify(resultSet.getBoolean("verify"));
        user.setPrime(resultSet.getBoolean("prime"));
        user.setDiscordId(resultSet.getString("discord_id"));
        user.setCreatedAt(resultSet.getString("created_at"));

        return user;
      }
    } catch (SQLException e) {
      Bukkit.getConsoleSender().sendMessage("§4[DvPayments/MySQL] There was an error getting the user password.");

      e.printStackTrace();
    }

    return null;
  }

  public static void update() {}
}
