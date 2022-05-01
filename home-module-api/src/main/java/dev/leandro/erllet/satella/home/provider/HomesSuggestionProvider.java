package dev.leandro.erllet.satella.home.provider;

import dev.leandro.erllet.satella.home.model.Home;
import dev.leandro.erllet.satella.home.model.HomeId;
import dev.leandro.erllet.satella.home.service.HomeService;
import dev.alangomes.springspigot.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.stream.Collectors;

@Component
public class HomesSuggestionProvider implements Iterable<String> {

    @Autowired
    private HomeService homeService;

    @Autowired
    private Context context;

    @Override
    public Iterator<String> iterator() {
        return homeService.findByIdOwnerIgnoreCase(context.getPlayer().getName())
                    .stream()
                    .map(Home::getId)
                    .map(HomeId::getName)
                    .collect(Collectors.toList())
                    .iterator();
    }

}
