package com.phincon.api.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phincon.api.entity.PokemonUser;

@Repository
public interface PokemonUserRepository extends JpaRepository<PokemonUser,Integer> {
    PokemonUser findFirstByUserIdAndPokemonId(String user_id, int pokemon_id);
    List<PokemonUser> findAllByUserId(String user_id);
}
