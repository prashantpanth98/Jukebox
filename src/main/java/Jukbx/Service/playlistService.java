package Jukbx.Service;

import Jukbx.DAO.playlistDAO;

import java.sql.SQLException;
import java.util.Hashtable;

public class playlistService
{
    boolean addPlaylist(String playlistname, int playlistid, Hashtable<String,Integer> playlist){
        boolean playlistPresent=playlist.containsKey(playlistname);
        if(playlistPresent=false){
            playlist.put(playlistname,playlistid);
        }
        return playlistPresent;
    }

    Hashtable<String,Integer> getAllPlaylist() throws SQLException
    {
        playlistDAO playlistDAO=new playlistDAO();
        return playlistDAO.viewAllPlaylist();
    }
}
