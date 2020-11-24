package com.github.games647.fastlogin.bukkit.hook.locklogin;

import com.github.games647.fastlogin.bukkit.FastLoginBukkit;
import ml.karmaconfigs.lockloginmodules.spigot.Module;
import org.bukkit.plugin.java.JavaPlugin;

public class FastLoginModule extends Module {

    @Override
    public JavaPlugin owner() {
        return JavaPlugin.getProvidingPlugin(FastLoginBukkit.class);
    }

    @Override
    public String name() {
        return "FastLogin auth module";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public String author() {
        return "KarmaDev";
    }

    @Override
    public String description() {
        return "This module is used by FastLogin, a premium plugin for spigot and bungeecord";
    }

    @Override
    public String author_url() {
        return "";
    }
}
