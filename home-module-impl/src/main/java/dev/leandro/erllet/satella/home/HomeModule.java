package dev.leandro.erllet.satella.home;


import dev.leandro.erllet.satella.Module;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@Configuration
@ComponentScan
@EnableScheduling
@EnableJpaRepositories
@Module("home")
public class HomeModule {
	
}
