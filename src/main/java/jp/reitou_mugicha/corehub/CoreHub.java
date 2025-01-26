package jp.reitou_mugicha.corehub;

import jp.reitou_mugicha.corehub.craft.Test;
import jp.reitou_mugicha.corehub.extended_enderchest.EnderchestDataManager;
import jp.reitou_mugicha.corehub.extended_enderchest.EnderchestUpgrade;
import jp.reitou_mugicha.corehub.extended_enderchest.ExtendedEnderchest;
import jp.reitou_mugicha.corehub.feature.*;
import jp.reitou_mugicha.corehub.fix.FixAnvilSweepingEdge;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class CoreHub extends SimplePlugin
{
    public static EnderchestDataManager enderchestDataManager;

    @Override
    public void onPluginStart()
    {
        enderchestDataManager = new EnderchestDataManager(getDataFolder());

        // EVENT REGISTER
        event(new FixAnvilSweepingEdge());
        event(new InstantChest());
        event(new BulkTrading());
        event(new ExperienceTrading());
        event(new UnlimitedAnvil());
        event(new ExtendedEnderchest(enderchestDataManager));
        event(new EnderchestUpgrade(enderchestDataManager));

        Test.register();
        Bukkit.getLogger().info("CoreHub is Enabled.");
    }

    private void event(Listener listener)
    {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onPluginStop()
    {
        Bukkit.getLogger().info("CoreHub is Disabled.");
    }
}
