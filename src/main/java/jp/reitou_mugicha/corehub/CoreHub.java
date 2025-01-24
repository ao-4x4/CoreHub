package jp.reitou_mugicha.corehub;

import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class CoreHub extends SimplePlugin
{
    @Override
    public void onPluginStart()
    {
        Bukkit.getLogger().info("CoreHub is Enabled.");
    }

    @Override
    public void onPluginStop()
    {
        Bukkit.getLogger().info("CoreHub is Disabled.");
    }
}
