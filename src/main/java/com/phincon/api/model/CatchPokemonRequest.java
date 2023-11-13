package com.phincon.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatchPokemonRequest {
    
    @NotNull
    @Positive
    private Integer pokemonId;

    @NotBlank
    @Size(max = 100)
    private String nickname;
}
