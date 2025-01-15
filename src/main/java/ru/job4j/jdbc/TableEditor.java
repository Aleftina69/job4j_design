package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        executeUpdate(String.format("CREATE TABLE %s ();", tableName));
    }

    public void dropTable(String tableName) {
        executeUpdate(String.format("DROP TABLE %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        executeUpdate(String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        executeUpdate(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeUpdate(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("SELECT * FROM %s LIMIT 1", tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (TableEditor editor = new TableEditor(config)) {
            String tableName = "test_table";

            editor.createTable(tableName);
            System.out.println("Таблица создана:");
            System.out.println(editor.getTableScheme(tableName));

            editor.addColumn(tableName, "id", "SERIAL PRIMARY KEY");
            System.out.println("Столбец 'id' добавлен:");
            System.out.println(editor.getTableScheme(tableName));

            editor.addColumn(tableName, "name", "VARCHAR(255)");
            System.out.println("Столбец 'name' добавлен:");
            System.out.println(editor.getTableScheme(tableName));

            editor.renameColumn(tableName, "name", "full_name");
            System.out.println("Столбец 'name' переименован в 'full_name':");
            System.out.println(editor.getTableScheme(tableName));

            editor.dropColumn(tableName, "full_name");
            System.out.println("Столбец 'full_name' удален:");
            System.out.println(editor.getTableScheme(tableName));

            editor.dropTable(tableName);
            System.out.println("Таблица удалена:");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}