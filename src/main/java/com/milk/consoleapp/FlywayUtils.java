package com.milk.consoleapp;

import org.flywaydb.core.Flyway;
/**
 * @author Jack Milk
 */
public class FlywayUtils {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/hbn_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    public static void flywayMigrations() {
        System.out.println("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        System.out.println("db migration finished.");
    }

}
