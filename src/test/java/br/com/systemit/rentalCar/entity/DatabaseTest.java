package br.com.systemit.rentalCar.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseTest {

    static Connection connection;

    // should be declared in a static method
    @BeforeAll
    static void setUpDatabase() throws SQLException {
        var CREATE_TABLE_SQL = "CREATE TABLE users (id INT, name VARCHAR)";
        connection = DriverManager.getConnection("jdbc:h2:mem:testeDb", "sa", "");
        connection.createStatement().execute(CREATE_TABLE_SQL);
    }

    @Test
    void insertUserTest() throws SQLException {
        var INSERT_USER_SQL = "INSERT INTO users (id, name) VALUES (1, 'Bento')";
        connection.createStatement().execute(INSERT_USER_SQL);
    }

    @Test
    // @Disabled
    void doesUserExists() throws SQLException {
        var result = connection
                .createStatement()
                .executeQuery("SELECT * FROM users WHERE id = 1");
        Assertions.assertTrue(result.next());
    }

    @AfterAll
    static void closeDatabase() throws SQLException {
        connection.close();
    }

}
