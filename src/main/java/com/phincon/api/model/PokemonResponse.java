package com.phincon.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.phincon.api.entity.Move;
import com.phincon.api.entity.TypeSlot;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonResponse {

    private int Id;
    
    private String name;
    
    private String image;

    @Nullable
    @JsonInclude(Include.NON_NULL)
    private String nickname;

    @Nullable
    @JsonInclude(Include.NON_NULL)
    private Integer pokemon_id;

    @Nullable
    @JsonInclude(Include.NON_NULL)
    private List<Move> moves;

    @Nullable
    @JsonInclude(Include.NON_NULL)
    private List<TypeSlot> types;
}
