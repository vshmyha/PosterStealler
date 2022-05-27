package com.lerkin.poststealler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnector {

    private static final String USER = "userLogin";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/NameOfDataBase";

    public static List<String> getAnimeNames() throws SQLException, ClassNotFoundException {

        Class.forName(DRIVER);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> animeNames = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT jap_name FROM animes");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String anime = resultSet.getString("jap_name");
                animeNames.add(anime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return animeNames;
    }
}
