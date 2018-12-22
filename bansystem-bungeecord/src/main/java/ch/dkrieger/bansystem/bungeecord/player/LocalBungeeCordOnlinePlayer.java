package ch.dkrieger.bansystem.bungeecord.player;

import ch.dkrieger.bansystem.bungeecord.BungeeCordBanSystemBootstrap;
import ch.dkrieger.bansystem.lib.BanSystem;
import ch.dkrieger.bansystem.lib.player.NetworkPlayer;
import ch.dkrieger.bansystem.lib.player.OnlineNetworkPlayer;
import ch.dkrieger.bansystem.lib.player.history.BanType;
import ch.dkrieger.bansystem.lib.player.history.entry.Ban;
import ch.dkrieger.bansystem.lib.player.history.entry.Kick;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class LocalBungeeCordOnlinePlayer implements OnlineNetworkPlayer {

    private ProxiedPlayer player;

    public LocalBungeeCordOnlinePlayer(ProxiedPlayer player) {
        this.player = player;
    }

    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public String getProxy() {
        return BungeeCordBanSystemBootstrap.getInstance().getProxyName();
    }

    @Override
    public String getServer() {
        return player.getServer().getInfo().getName();
    }

    @Override
    public int getPing() {
        return player.getPing();
    }

    @Override
    public NetworkPlayer getPlayer() {
        return BanSystem.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
    }
    @Override
    public void sendMessage(String message) {
        sendMessage(new TextComponent(message));
    }

    @Override
    public void sendMessage(TextComponent component) {
        player.sendMessage(component);
    }

    @Override
    public void connect(String server) {
        ServerInfo serverInfo = BungeeCord.getInstance().getServerInfo(player.getName());
        if(serverInfo != null && !(player.getServer().getInfo().equals(serverInfo))) player.connect(serverInfo);
    }

    @Override
    public void executeCommand(String command) {
        if(command.startsWith("/")) command = command.substring(1);
        BungeeCord.getInstance().getPluginManager().dispatchCommand(player,command);
    }
    @Override
    public void sendBan(Ban ban) {
        if(ban.getBanType() == BanType.NETWORK) player.disconnect(ban.toMessage());
        else player.sendMessage(ban.toMessage());
    }

    @Override
    public void kick(Kick kick) {
        player.disconnect(kick.toMessage());
    }
}
