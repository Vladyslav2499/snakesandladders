# API endpoints

These endpoints allow you to perform player actions in Snakes and Ladders game.



### POST /api/snakes-and-ladders/games
Creates new game and player

**Response**

```
{
    "id": 1,
    "player": {
        "id": 1,
        "tokenPosition": 1,
        "winner": false
    },
    "rolledValue": 0
}
```
___

### PUT /api/snakes-and-ladders/games/[gameId]/player/[playerId]
Performs player token movement by dice rolled value

**Response**

```
{
    "id": 1,
    "player": {
        "id": 1,
        "tokenPosition": 3,
        "winner": false
    },
    "rolledValue": 2
}

or error if game with gameId not found

{
    "status": "NOT_FOUND",
    "message": "Game with id 2 not found."
}

or error if player with playerId not found

{
    "status": "NOT_FOUND",
    "message": "Player with id 2 not found."
}
```
