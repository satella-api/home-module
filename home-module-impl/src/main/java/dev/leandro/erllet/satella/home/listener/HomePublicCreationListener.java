package dev.leandro.erllet.satella.home.listener;

import dev.leandro.erllet.satella.home.event.HomeCreateEvent;
import dev.leandro.erllet.satella.home.service.HomeService;
import dev.alangomes.springspigot.extensions.EconomyService;
import lombok.val;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@ConditionalOnClass(EconomyService.class)
@Component
public class HomePublicCreationListener implements Listener {

    @Autowired
    private HomeService homeService;


    @EventHandler
    public void onHomeCreation(HomeCreateEvent event) {
        val sender = event.getSender();
        val home = event.getHome();
        if(sender.hasPermission("home.publica.bypass")) {
            return;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eVocê pagou R$ 1.000,00 para setar uma home publica"));

    }
}
