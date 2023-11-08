# User API SPEC

## Register User

Endpoint:  POST /api/user/register

Request body:
```json
{
    "username":"dirga",
    "password":"rahasia",
    "name":"Dirga Meligo"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed, 400) :

```json
{
  "errors" : "Bad Request"
}
```

## Login User

Endpoint:  POST /api/auth/login

Request body:
```json
{
    "username":"dirga",
    "password":"rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 2342342423423 // milliseconds
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Username or password wrong"
}
```

## Get User

Endpoint : GET /api/user/current

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Response Body (Success) :

```json
{
  "data" : {
    "username" : "dirga",
    "name" : "Dirga Meligo"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```
