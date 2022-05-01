package dev.leandro.erllet.satella.home.listener;

import dev.leandro.erllet.satella.home.event.HomeCreateEvent;
import dev.leandro.erllet.satella.home.service.HomeService;
import lombok.val;
import org.bukkit.command.CommandException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeCreationListener implements Listener {

    @Autowired
    private HomeService homeService;

    @EventHandler
    public void onHomeCreation(HomeCreateEvent event) {
        val sender = event.getSender();
        val home = event.getHome();

        if (homeService.findByIdOwnerIgnoreCase(sender.getName()).size() >= 3
                && !sender.hasPermission("home.limit.bypass")) {
            event.setCancelled(true);
            throw new CommandException("Limite de home antigido, compre VIP e tenha homes ilimitadas!");
        }

        if (!sender.getName().equalsIgnoreCase(home.getId().getOwner()) && !sender.hasPermission("home.create.others")) {
            event.setCancelled(true);
            throw new CommandException("Sem permissão para isto!");
        }
    }
}
