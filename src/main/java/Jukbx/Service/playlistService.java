package Jukbx.Service;

import Jukbx.DAO.playlistDAO;

import java.sql.SQLException;
import java.util.Hashtable;

public class playlistService
{
    public boolean addPlaylist(String playlistname, Hashtable<String,Integer> playlist) throws JukeboxException,SQLException {
        boolean result=false;
        if (playlistname==null || playlist.isEmpty() || playlistname.isEmpty() || playlist==null){
            throw new JukeboxException("Please provide proper details");
        }
        else {
            boolean playlistPresent = playlist.containsKey(playlistname);
            if (playlistPresent == false) {
                playlistDAO playlistDao=new playlistDAO();
                playlistDao.createPlaylist(playlistname);
                result=true;
            }
        }
        return result;
    }

    public Hashtable<String,Integer> getAllPlaylist() throws SQLException
    {
        playlistDAO playlistDao=new playlistDAO();
        return playlistDao.viewAllPlaylist();
    }
}
