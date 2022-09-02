package net.teamdvlpr.dvpayments.plugin.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
public class BillListener implements Listener {

    @EventHandler
    public void PlayerInteractInventoryEvent(InventoryClickEvent e) {
        HumanEntity p = e.getWhoClicked();
        InventoryAction action = e.getAction();

        if(e.getView().getTitle().contains("Compras - " + p.getName())) {
            if (
                            action == InventoryAction.DROP_ALL_CURSOR ||
                            action == InventoryAction.DROP_ONE_SLOT ||
                            action == InventoryAction.DROP_ONE_CURSOR
            ) {
                e.setCancelled(true);
            }
        }
    }
}
