package com.milk.consoleapp;

import com.milk.consoleapp.util.FlywayUtils;
import com.milk.consoleapp.view.MainView;

/**
 * @author Jack Milk
 */
public class Main {
    public static void main(String[] args) {
        FlywayUtils.flywayMigrations();
        MainView.mainView();
    }
}
