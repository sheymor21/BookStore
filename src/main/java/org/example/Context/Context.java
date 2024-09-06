package org.example.Context;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Context {
    private static Context instance;
    private final Connection connection;

    public Context() {
        connection = createContext();
    }

    public Connection getConnection() {
        return connection;
    }

    public static Context getInstance() {
        if (instance == null) {
            return new Context();
        }
        return instance;
    }

    public void createDatabase() {
        if (connection != null) {
            try {
                Map<String, String> queries = new LinkedHashMap<>();
                queries.put("users", """
                        Create Table users(
                        user_id varchar(50) primary key,
                        dni varchar(20) not null,
                        first_name varchar(20) not null,
                        last_name varchar(20) not null,
                        email varchar(20) not null,
                        created_at date not null
                        );
                        """);

                queries.put("books", """
                        Create Table books(
                        book_id varchar(50) primary key,
                        title varchar(20) not null,
                        author varchar(20) not null,
                        price double precision not null,
                        release_year int not null,
                        created_at date not null
                         )
                        """);

                queries.put("orders", """
                        Create Table orders(
                        order_id varchar(50) primary key,
                        book_id varchar(50) not null,
                        user_id varchar(50) not null,
                        created_at date not null,
                        foreign key (book_id) references books(book_id),
                        foreign key (user_id) references users(user_id)
                        )
                        """);
                Statement statement = connection.createStatement();
                for (String query : queries.keySet()) {
                    var result = doesTableExist(query.toLowerCase(Locale.ROOT));
                    if (!result) {

                        statement.executeUpdate(queries.get(query));
                    }
                }
                statement.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not database connection");
        }

    }

    private boolean doesTableExist(String tableName) {
        try {
            Statement statement = connection.createStatement();
            DatabaseMetaData metaData = statement.getConnection().getMetaData();
            try (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"})) {
                return resultSet.next();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private Connection createContext() {

        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "root", "password!1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
