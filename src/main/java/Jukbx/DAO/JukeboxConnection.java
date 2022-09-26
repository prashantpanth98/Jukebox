package Jukbx.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JukeboxConnection
{
    public static Connection getJukeboxConnection() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jukebox","root","pass1234");
        return con;
    }
}