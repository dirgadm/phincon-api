package com.phincon.api.entity;

import java.util.List;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokemon {
    private String name;
    private String url;
    @Nullable
    private List<Move> moves;
    @Nullable
    private List<TypeSlot> types;
}
