package net.teamdvlpr.dvpayments.plugin.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PurchasesCommand implements CommandExecutor {

    public static ItemStack noPurchases;
    public static ItemMeta noPurchasesMeta;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Apenas jogadores podem executar este comando.");
            return true;
        }

        Player player = (Player) sender;
        Inventory purchases = Bukkit.createInventory(player, 1, "Compras - " + player.getName());

        noPurchases = new ItemStack(Material.BARRIER);
        noPurchasesMeta = noPurchases.getItemMeta();
        noPurchasesMeta.setDisplayName("§cVazio.");
        noPurchases.setItemMeta(noPurchasesMeta);

        for (int i =0; i != 1; i++) {
            purchases.setItem(0, noPurchases);
        }

        player.openInventory(purchases);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1,0F);
        return false;
    }
}
