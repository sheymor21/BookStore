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
                        Id varchar(50) primary key,
                        Dni varchar(20) not null,
                        FirstName varchar(20) not null,
                        LastName varchar(20) not null,
                        Email varchar(20) not null,
                        CreatedAt date not null
                        );
                        """);

                queries.put("books", """
                        Create Table books(
                        Id varchar(50) primary key,
                        Title varchar(20) not null,
                        Author varchar(20) not null,
                        ReleaseYear int not null,
                        CreatedAt date not null
                         )
                        """);

                queries.put("orders", """
                        Create Table orders(
                        Id varchar(50) primary key,
                        bookId varchar(50) not null,
                        userId varchar(50) not null,
                        CreatedAt date not null,
                        foreign key (bookId) references books(Id),
                        foreign key (userId) references users(Id)
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
