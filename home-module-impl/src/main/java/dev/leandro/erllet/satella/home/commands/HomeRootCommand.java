package dev.leandro.erllet.satella.home.commands;

import dev.leandro.erllet.satella.home.service.HomeService;
import dev.alangomes.springspigot.context.Context;
import org.bukkit.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@CommandLine.Command(
        name = "home",
        subcommands = {HomeListCommand.class, HomeCreateCommand.class, HomeDeleteCommand.class}
)
public class HomeRootCommand implements Callable<String> {

    @Autowired
    private HomeService homeService;

    @Autowired
    private Context context;

    @CommandLine.Parameters(index = "0", defaultValue = "principal")
    private String homeName;

    @Override
    public String call() {
        Player player = context.getPlayer();
        homeService.joinHome(homeName);
        return String.format("&aVocê acessou a home &e%s&a!", homeName);
    }

}
