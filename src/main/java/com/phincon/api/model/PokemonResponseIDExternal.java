package com.phincon.api.model;
import java.util.List;

import com.phincon.api.entity.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonResponseIDExternal {
    private String name;
    private List<Move> moves;
    private List<TypeSlot> types;
    private int weight;
}
