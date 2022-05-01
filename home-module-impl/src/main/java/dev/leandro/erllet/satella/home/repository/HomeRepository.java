package dev.leandro.erllet.satella.home.repository;

import dev.leandro.erllet.satella.home.model.Home;
import dev.leandro.erllet.satella.home.model.HomeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home, HomeId> {
	
	List<Home> findByIdOwnerIgnoreCase(String owner);
}
