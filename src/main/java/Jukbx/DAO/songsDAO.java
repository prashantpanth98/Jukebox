package Jukbx.DAO;

import Jukbx.Model.songs;
import java.sql.*;
import java.util.ArrayList;

public class songsDAO
{
    public int insertSongs(songs song) throws SQLException
    {
        PreparedStatement insertStatement=JukeboxConnection.getJukeboxConnection()
                .prepareStatement("insert into songs(songname,artistname,songgenre,album,songduration) values(?,?,?,?,?);");
        insertStatement.setString(1,song.getSongname());
        insertStatement.setString(2,song.getArtistname());
        insertStatement.setString(3,song.getSonggenre());
        insertStatement.setString(4,song.getAlbum());
        insertStatement.setString(5,song.getSongduration());
        int result=insertStatement.executeUpdate();
        return result;
    }
    public ArrayList<songs> viewAllSongs() throws SQLException
    {
        ArrayList<songs> songslist=null;
        Statement selectStatement= JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet rs=selectStatement.executeQuery("select * from songs");
        if(rs.isBeforeFirst()) {
            songslist= new ArrayList<>();
            while (rs.next()) {
                boolean rs1 = songslist.add(new songs(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        }
        return songslist;
    }
}
