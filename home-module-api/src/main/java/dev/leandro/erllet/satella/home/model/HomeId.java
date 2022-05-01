package dev.leandro.erllet.satella.home.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class HomeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", length = 17, nullable = false, updatable = false)
    private String name;

    @Column(name = "player_owner", length = 17, nullable = false, updatable = false)
    private String owner;

    @Override
    public String toString() {
        return name + "." + owner;
    }
}
