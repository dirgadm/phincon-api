package com.phincon.api.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.phincon.api.entity.PokemonUser;
import com.phincon.api.entity.User;
import com.phincon.api.model.CatchPokemonRequest;
import com.phincon.api.model.PokemonResponse;
import com.phincon.api.model.PokemonResponseIDExternal;
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

        String url = "https://pokeapi.co/api/v2/pokemon/"+request.getPokemonId();

        RestTemplate restTemplate = new RestTemplate();
        PokemonResponseIDExternal pokemon = restTemplate.getForObject(url, PokemonResponseIDExternal.class);

        pokemonUser = new PokemonUser();

        pokemonUser.setUserId(user.getUsername());
        pokemonUser.setPokemonId(request.getPokemonId());
        pokemonUser.setPokemonNickname(request.getNickname());
        pokemonUser.setPokemonName(pokemon.getName());
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

    @Transactional
    public String renamePokemon(ReleasePokemon request,User user){
        validationService.validate(request);

        PokemonUser pokemonUser = pokemonUserRepository.findFirstByUserIdAndPokemonId(user.getUsername(), request.getPokemonId());
        if (pokemonUser == null){
            return "Pokemon is not belong to this user ";
        }

        String updatedNickName = "";
        int fib = CustomValidation.Fibonacci(pokemonUser.getCurentFibo());
        if (fib == 0){
            updatedNickName = pokemonUser.getPokemonNickname() + "-0";
        }else{
             String[] parts = pokemonUser.getPokemonNickname().split("-");
        if (parts.length == 2) {
            String prefix = parts[0];

            updatedNickName = prefix + "-" + fib;

            System.out.println(updatedNickName);
        }
        }

        pokemonUser.setPokemonNickname(updatedNickName);
        pokemonUser.setCurentFibo(pokemonUser.getCurentFibo()+1);

        pokemonUserRepository.save(pokemonUser);

        return "OK";
        
    }

    @Transactional
    public List<PokemonResponse> listPokemon(User user){

        String image_hardcoded = "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/807.png";

        List<PokemonResponse>response = new ArrayList<>();
        List<PokemonUser> pokemonUser = pokemonUserRepository.findAllByUserId(user.getUsername());

        for (PokemonUser p : pokemonUser){
            PokemonResponse temp = new PokemonResponse();
            temp.setId(p.getId());
            temp.setImage(image_hardcoded);
            temp.setNickname(p.getPokemonNickname());
            temp.setName(p.getPokemonName());
            temp.setPokemon_id(p.getPokemonId());
            response.add(temp);
        }
        return response;
        
    }
}
