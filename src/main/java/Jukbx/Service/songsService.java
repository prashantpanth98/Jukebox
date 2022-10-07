package Jukbx.Service;

import Jukbx.DAO.songsDAO;
import Jukbx.Model.songs;

import java.sql.SQLException;
import java.util.ArrayList;

public class songsService
{
    private boolean checkSong(String songname, ArrayList<songs> songslist) throws SQLException,JukeboxException
    {
        boolean result=false;
        if(songslist!=null && songname!=null) {
            for (songs Song : songslist) {
                if (Song.getSongname().equalsIgnoreCase(songname.trim())) {
                    result = true;
                }
            }
        }
        else { throw new JukeboxException("Song can not be added"); }
        return result;
    }

    public boolean addSong(songs song,ArrayList<songs> songslist) throws SQLException,JukeboxException
    {
        boolean result=false;

        if(!checkSong(song.getSongname(),songslist))
        {
            songsDAO songsDao=new songsDAO();
            songsDao.insertSongs(song);
            result=true;
        }
       else { throw new JukeboxException("Song can not be added"); }
        return result;
    }

    public ArrayList<songs> getAllSongs() throws SQLException{
        songsDAO songsDao=new songsDAO();
        return songsDao.viewAllSongs();
    }

    public void displaySongs(ArrayList<songs> songslist)
    {
        System.out.format("%7s\t%15s\t%20s\t%20s\t%20s\t%20s","songid","songname","artistname","songgenre","album","songduration");
        for(songs Song:songslist){
            System.out.println(Song);
        }
    }

    public songs getSongbyName(ArrayList<songs> songslist, String songname)
    {
        songs selectSong=null;
        for(songs Song:songslist)
        {
            if((Song.getSongname().equalsIgnoreCase(songname)))
            {
               selectSong=Song;
               break;
            }
        }
        return selectSong;
    }
    public ArrayList<songs> getSongbyGenre(String songgenre,ArrayList<songs> songslist) throws JukeboxException {
        ArrayList<songs> genrelist=null;
        if(songslist.isEmpty() && songgenre ==null) {
            throw new JukeboxException("Song list is empty or Genre not present");
        }
        else{
            genrelist = new ArrayList<>();
            for (songs Song : songslist)
            {
                if(Song.getSonggenre().equalsIgnoreCase((songgenre))){
                    genrelist.add(Song);
                }
            }
        }
        return genrelist;
    }

    public ArrayList<songs> getSongbyArtist(String artistname,ArrayList<songs> songslist) throws JukeboxException
    {
        ArrayList<songs> artistlist=null;
        if(songslist.isEmpty() && artistname==null)
        {
            throw new JukeboxException("Artist list is empty or Artist not present");
        }
        else{
            artistlist=new ArrayList<>();
            for(songs Song:songslist){
                if(Song.getArtistname().contains(artistname)){
                    artistlist.add(Song);
                }
            }
        }
        return artistlist;
    }

    public ArrayList<songs> getSongbyAlbum(ArrayList<songs> songslist, String album) throws JukeboxException
    {
        ArrayList<songs> albumList=null;
        if(songslist.isEmpty() && album==null)
        {
            throw new JukeboxException("Songs list is empty or Album not present");
        } else{
            albumList = new ArrayList<>();
            for (songs Song : songslist) {
                if (Song.getAlbum().equalsIgnoreCase(album)) {
                    albumList.add(Song);
                }
            }
        }
        return albumList;
    }
}
