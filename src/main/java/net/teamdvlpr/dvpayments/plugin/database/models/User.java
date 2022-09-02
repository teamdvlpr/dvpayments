package net.teamdvlpr.dvpayments.plugin.database.models;

public class User {
  public String id;
  public String nickname;
  public String password;
  public String email;
  public String phone;
  public Boolean verify;
  public Boolean prime;
  public String discord_id;
  public String created_at;

  public void setId(String id) {
    this.id = id;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setVerify(Boolean verify) {
    this.verify = verify;
  }

  public void setPrime(Boolean prime) {
    this.prime = prime;
  }

  public void setDiscordId(String discord_id) {
    this.discord_id = discord_id;
  }

  public void setCreatedAt(String created_at) {
    this.created_at = created_at;
  }


}
