package dev.leandro.erllet.satella.home.commands;

import dev.leandro.erllet.satella.home.service.HomeService;
import dev.alangomes.springspigot.command.Subcommand;
import dev.alangomes.springspigot.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Subcommand
@CommandLine.Command(name = "delete")
public class HomeDeleteCommand implements Callable<String> {

    @Autowired
    private HomeService homeService;
    
    @Autowired
    private Context context;
    
    @CommandLine.Parameters(index = "0", defaultValue = "principal")
    private String homeName;

    @Override
    public String call() {
        homeService.deleteHome(homeName);
    	return String.format("&aVocê deletou a home &e%s&a!", homeName);
    	
    }

}
