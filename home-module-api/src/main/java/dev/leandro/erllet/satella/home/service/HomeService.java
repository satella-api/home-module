package dev.leandro.erllet.satella.home.service;

import dev.leandro.erllet.satella.home.model.Home;
import dev.leandro.erllet.satella.home.model.HomeId;
import dev.leandro.erllet.satella.home.model.HomeType;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Optional;

public interface HomeService {

    List<Home> findByIdOwnerIgnoreCase(String owner);

    Optional<Home> findById(HomeId id);

    void deleteHome(String name);

    void joinHome(String name);

    void createHome(String name, HomeType homeType);

    ChatColor getTypeColor(HomeType type);
}
