package Jukbx.Service;

import Jukbx.DAO.songsDAO;
import Jukbx.Model.songs;

import java.sql.SQLException;
import java.util.ArrayList;

public class songsService
{
    songsDAO songDAO;

    public boolean addSong(songs song,ArrayList<songs> songsList) throws SQLException
    {
        boolean result=false;
        if(checkSong(song.getSongname(),songsList)==false)
        {
            songDAO.insertSongs(song);
        }
        return result;
    }

    private boolean checkSong(String songname, ArrayList<songs> songsList)
    {
        boolean result=false;
        for(songs Song:songsList){
            if(Song.getSongname().equalsIgnoreCase(songname)){
                result = true;
            }
        }
        return result;
    }

    public void displaySongs(ArrayList<songs> songsList)
    {
        for(songs Song:songsList){
            System.out.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s","songid","songname","artistname","songgenre","album","songduration");
            }
    }

    public songs getSongbyName(ArrayList<songs> songsList, String songname)
    {
        songs selectSong=null;
        for(songs Song:songsList){
            //if(songsList.contains(Song))
            if((Song.getSongname().contains(songname)))
            {
               selectSong=Song;
            }
        }
        return selectSong;
    }

    public ArrayList<songs> getSongbyAlbum(ArrayList<songs> songsList, String album){
        ArrayList<songs> albumList=null;
        if(albumList.isEmpty()==false && album !=null)
        {
            albumList = new ArrayList<>();
            for (songs Song : songsList) {
                if (Song.getAlbum().equalsIgnoreCase(album)) {
                    albumList.add(Song);
                }
            }
        }
        return albumList;
    }

    public ArrayList<songs> getSongbyArtist(String artistname,ArrayList<songs> songslist){
        ArrayList<songs> artistList=null;
        if(artistList.isEmpty()==false && artistname !=null)
        {
            artistList=new ArrayList<>();
            for(songs Song:songslist){
                if(Song.getArtistname().contains(artistname)){
                    artistList.add(Song);
                }
            }
        }
        return artistList;
    }

    public ArrayList<songs> getSongbyGenre(String songgenre,ArrayList<songs> songsList)
    {
        ArrayList<songs> genreList=null;
        if(genreList.isEmpty()==false && songgenre !=null)
        {
            genreList = new ArrayList<>();
            for (songs Song : songsList)
            {
                if(Song.getSonggenre().equalsIgnoreCase((songgenre))){
                    genreList.add(Song);
                }
            }
        }
        return genreList;
    }
}
