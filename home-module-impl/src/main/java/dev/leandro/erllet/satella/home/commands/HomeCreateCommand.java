package dev.leandro.erllet.satella.home.commands;


import dev.leandro.erllet.satella.home.model.HomeType;
import dev.leandro.erllet.satella.home.service.HomeService;
import dev.alangomes.springspigot.command.Subcommand;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Subcommand
@CommandLine.Command(name = "create", aliases = {"set"})
public class HomeCreateCommand implements Callable<String> {

    @Autowired
    private HomeService homeService;

    @CommandLine.Parameters(index = "0", defaultValue = "principal")
    private String homeName;

    @CommandLine.Parameters(index = "1", defaultValue = "PRIVATE")
    private HomeType homeType;

    @Override
    public String call() {
        homeService.createHome(homeName, homeType);
        return String.format("&aVocê criou a home &e%s&a!", homeName);

    }

}
