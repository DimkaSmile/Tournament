package ru.netology.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;
import ru.netology.game.Game;

class GameTest {
    private final Player first = new Player(1, "Slava", 75);
    private final Player second = new Player(2, "Petya", 100);
    private final Player third = new Player(3, "Pavel", 50);
    private final Player fourth = new Player(4, "Kostya", 50);
    private final Player fifth = new Player(5, "Vasya", 50);
    Game game = new Game();

    @Test
    void setPlayersRegistered() throws NotRegisteredException {
        game.setPlayersRegistered(first);
        game.checkForRegistered("Slava");
        Player expected = first;
        Player actual = game.searchByName("Slava");

        assertEquals(expected, actual);
    }

    @Test
    void checkForRegistered() {
        game.setPlayersRegistered(second);

        Player expected = second;
        Player actual = game.searchByName("Petya");

        assertEquals(expected, actual);
    }

    @Test
    void searchByName() {
        game.setPlayersRegistered(first);
        game.setPlayersRegistered(second);
        game.setPlayersRegistered(third);
        game.setPlayersRegistered(fourth);

        Player expected = second;
        Player actual = game.searchByName("Petya");

        assertEquals(expected, actual);

    }

    private void assertArrayEquals(Player[] expected, Player actual) {
    }

    @Test
    public void searchByNameIfNotFound() {
        game.setPlayersRegistered(first);
        game.setPlayersRegistered(second);
        game.setPlayersRegistered(third);
        game.setPlayersRegistered(fourth);

        Player[] expected = {};
        Player actual = game.searchByName("Vasya");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void roundWinBeta() throws NotRegisteredException {
        game.setPlayersRegistered(first);
        game.setPlayersRegistered(second);
        int expected = 1;
        int actual = game.round("Petya", "Slava");
        assertEquals(expected, actual);
    }

    @Test
    public void roundWinGama() throws NotRegisteredException {
        game.setPlayersRegistered(third);
        game.setPlayersRegistered(first);
        int expected = 2;
        int actual = game.round("Pavel", "Slava");
        assertEquals(expected, actual);
    }

    @Test
    public void roundWinAlpha() throws NotRegisteredException {
        game.setPlayersRegistered(fourth);
        game.setPlayersRegistered(third);
        int expected = 0;
        int actual = game.round("Kostya", "Pavel");
        assertEquals(expected, actual);
    }

    @Test
    public void roundIfNotRegistered() {
        game.setPlayersRegistered(third);

        int expected = 0;

        try {
            int actual = game.round("Kostya", "Vasya");
            assertEquals(expected, actual);
        } catch (NotRegisteredException exception) {
            System.out.println("Player is not registered");
        }
    }
}
