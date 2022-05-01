package dev.leandro.erllet.satella.home.commands;

import dev.leandro.erllet.satella.home.service.HomeService;
import dev.alangomes.springspigot.command.Subcommand;
import dev.alangomes.springspigot.context.Context;
import lombok.val;
import me.rayzr522.jsonmessage.JSONMessage;
import org.bukkit.ChatColor;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine;

@Subcommand
@CommandLine.Command(name = "list")
public class HomeListCommand implements Runnable {

    @Autowired
    private HomeService homeService;

    @Autowired
    private Context context;

    @Override
    public void run() {
        val player = context.getPlayer();
        val homes = homeService.findByIdOwnerIgnoreCase(player.getName());
        val msg = JSONMessage.create();
        homes.forEach(backpack -> {
            msg.then(backpack.getId().getName());
            msg.runCommand("/home " + backpack.getId().getName());
            val type = backpack.getHomeType();
            msg.tooltip(
                    JSONMessage.create("Visibilidade: ")
                            .color(ChatColor.WHITE)
                            .then(type.getDescription())
                            .color(homeService.getTypeColor(type))
            );
            msg.color(homeService.getTypeColor(type)).then(", ").color(ChatColor.GOLD);
        });
        player.sendMessage(ChatColor.GREEN + "Lista de homes : (clique no nome para abrir)");
        msg.send(player);
        if (homes.isEmpty()) {
            player.sendMessage(ChatColor.RED + "Você não possui nenhuma home.");
        }


    }

}
