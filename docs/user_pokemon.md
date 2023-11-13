# User Pokemon API SPEC

## Get Pokemons

Source Pokemon: https://pokeapi.co/

Endpoint : GET /api/pokemons 

Querystring: offset=0, limit=100 

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Response Body (Success) :

```json
{
  "data" : {
    "name" : "Bulbasaur",
    "image":""
  }
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
    ]
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
  "data" : {
    "pokemon":{
      "name" : "Bulbasaur",
      "image":""
    },
    "user_id":"dirga",
    "pokemon_nickname":"dirga_bulbas"
  }
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
  "user_id":"dirga",
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
  "user_id":"dirga",
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
  "user_id":"dirga",
  "pokemon_id":1,
  "nickname":"BulbasDirDir"
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
