package com.phincon.api.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.phincon.api.entity.PokemonUser;
import com.phincon.api.entity.User;
import com.phincon.api.model.CatchPokemonRequest;
import com.phincon.api.model.ReleasePokemon;
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
        int number = generateRandomNumber(1, 2);
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

        int number = generateRandomNumber(1, 100);
        if (!isPrime(number)) {
            return "Not Prime number: "+number+" failed to release pokemon";
        }

        pokemonUserRepository.delete(pokemonUser);

        return "OK";
        
    }

    public static int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
