package com.github.games647.fastlogin.bukkit.hook.locklogin;

import com.github.games647.fastlogin.bukkit.FastLoginBukkit;
import com.github.games647.fastlogin.core.hooks.AuthPlugin;
import ml.karmaconfigs.lockloginmodules.spigot.Module;
import ml.karmaconfigs.lockloginsystem.spigot.api.OfflineAPI;
import ml.karmaconfigs.lockloginsystem.shared.AuthResult;
import ml.karmaconfigs.lockloginsystem.spigot.api.PlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class LockLoginHook implements AuthPlugin<Player>, Listener {

    private final FastLoginBukkit plugin;

    public LockLoginHook(FastLoginBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean forceLogin(Player player) {
        Module module = new FastLoginModule();
        PlayerAPI p_api = new PlayerAPI(module, player);

        if (p_api.isLogged()) {
            plugin.getLog().warn(ALREADY_AUTHENTICATED, player);
            return false;
        }

        AuthResult result = p_api.tryLogin(true);
        return result.equals(AuthResult.SUCCESS) || result.equals(AuthResult.SUCCESS_TEMP);
    }

    @Override
    public boolean forceRegister(Player player, String password) {
        Module module = new FastLoginModule();
        PlayerAPI p_api = new PlayerAPI(module, player);

        if (!p_api.isRegistered()) {
            AuthResult result = p_api.tryRegister(password);

            return result.equals(AuthResult.SUCCESS) || result.equals(AuthResult.SUCCESS_TEMP);
        } else {
            return forceLogin(player);
        }
    }

    @Override
    public boolean isRegistered(String playerName) {
        Module module = new FastLoginModule();
        OfflineAPI offline = new OfflineAPI(module, playerName);

        return offline.isRegistered();
    }
}
