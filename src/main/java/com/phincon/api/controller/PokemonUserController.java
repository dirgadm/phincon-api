package com.phincon.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.phincon.api.entity.Pokemon;
import com.phincon.api.entity.User;
import com.phincon.api.model.CatchPokemonRequest;
import com.phincon.api.model.PokemonResponse;
import com.phincon.api.model.PokemonResponseIDExternal;
import com.phincon.api.model.PokemonResponseListExternal;
import com.phincon.api.model.ReleasePokemon;
import com.phincon.api.model.WebResponse;
import com.phincon.api.service.PokemonUserService;

@RestController
public class PokemonUserController {

    @Autowired
    private PokemonUserService pokemonUserService;
    
    @GetMapping(
        path = "/api/pokemons",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<PokemonResponse>> get(User user) {
        String url = "https://pokeapi.co/api/v2/pokemon";
        String image_hardcoded = "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/006-Gmax.png";

        List<PokemonResponse> pokemonResponse = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        PokemonResponseListExternal pokemons = restTemplate.getForObject(url, PokemonResponseListExternal.class);
        for (Pokemon p: pokemons.getResults()) {
            PokemonResponse poke = new PokemonResponse();
            String[] parts = p.getUrl().split("/");
            String numberId = parts[parts.length - 1];

            poke.setId(Integer.parseInt(numberId));
            poke.setImage(image_hardcoded);
            poke.setName(p.getName());

            pokemonResponse.add(poke);
        }
        
        return WebResponse.<List<PokemonResponse>>builder().data(pokemonResponse).build();
    }

     @GetMapping(
        path = "/api/pokemon/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PokemonResponse>getDetail(User user,@PathVariable("id") int id){
        System.out.println("input ID: "+ id);
        String url = "https://pokeapi.co/api/v2/pokemon/"+id;
        String image_hardcoded = "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/807.png";

        PokemonResponse pokemonResponse = new PokemonResponse();
        RestTemplate restTemplate = new RestTemplate();

        PokemonResponseIDExternal pokemon = restTemplate.getForObject(url, PokemonResponseIDExternal.class);

        pokemonResponse.setId(id);
        pokemonResponse.setName(pokemon.getName());
        pokemonResponse.setImage(image_hardcoded);
        pokemonResponse.setMoves(pokemon.getMoves());
        pokemonResponse.setTypes(pokemon.getTypes());

        return WebResponse.<PokemonResponse>builder().data(pokemonResponse).build();
    }

    @PutMapping(
        path = "/api/pokemon/catch",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String>catchPokemon(User user, @RequestBody CatchPokemonRequest request){
        
        String response = pokemonUserService.catchPokemon(request, user);

        return WebResponse.<String>builder().data(response).build();
    }

    @DeleteMapping(
        path = "/api/pokemon/release",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String>releasePokemon(User user, @RequestBody ReleasePokemon request){
        
        String response = pokemonUserService.releasePokemon(request, user);

        return WebResponse.<String>builder().data(response).build();
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
