package jp.reitou_mugicha.corehub.compressor;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompItemFlag;
import org.mineacademy.fo.remain.CompMaterial;

public class CompressorListener implements Listener
{
    private static final String COMPRESSOR_TAG = "Compressor";

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        ItemStack handItem = event.getItemInHand();
        NBTItem nbtItem = new NBTItem(handItem);

        if (!nbtItem.hasKey("CoreHubMachine") || !nbtItem.getString("CoreHubMachine").equals(COMPRESSOR_TAG)) return;

        Block block = event.getBlockPlaced();
        if (block.getType() == Material.IRON_BLOCK)
        {
            NBTBlock nbtBlock = new NBTBlock(block);
            nbtBlock.getData().setBoolean(COMPRESSOR_TAG, true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (event.getClickedBlock() == null || event.getHand() != EquipmentSlot.HAND) return;
        Block block = event.getClickedBlock();
        NBTBlock nbtBlock = new NBTBlock(block);
        if (nbtBlock.getData().getBoolean(COMPRESSOR_TAG))
        {
            Player player = event.getPlayer();
            player.sendMessage(Component.text("圧縮機"));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        NBTBlock nbtBlock = new NBTBlock(block);
        if (nbtBlock.getData().getBoolean(COMPRESSOR_TAG))
        {
            ItemStack result = ItemCreator.of(CompMaterial.IRON_BLOCK).name("圧縮機").lore("鉱石をブロックに変換します。").enchant(Enchantment.UNBREAKING, 1).flags(CompItemFlag.HIDE_ENCHANTS).make();
            NBTItem nbtItem = new NBTItem(result);
            nbtItem.setString("CoreHubMachine", "Compressor");
            result = nbtItem.getItem();

            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getWorld().dropItemNaturally(block.getLocation(), result);
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        Block hopperBlock = event.getDestination().getLocation().getBlock();
        Block lowerMachine = hopperBlock.getRelative(0, -1, 0);
        Block lowerHopper = hopperBlock.getRelative(0, -2, 0);

        NBTBlock machineNbt = new NBTBlock(lowerMachine);

        if (machineNbt.getData().getBoolean(COMPRESSOR_TAG) && hopperBlock.getType() == Material.HOPPER) {
            Container hopperContainer = (Container) hopperBlock.getState();
            if (hopperContainer.getInventory().contains(Material.IRON_INGOT, 8)) {
                for (ItemStack item : hopperContainer.getInventory().getContents()) {
                    if (item != null && item.getType() == Material.IRON_INGOT) {
                        if (item.getAmount() >= 9)
                        {
                            item.setAmount(item.getAmount() - 9);
                        }
                    }
                }

                Container container = (Container) lowerHopper.getState();
                Inventory hopperInventory = container.getInventory();
                hopperInventory.addItem(new ItemStack(Material.IRON_BLOCK));
            }
        }
    }
}
