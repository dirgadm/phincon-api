package com.phincon.api.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.phincon.api.entity.Pokemon;
import com.phincon.api.model.PokemonResponse;
import com.phincon.api.model.WebResponse;

@RestController
public class PokemonUserController {
    
    @GetMapping(
        path = "/api/pokemon/list",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<Pokemon>> get() {
        String url = "https://pokeapi.co/api/v2/pokemon";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PokemonResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<PokemonResponse>() {}
        );
        System.out.println(response.getBody().toString());

        List<Pokemon> pokemons = response.getBody().getPokemons();
        return WebResponse.<List<Pokemon>>builder().data(pokemons).build();
    }
}

//  public WebResponse<List<Pokemon>> get() {
//         String url = "https://pokeapi.co/api/v2/pokemon";

//         RestTemplate restTemplate = new RestTemplate();

//         ResponseEntity<PokemonResponse> response = restTemplate.exchange(
//             url,
//             HttpMethod.GET,
//             null,
//             new ParameterizedTypeReference<PokemonResponse>() {}
//         );

//         List<Pokemon> pokemons = response.getBody().getPokemons();
//         return WebResponse.<List<Pokemon>>builder().data(pokemons).build();
//     }

// public WebResponse<Object> get(){
//         String url = "https://pokeapi.co/api/v2/pokemon";

//         RestTemplate restTemplate = new RestTemplate();

//         Object pokemons = restTemplate.getForObject(url, Object.class);
//         return WebResponse.<Object>builder().data(pokemons).build();
//     }
