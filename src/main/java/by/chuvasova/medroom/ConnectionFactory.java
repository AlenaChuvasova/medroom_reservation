package by.chuvasova.medroom;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Properties properties = new Properties();

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(fileName);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getConnection() throws IOException, ClassNotFoundException {
        properties = readPropertiesFile("application.properties");
        Connection connection = null;
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String driver = properties.getProperty("jdbc.driverClassName");
        Class.forName(driver);
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
        return connection;
    }
}
