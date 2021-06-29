package me.sjaeledyr.transcendentmobs.Mobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import me.sjaeledyr.transcendentmobs.util.killCounter;

public class SkeletonCrusader extends JavaPlugin implements Listener {
    killCounter kc = new killCounter();
    @EventHandler
    public void onSkeletonDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Player p = e.getEntity().getKiller();;
        // Spawn Location
        Location spawnLoc = p.getLocation().add(2,0,0);
        World world = p.getWorld();
        if (entity instanceof Skeleton) {
           kc.addKill(p, 1);
            // Spawn Condition
            if (kc.getKills(p) >= 20) {
                p.sendMessage("Testing!");
                Skeleton skeleton = (Skeleton) world.spawnEntity(spawnLoc, EntityType.SKELETON);
                skeleton.setCustomName("Skeleton Crusader");
                skeleton.setCustomNameVisible(true);
                // Skeleton Crusader Gear
                skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_AXE));
                skeleton.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                skeleton.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                skeleton.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                skeleton.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                kc.resetKills(p);
            }
        }
    }
    @EventHandler
    public void onDamage (EntityDamageByEntityEvent e) {

    }
}
