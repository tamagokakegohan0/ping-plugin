package tamakake.ping;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("ping").setExecutor(this); // ← これ追加
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("プレイヤーのみ使用できます");
            return true;
        }

        // 自分のPing
        if (args.length == 0) {
            player.sendMessage("§fping:" + player.getPing() + "ms");
            return true;
        }

        // 権限チェック
        if (!player.hasPermission("tamakake.admin")) {
            player.sendMessage("§c権限がありません");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cプレイヤーが見つかりません");
            return true;
        }

        player.sendMessage("§f" + target.getName() + " ping:" + target.getPing() + "ms");

        return true;
    }
}