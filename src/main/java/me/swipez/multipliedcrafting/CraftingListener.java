package me.swipez.multipliedcrafting;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;


public class CraftingListener implements Listener {
    Multipliedcrafting plugin;

    public CraftingListener(Multipliedcrafting plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCraft(CraftItemEvent e){
        if (plugin.gamestarted){
            Player p = (Player) e.getWhoClicked();
            int newammount = 1;
            int multiplier = plugin.getConfig().getInt("enchantmentmultiplier");
            if (!plugin.craftamount.containsKey(p.getUniqueId())){
                plugin.craftamount.put(p.getUniqueId(), 1);
            }
            else {
                newammount = plugin.craftamount.get(p.getUniqueId())+multiplier;
                plugin.craftamount.put(p.getUniqueId(), newammount);
            }
            ItemStack crafteditem = e.getCurrentItem();
            int ammountofitems = crafteditem.getAmount();
            int finalcrafted = ammountofitems*newammount;
            if (finalcrafted > 64){
                int restoftheitems = finalcrafted - 64;
                finalcrafted = 64;
                ItemStack restoftheitemstack = crafteditem;
                restoftheitemstack.setAmount(restoftheitems);
                p.getInventory().addItem(restoftheitemstack);
                int testvalue = restoftheitemstack.getAmount();
                int stackcount = 0;
                if (p.getInventory().firstEmpty() == -1){
                    for (int i = 0;i < 600;i++) {
                        if (testvalue > 64){
                            testvalue = testvalue - 64;
                            stackcount++;
                        }
                    }
                    for (int i=0; i < stackcount; i++){
                        restoftheitemstack.setAmount(64);
                        p.getWorld().dropItemNaturally(p.getLocation(), restoftheitemstack);
                        for (int t=0; t<9;t++){
                            if (e.getInventory().getItem(t) == null){
                                ItemStack cstack = e.getInventory().getItem(t);
                                cstack.setAmount(cstack.getAmount()-1);
                            }
                        }
                    }
                }
            }
            crafteditem.setAmount(finalcrafted);
        }
    }
}
