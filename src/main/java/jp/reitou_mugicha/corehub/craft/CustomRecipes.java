package jp.reitou_mugicha.corehub.craft;

import jp.reitou_mugicha.corehub.compressor.CompressorRecipe;

import java.util.ArrayList;
import java.util.List;

public class CustomRecipes
{
    public static List<ICustomRecipe> recipes = new ArrayList<>();

    public static void add(ICustomRecipe recipe)
    {
        recipes.add(recipe);
    }

    public static void registerAll()
    {
        add(new Test());
        add(new CompressorRecipe());

        for (ICustomRecipe recipe : recipes)
        {
            recipe.register();
        }
    }
}
