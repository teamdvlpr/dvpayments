package net.teamdvlpr.dvpayments.plugin.helpers;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class HasherHelper {
  public static String generate(String password) {
    String encryptedPassword = null;

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.update(password.getBytes());
      byte[] bytes = messageDigest.digest();
      StringBuilder hash = new StringBuilder();

      for (int i = 0; i < bytes.length; i++) {
        hash.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      encryptedPassword = hash.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return encryptedPassword;
  }

  public static boolean compare(String password, String hash) {
    return Objects.equals(HasherHelper.generate(password), hash);
  }
}
