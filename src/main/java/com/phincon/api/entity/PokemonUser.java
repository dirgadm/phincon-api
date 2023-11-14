package com.phincon.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pokemon_users")
public class PokemonUser {
    @Id
    private int id;
    
    @Column(name = "user_id")
    private String userId;

    @Column(name = "pokemon_id")
    private Integer pokemonId;

    @Column(name = "pokemon_nickname")
    private String pokemonNickname;
    
    @Column(name = "current_fibo")
    private int curentFibo;

    @Column(name = "pokemon_name")
    private String pokemonName;
}
