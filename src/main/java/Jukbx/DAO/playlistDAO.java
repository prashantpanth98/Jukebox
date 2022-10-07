package Jukbx.DAO;

import Jukbx.Service.JukeboxException;

import java.sql.*;
import java.util.Hashtable;

public class playlistDAO
{
    public boolean createPlaylist(String playlistname) throws SQLException
    {
        boolean flag =false;
            PreparedStatement insertStatement=JukeboxConnection.getJukeboxConnection().
                    prepareStatement("insert into playlist(playlistname) values(?);");
                insertStatement.setString(1, playlistname);
                int result = insertStatement.executeUpdate();
                if (result == 1) {
                    flag = true;
                }
        return flag;
    }

    public Hashtable<String,Integer> viewAllPlaylist() throws SQLException
    {
        Hashtable<String,Integer> playlist=new Hashtable<>();
        Statement selectStatement=JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet rs=selectStatement.executeQuery("select * from playlist");
        while(rs.next()){
            playlist.put(rs.getString(2),rs.getInt(1));
        }
        return playlist;
    }
}
