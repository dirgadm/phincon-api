package com.phincon.api.model;
import java.util.List;

import com.phincon.api.entity.Pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonResponseListExternal {
    private int count;
    private String next;
    private String previous;
    private List<Pokemon> results;
}
