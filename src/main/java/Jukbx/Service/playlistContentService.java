package Jukbx.Service;

import Jukbx.DAO.playlistContentDAO;
import Jukbx.Model.songs;
import java.util.*;


public class playlistContentService {
    playlistContentDAO playlistcontentDAO = new playlistContentDAO();
    boolean addSongToPlaylist(ArrayList<songs> songlist, Hashtable<String, Integer> playlist, String songname, String playlistname)
            throws Exception {
        boolean temp = false;
        if (songname != null && playlistname != null) {
            int playlistid = playlist.get(playlistname);
            int songid = 0;
            for (songs Song : songlist) {
                if (Song.getSongname().equalsIgnoreCase(songname)) {
                    songid=Song.getSongid();
                    temp = true;
                    break;
                }
                if(songid==0)
                { throw new Exception("Song not present");}
                else if (playlistid == 0) {
                    throw new Exception("Playlist not present");
                } else {
                    playlistcontentDAO.addSongstoPlaylist(playlistid, songid);
                }
            }
        }
        return temp;
    }

    boolean addAlbumToPlaylist(ArrayList<songs> songlist, Hashtable<String, Integer> playlist, String album, String playlistname)
            throws Exception
    {
        boolean temp=false;
        if (album != null && playlistname != null) {
            int playlistid=playlist.get(playlistname);
            int songid=0;
            ArrayList<Integer> songidList=new ArrayList<>();
            for (songs Song : songlist) {
                if (songidList.contains(songid)==false)
                {
                    throw new Exception("Song not present");
                }
                else if (playlistid == 0) {
                    throw new Exception("Playlist not present");
                }
                else {
                    Iterator<String> iteratr=playlist.keySet().iterator();
                    if(iteratr.hasNext()) {
                        playlistcontentDAO.addSongstoPlaylist(playlistid, songid);
                        temp = true;
                    }
                }
            }
        }
        return temp;
    }

    ArrayList<songs> playlistContent(String playlistname,Hashtable<String,Integer> playlist,ArrayList<songs> songlist)
            throws Exception
    {
        ArrayList<songs> arrayList=null;
        int songid=0;
        if(playlistname!=null){
            int playlistid=playlist.get(playlistname);
            if(playlistid==0){
                throw new Exception("Playlist not present");
            }
            else{
                ArrayList<Integer> songidlist=playlistcontentDAO.viewSongsinPlaylist(playlistid,songid);
                for(songs Song : songlist){
                    if(Song.getSongid()==songid)
                    {
                        songidlist.add(songid);
                    }
                }
            }
        }
        return arrayList;
    }
}
