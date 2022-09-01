package net.teamdvlpr.dvpayments.plugin.helpers;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptHelper {
  public static String generate(String text, Integer hashSalt) {
    return BCrypt.hashpw(text, BCrypt.gensalt(hashSalt));
  }

  public static boolean compare(String text, String textHash) {
    return BCrypt.checkpw(text, textHash);
  }
}
