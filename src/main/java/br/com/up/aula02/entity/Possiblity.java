package br.com.up.aula02.entity;

import br.com.up.aula02.enums.OptionsGame;

public class Possiblity {
    private OptionsGame currentOption;
    private OptionsGame winOption;

    private Possiblity() {

    }

    public Possiblity(OptionsGame currentOption, OptionsGame winOption) {
        this.currentOption = currentOption;
        this.winOption = winOption;
    }

    public OptionsGame getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(OptionsGame currentOption) {
        this.currentOption = currentOption;
    }

    public OptionsGame getWinOption() {
        return winOption;
    }

    public void setWinOption(OptionsGame winOption) {
        this.winOption = winOption;
    }
}
