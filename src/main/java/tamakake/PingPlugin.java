package tamakake.ping;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PingPlugin extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("ping")) return false;

        if (!(sender instanceof Player player)) {
            sender.sendMessage("プレイヤーのみ使用できます");
            return true;
        }

        // 引数なし → 自分のPing
        if (args.length == 0) {
            int ping = player.getPing();
            player.sendMessage("§fping:" + ping + "ms");
            return true;
        }

        // 引数あり → 他人のPing（権限チェック）
        if (!player.hasPermission("tamakake.admin")) {
            player.sendMessage("§c権限がありません");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cプレイヤーが見つかりません");
            return true;
        }

        int ping = target.getPing();
        player.sendMessage("§f" + target.getName() + " ping:" + ping + "ms");

        return true;
    }
}