# User Pokemon API SPEC

## Get Pokemons

Source Pokemon: https://pokeapi.co/

Endpoint : GET /api/pokemons 

Querystring: offset=0, limit=100 

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Response Body (Success) :

```json
{"data": 
    [
        {
            "name": "bulbasaur",
            "image": "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/006-Gmax.png",
            "id": 1
        },
        {
            "name": "ivysaur",
            "image": "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/006-Gmax.png",
            "id": 2
        }
    ]
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Get Pokemon Id

Source Pokemon: https://pokeapi.co/

Endpoint : GET /api/pokemon/:id

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Response Body (Success) :

```json
{
  "data" : {
    "name" : "Bulbasaur",
    "image":"",
    "moves":[
      {
        "name":"transform"
      }
    ],
    "types":[
      {
        "name":"normal"
      }
    ],
    "id":1
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Get My Pokemon List

Endpoint : GET /api/user/current/pokemon/

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Response Body (Success) :

```json
{
    "data": [
        {
            "name": "butterfree",
            "image": "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/807.png",
            "nickname": "Mighty flash-0",
            "pokemon_id": 12,
            "id": 2
        }
    ]
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Catch Pokemon

Endpoint : PUT /api/pokemon/catch

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Request Body :
```json
{
  "pokemon_id":1,
  "nickname":"Mighty Pikachu"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

Response Body (Failed, 400) :

```json
{
  "errors" : "Pokemon already in deck"
}
```

## Release Pokemon

Endpoint : DELETE /api/pokemon/release

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Request Body :
```json
{
  "pokemon_id":1
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Rename Pokemon

Endpoint : PUT /api/pokemon/rename

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Request Body :
```json
{
  "pokemon_id":1,
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```
