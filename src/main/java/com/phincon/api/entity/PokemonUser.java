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
    private String pokemonId;

    @Column(name = "pokemon_nickname")
    private String pokemonNickname;
    
    @Column(name = "curent_fibo")
    private int curentFibo;
}
