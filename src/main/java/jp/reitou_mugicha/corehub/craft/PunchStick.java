package jp.reitou_mugicha.corehub.craft;

import jp.reitou_mugicha.corehub.CoreHub;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class PunchStick implements ICustomRecipe
{
    public void register() {
        ItemStack result = ItemCreator.of(CompMaterial.STICK).name("しかぱんち").lore("PUNCH THE DEER!").enchant(Enchantment.KNOCKBACK, 30).make();
        NamespacedKey key = new NamespacedKey(CoreHub.getInstance(), "punch_stick");

        if (Bukkit.getRecipe(key) != null) {
            return;
        }

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape(" S ", " S ");
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }
}
