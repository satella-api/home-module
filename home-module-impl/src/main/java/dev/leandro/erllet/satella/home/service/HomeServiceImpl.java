package dev.leandro.erllet.satella.home.service;

import dev.leandro.erllet.satella.home.event.HomeCreateEvent;
import dev.leandro.erllet.satella.home.model.Home;
import dev.leandro.erllet.satella.home.model.HomeId;
import dev.leandro.erllet.satella.home.model.HomeType;
import dev.leandro.erllet.satella.home.repository.HomeRepository;
import dev.leandro.erllet.satella.service.LocationSerializationService;
import dev.alangomes.springspigot.context.Context;
import dev.alangomes.springspigot.security.Authorize;
import lombok.val;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class HomeServiceImpl implements HomeService {

    private static final Pattern PATTTERN = Pattern.compile("[^a-zA-Z0-9_]+");

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private Server server;

    @Autowired
    private LocationSerializationService locationSerializationService;

    @Autowired
    private Context context;


    public void init() {
        homeRepository.findAll().stream()
                .filter(home -> home.getHomeType().equals(HomeType.TEMPORARY))
                .forEach(home -> homeRepository.delete(home));
    }


    @Override
    public List<Home> findByIdOwnerIgnoreCase(String owner) {
        return homeRepository.findByIdOwnerIgnoreCase(owner);
    }

    @Override
    public Optional<Home> findById(HomeId id) {
        return homeRepository.findById(id);
    }

    @Override
    @Authorize(
            value = "hasPermission('home.delete')",
            message = "Você não tem permissão para isto!"
    )
    public void deleteHome(String name) {
        val player = context.getPlayer();
        val id = getIdFromInput(name);
        val home = findById(id)
                .filter(playerHome -> player.getName().equalsIgnoreCase(playerHome.getId().getOwner()) || player.hasPermission("home.delete.others"))
                .orElseThrow(() -> new CommandException("Esta home não existe!"));
        homeRepository.delete(home);
    }

    @Override
    @Authorize(
            value = "hasPermission('home.acessar')",
            message = "Você não tem permissão para isto!"
    )
    public void joinHome(String name) {
        val player = context.getPlayer();
        val homeId = getIdFromInput(name);
        val location = findById(homeId)
                .filter(home -> player.getName().equalsIgnoreCase(home.getId().getOwner()) || home.getHomeType().equals(HomeType.PUBLIC))
                .map(home -> locationSerializationService.deserializeLocation(home.getLocation()))
                .orElseThrow(() -> new CommandException("Esta home não existe!"));
        player.teleport(location);
    }

    @Override
    @Authorize(
            value = "hasPermission('home.create')",
            message = "Você não tem permissão para isto!"
    )
    public void createHome(String name, HomeType homeType) {
        val player = context.getPlayer();
        val homeId = getIdFromInput(name);
        val home =findById(homeId)
                .orElse(new Home());
        saveHome(homeType, player, homeId, home);
    }

    private void saveHome(HomeType homeType, Player player, HomeId homeId, Home home) {
        home.setId(homeId);
        home.setHomeType(homeType);
        home.setLocation(locationSerializationService.serializeLocation(player.getLocation().clone()));
        val event = new HomeCreateEvent(home, player);
        server.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            homeRepository.save(home);
        }
    }

    @Override
    public ChatColor getTypeColor(HomeType homeType) {
        return switch (homeType) {
            case PUBLIC -> ChatColor.GREEN;
            case PRIVATE -> ChatColor.RED;
            case TEMPORARY -> ChatColor.GOLD;
            case DEFAULT -> ChatColor.YELLOW;
        };
    }

    private HomeId getIdFromInput(String name) {
        String owner = context.getPlayer().getName();

        if (name.contains(":")) {
            val parts = name.split(":");
            if (parts.length == 2) {
                owner = parts[0];
                name = parts[1];
            }
        }
        val id = new HomeId();
        id.setName(name.toLowerCase());
        id.setOwner(owner.toLowerCase());
        return id;
    }
}
