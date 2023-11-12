package com.phincon.api.model;
import java.util.List;

import com.phincon.api.entity.Pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonResponse {
    private List<Pokemon> pokemons;
    // Getters and setters
}
