package jp.reitou_mugicha.corehub.compressor;

import de.tr7zw.nbtapi.NBTItem;
import jp.reitou_mugicha.corehub.CoreHub;
import jp.reitou_mugicha.corehub.craft.CustomRecipes;
import jp.reitou_mugicha.corehub.craft.ICustomRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompItemFlag;
import org.mineacademy.fo.remain.CompMaterial;

public class CompressorRecipe implements ICustomRecipe
{
    public void register()
    {
        ItemStack result = ItemCreator.of(CompMaterial.IRON_BLOCK).name("圧縮機").lore("鉱石をブロックに変換します。").enchant(Enchantment.UNBREAKING, 1).flags(CompItemFlag.HIDE_ENCHANTS).make();
        NBTItem nbtItem = new NBTItem(result);
        nbtItem.setString("CoreHubMachine", "Compressor");
        result = nbtItem.getItem();

        NamespacedKey key = new NamespacedKey(CoreHub.getInstance(), "compressor");

        if (Bukkit.getRecipe(key) != null) {
            return;
        }

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("III", "DDD", "RRR");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
