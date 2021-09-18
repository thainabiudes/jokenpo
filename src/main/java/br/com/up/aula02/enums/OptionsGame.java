package br.com.up.aula02.enums;

import java.util.Random;

public enum OptionsGame {
    TESOURA,
    PEDRA,
    PAPEL;

    public static OptionsGame getRandomOptionsGame() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
