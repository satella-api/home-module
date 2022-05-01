package dev.leandro.erllet.satella.home.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "homes")
public class Home {

    @EmbeddedId
    private HomeId id;

    @Column(name = "location", nullable = false)
    private String location;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "home_type", nullable = false, columnDefinition = "smallint")
    private HomeType homeType = HomeType.DEFAULT;

}
