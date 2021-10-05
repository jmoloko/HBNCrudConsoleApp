package com.milk.consoleapp;

import com.milk.consoleapp.view.MainView;
import org.hibernate.Session;

/**
 * @author Jack Milk
 */
public class Main {
    public static void main(String[] args) {
//        FlywayUtils.flywayMigrations();
        MainView.mainView();
    }
}
