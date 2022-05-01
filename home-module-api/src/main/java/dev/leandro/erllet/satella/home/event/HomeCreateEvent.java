package dev.leandro.erllet.satella.home.event;


import dev.leandro.erllet.satella.home.model.Home;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class HomeCreateEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Home home;

    private final CommandSender sender;

    @Setter
    private boolean cancelled;

    public HomeCreateEvent(Home home, CommandSender sender) {
        this.home = home;
        this.sender = sender;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
