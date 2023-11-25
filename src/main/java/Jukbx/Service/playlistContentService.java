package Jukbx.Service;

import Jukbx.DAO.playlistContentDAO;
import Jukbx.Model.songs;

import java.sql.SQLException;
import java.util.*;

public class playlistContentService {
    public boolean addSongToPlaylist(ArrayList<songs> songslist, Hashtable<String, Integer> playlist,String songname,String playlistname)
            throws JukeboxException,SQLException {
        boolean temp = false;
        int playListId=0;
        if(!playlist.containsKey(playlistname)) { throw new JukeboxException("Enter Correct Playlist Name"); }
        if (songslist.isEmpty() || playlist.isEmpty() || songname==null || playlistname == null
                || songname.isEmpty() || playlistname.isEmpty() )
        { throw new JukeboxException("Please enter all required values"); }
        else
        {
            int playlistid = playlist.get(playlistname);
            int songid = 0;
                for (songs Song : songslist)
                {
                    if (Song.getSongname().equalsIgnoreCase(songname))
                    {
                    songid = Song.getSongid();
                    temp = true;
                    break;
                    }
                   // else if(Song.getSongname().equalsIgnoreCase(songname)==false) { throw new JukeboxException("Enter Correct Song Name"); }
                    if(playlistname.contains(songname)) { throw new JukeboxException("Song already present in Playlist"); }
                }
             if (playlistid == 0) { throw new JukeboxException("Playlist is not present"); }
            else if (songid == 0) { throw new JukeboxException("Song is not present"); }
            else {
                playlistContentDAO playlistcontentDao = new playlistContentDAO();
                temp = playlistcontentDao.addSongstoPlaylist(playlistid, songid);
            }
        }
        return temp;
    }

    public boolean addAlbumToPlaylist(ArrayList<songs> songslist, Hashtable<String, Integer> playlist, String album, String playlistname)
            throws JukeboxException, SQLException {
        boolean temp = false;
        boolean isAlbumPresent = false;
        ArrayList<Integer> songidList = null;
        if (songslist.isEmpty() || playlist.isEmpty() || album == null || playlistname == null || album.isEmpty() || playlistname.isEmpty())
        { throw new JukeboxException("Please provide all values"); }
        if(!playlist.containsKey(playlistname)) { throw new JukeboxException("Playlist doesn't Exist"); }
        for(songs Song:songslist){
            if(Song.getAlbum().equalsIgnoreCase(album)){
                isAlbumPresent=true;
                break;
            }
        }
        if(!isAlbumPresent) { throw new JukeboxException("Album is not present"); }
        else {
            int playlistid = playlist.get(playlistname);
            ArrayList<Integer> allSongidList = playlistContentDAO.viewSongsinPlaylist(playlistid);
            songidList = new ArrayList<>();
            for (songs Song : songslist) {
                if (Song.getAlbum().equalsIgnoreCase(album)) {
                    if (!allSongidList.contains(Song.getSongid())) {
                        int songid = Song.getSongid();
                        songidList.add(songid);
                    }
                }
                if (playlistid == 0) { throw new JukeboxException("Empty Playlist!"); }
            }
                playlistContentDAO playlistcontentDao = new playlistContentDAO();
                for(int songid:songidList)
                 {
                    temp = playlistcontentDao.addSongstoPlaylist(playlistid, songid);
                }
            }
        return temp;
    }


    public ArrayList<songs> playlistContent(String playlistname, Hashtable<String, Integer> playlist, ArrayList<songs> songslist)
            throws SQLException, JukeboxException, NullPointerException
    {
        ArrayList<songs> songList = null;
        ArrayList<Integer> songIdList = null;
        if (playlistname == null || playlist.isEmpty() || songslist.isEmpty() || playlistname.isEmpty()) {
            throw new JukeboxException("Please provide all the details");
        }
        int playlistid=0;
        if(!playlist.containsKey(playlistname)){
            throw new JukeboxException("PlayList is Not Present in JukeBox");
        }
        else {
            playlistid = playlist.get(playlistname);
            if (playlistid == 0) { throw new JukeboxException("Playlist Id can not ne zero!"); }
            else {
                playlistContentDAO playlistcontentDao = new playlistContentDAO();
                songIdList = new ArrayList<>();
                songIdList = playlistcontentDao.viewSongsinPlaylist(playlistid);
            }
            if(songIdList.isEmpty() == false)
                {
                    songList = new ArrayList<>();
                    for (int Id : songIdList) {
                        for (songs Song : songslist) {
                            if (Song.getSongid() == Id) {
                                songList.add(Song);
                            }
                        }
                    }
                }
                else { throw new JukeboxException("Playlist is Empty!"); }
            }
        return songList;
    }
}
