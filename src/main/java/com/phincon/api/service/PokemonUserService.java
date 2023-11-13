package com.phincon.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phincon.api.entity.PokemonUser;
import com.phincon.api.entity.User;
import com.phincon.api.model.CatchPokemonRequest;
import com.phincon.api.model.ReleasePokemon;
import com.phincon.api.pkg.CustomValidation;
import com.phincon.api.repository.PokemonUserRepository;

import jakarta.transaction.Transactional;

@Service
public class PokemonUserService {
    
    @Autowired
    private PokemonUserRepository pokemonUserRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public String catchPokemon(CatchPokemonRequest request,User user){
        validationService.validate(request);

        PokemonUser pokemonUser = pokemonUserRepository.findFirstByUserIdAndPokemonId(user.getUsername(), request.getPokemonId());
        if (pokemonUser != null){
            return "Pokemon already in deck ";
        }

        /*
         *  The Probability 50% determined by number
         *  1: Success
         *  2: Failed
         */
        int number = CustomValidation.GenerateRandomNumber(1, 2);
        if (number == 2) {
            return "Failed to catch Pokemon, it already gone!!!";
        }

        pokemonUser = new PokemonUser();

        pokemonUser.setUserId(user.getUsername());
        pokemonUser.setPokemonId(request.getPokemonId());
        pokemonUser.setPokemonNickname(request.getNickname());
        pokemonUser.setCurentFibo(0);

        pokemonUserRepository.save(pokemonUser);

        return "OK";
        
    }

    @Transactional
    public String releasePokemon(ReleasePokemon request,User user){
        validationService.validate(request);

        PokemonUser pokemonUser = pokemonUserRepository.findFirstByUserIdAndPokemonId(user.getUsername(), request.getPokemonId());
        if (pokemonUser == null){
            return "Pokemon is not belong to this user ";
        }

        int number = CustomValidation.GenerateRandomNumber(1, 100);
        if (!CustomValidation.IsPrime(number)) {
            return "Not Prime number: "+number+" failed to release pokemon";
        }

        pokemonUserRepository.delete(pokemonUser);

        return "OK";
        
    }
}
