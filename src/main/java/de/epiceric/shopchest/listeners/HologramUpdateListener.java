package de.epiceric.shopchest.listeners;

import de.epiceric.shopchest.ShopChest;
import de.epiceric.shopchest.fastRunnable.UpdateEvent;
import de.epiceric.shopchest.fastRunnable.UpdateType;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class HologramUpdateListener implements Listener {

    private ShopChest plugin;

    public HologramUpdateListener(ShopChest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        updateHolograms(e.getPlayer());
    }

    @EventHandler
    public void onHoloUpdate(UpdateEvent event){
        if(event.getType().equals(UpdateType.FAST) && plugin.getShopChestConfig().enable_quality_mode){
            for(Player p : Bukkit.getOnlinePlayers()){
                this.updateHolograms(p);
            }
            return;
        }
        else if(event.getType().equals(UpdateType.SLOW)){
            for(Player p : Bukkit.getOnlinePlayers()){
                this.updateHolograms(p);
            }
        }else if(event.getType().equals(UpdateType.MIN1)){
            this.checkShops();
        }
    }

    public void checkShops(){
        for(Shop shop : plugin.getShopUtils().getShops()){
            int chunkx = ((int)shop.getLocation().getX())>>4;
            int chunkz = ((int)shop.getLocation().getZ())>>4;
            if(shop.getLocation().getWorld().isChunkLoaded(chunkx,chunkz)){
            Block b = shop.getLocation().getBlock();
            if(b.getType() != Material.CHEST && b.getType() != Material.TRAPPED_CHEST){//Moved the check to see if the shop is null here.
                plugin.getShopUtils().removeShop(shop, plugin.getShopChestConfig().remove_shop_on_error);
            }
        }}
    }

    private void updateHolograms(Player p) {
        Location playerLocation = p.getLocation();
        double hologramDistanceSquared = Math.pow(plugin.getShopChestConfig().maximal_distance, 2);

        for (Shop shop : plugin.getShopUtils().getShops()) {

            if (shop.getHologram() == null) continue;

            Location shopLocation = shop.getLocation();
            
            if (playerLocation.getWorld().getName().equals(shopLocation.getWorld().getName())) {
                if (playerLocation.distanceSquared(shop.getHologram().getLocation()) <= hologramDistanceSquared) {
                    if (!shop.getHologram().isVisible(p)) {
                        shop.getHologram().showPlayer(p);
                    }
                } else {
                    if (shop.getHologram().isVisible(p)) {
                        shop.getHologram().hidePlayer(p);
                    }
                }
            }

        }
    }

}
