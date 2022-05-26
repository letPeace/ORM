package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector{

    private String filePath;
    private Connection connection;

    protected Connector(){
        setFilePath("D:\\_Mehi\\6sem\\java\\lab4\\src\\main\\resources\\reactors.accdb");
    }

    //

    protected String getFilePath() {
        return filePath;
    }

    protected void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

    //

    protected void connect() throws SQLException{
        setConnection(DriverManager.getConnection("jdbc:ucanaccess://"+getFilePath()));
    }

    protected void disconnect() throws SQLException{
        getConnection().close();
    }

}
