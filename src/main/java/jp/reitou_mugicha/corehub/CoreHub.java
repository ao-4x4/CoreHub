package jp.reitou_mugicha.corehub;

import jp.reitou_mugicha.corehub.craft.Test;
import jp.reitou_mugicha.corehub.extended_enderchest.EnderchestDataManager;
import jp.reitou_mugicha.corehub.extended_enderchest.EnderchestUpgrade;
import jp.reitou_mugicha.corehub.extended_enderchest.ExtendedEnderchest;
import jp.reitou_mugicha.corehub.feature.BulkTrading;
import jp.reitou_mugicha.corehub.feature.ExperienceTrading;
import jp.reitou_mugicha.corehub.feature.InstantChest;
import jp.reitou_mugicha.corehub.feature.UnlimitedAnvil;
import jp.reitou_mugicha.corehub.fix.FixAnvilSweepingEdge;
import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class CoreHub extends SimplePlugin
{
    public static EnderchestDataManager enderchestDataManager;

    @Override
    public void onPluginStart()
    {
        enderchestDataManager = new EnderchestDataManager(getDataFolder());

        // EVENT REGISTER
        getServer().getPluginManager().registerEvents(new FixAnvilSweepingEdge(), this);
        getServer().getPluginManager().registerEvents(new InstantChest(), this);
        getServer().getPluginManager().registerEvents(new BulkTrading(), this);
        getServer().getPluginManager().registerEvents(new ExperienceTrading(), this);
        getServer().getPluginManager().registerEvents(new UnlimitedAnvil(), this);
        getServer().getPluginManager().registerEvents(new ExtendedEnderchest(enderchestDataManager), this);
        getServer().getPluginManager().registerEvents(new EnderchestUpgrade(enderchestDataManager), this);

        Test.register();
        Bukkit.getLogger().info("CoreHub is Enabled.");
    }

    @Override
    public void onPluginStop()
    {
        Bukkit.getLogger().info("CoreHub is Disabled.");
    }
}
