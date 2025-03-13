# Assignment A2 - Rescue mission

## Product Description

This product is an _exploration command center_ for the [Island](https://ace-design.github.io/island/) serious game. 

- The `ca.mcmaster.se2aa4.island.team_XXX_.Explorer` class implements the command center, used to compete with the others. (You can replace `teamXXX` with your team's name if you'd like to.)
- The `Runner` class allows one to run the command center on a specific map.

### Strategy description

The exploration strategy is to first locate the coast and follow the coast line till a creek is spotted. From there, the drone is programmed to fly towards the middle of the island and spiral out untill it scans
the emergency site. Once both are located, it flys back to the base with the remaining battery it has left.

## How to compile, run and deploy

### Compiling the project:

```mvn clean package```

This creates one jar file in the `target` directory, named after the team identifier (i.e., team 00 uses `team00-1.0.jar`).

### Run the project

Execution for testing purposes:

```mvn exec:java -q -Dexec.args="./maps/map03.json"```

It creates three files in the `outputs` directory:

- `_pois.json`: the location of the points of interests
- `Explorer_Island.json`: a transcript of the dialogue between the player and the game engine
- `Explorer.svg`: the map explored by the player, with a fog of war for the tiles that were not visited.
