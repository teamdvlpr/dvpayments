package net.teamdvlpr.dvpayments.plugin.managers;

import net.teamdvlpr.dvpayments.plugin.DvpaymentsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
  private static Connection connection;

  private void createTables() {
    try {
      connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users (" +
        "id VARCHAR(255) NOT NULL PRIMARY KEY," +
        "nickname VARCHAR(255) NOT NULL," +
        "password VARCHAR(255) NOT NULL," +
        "email VARCHAR(255)," +
        "phone VARCHAR(255)," +
        "verify TINYINT(1) DEFAULT 0," +
        "prime TINYINT(1) DEFAULT 0," +
        "discord_id VARCHAR(255)," +
        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
      ");");

      connection.createStatement().execute("CREATE TABLE IF NOT EXISTS ips (" +
        "ip VARCHAR(255) NOT NULL" +
      ");");

      connection.createStatement().execute("CREATE TABLE IF NOT EXISTS codes (" +
        "id VARCHAR(255) NOT NULL PRIMARY KEY," +
        "code VARCHAR(255) NOT NULL," +
        "user_id VARCHAR(255) NOT NULL," +
        "used TINYINT(1) DEFAULT 0," +
        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
        "FOREIGN KEY(user_id) REFERENCES users(id)" +
      ");");
    } catch (SQLException e) {
      Bukkit.getConsoleSender().sendMessage("§4[DvPayments/MySQL] Error creating tables.");

      e.printStackTrace();
    }
  }

  public void createConnection(DvpaymentsPlugin main) {
    FileConfiguration config = main.getConfig();

    String host = config.getString("mysql.host");
    String port = Integer.toString(config.getInt("mysql.port"));
    String database = config.getString("mysql.database");
    String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=latin1&useConfigs=maxPerformance";
    String user =  config.getString("mysql.user");
    String password = config.getString("mysql.password");

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      connection = DriverManager.getConnection(url, user, password);

      Bukkit.getConsoleSender().sendMessage("§a[DvPayments/MySQL] Success connecting to MySQL.");

      createTables();
    } catch (SQLException e) {
      Bukkit.getConsoleSender().sendMessage("§c[DvPayments/MySQL] Failed to Connect to MySQL!");

      Bukkit.getConsoleSender().sendMessage("§4[DvPayments/MySQL] You need connect to MySQL.");
      Bukkit.getConsoleSender().sendMessage("§4[DvPayments/MySQL] You need connect to MySQL.");
      Bukkit.getConsoleSender().sendMessage("§4[DvPayments/MySQL] You need connect to MySQL.");

      e.printStackTrace();
    }
  }

  public void stopConnection() {
    try {
      if (!connection.isClosed()) {
        connection.close();

        Bukkit.getConsoleSender().sendMessage("§6[DvPayments/MySQL] Success while Disconnecting to MySQL!");
      }
    } catch(Exception exception) {
      Bukkit.getConsoleSender().sendMessage("§c[DvPayments/MySQL] Failed to Disconnect to MySQL!");
    }
  }

  public static Connection getConnection(){
    return connection;
  }
}
