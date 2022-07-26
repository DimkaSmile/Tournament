package ru.netology.game;

import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.ArrayList;
import java.util.Collection;

public class Game {
    private final Collection<Player> items = new ArrayList<>();


    public void setPlayersRegistered(Player player) {
        items.add(player);
    }

    public void checkForRegistered(String name) throws NotRegisteredException {
        if (searchByName(name) == null) {
            throw new NotRegisteredException("Player " + name + " is not registered");
        }
    }

    public Player searchByName(String name) {
        for (Player player : items) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public int round(String firstName, String secondName) throws NotRegisteredException {

        checkForRegistered(firstName);
        checkForRegistered(secondName);
        if (searchByName(firstName).getStrength() > searchByName(secondName).getStrength()) {
            return 1;
        }
        if (searchByName(firstName).getStrength() < searchByName(secondName).getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }
}
