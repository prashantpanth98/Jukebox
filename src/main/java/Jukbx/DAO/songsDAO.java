package Jukbx.DAO;

import Jukbx.Model.songs;
import java.sql.*;
import java.util.ArrayList;

public class songsDAO
{
    public int insertSongs(songs song) throws SQLException
    {
        PreparedStatement insertStatement=JukeboxConnection.getJukeboxConnection()
                .prepareStatement("insert into songs(songid,songname,artistname,songgenre,album,songduration) values(?,?,?,?,?,?);");
        insertStatement.setInt(1,song.getSongid());
        insertStatement.setString(2,song.getSongname());
        insertStatement.setString(3,song.getArtistname());
        insertStatement.setString(3,song.getSonggenre());
        insertStatement.setString(3,song.getAlbum());
        insertStatement.setString(3,song.getSongduration());
        int result=insertStatement.executeUpdate();
        return result;
    }

    public ArrayList<songs> getAllSongs() throws SQLException
    {
        ArrayList<songs> songsList=new ArrayList<>();
        Statement selectStatement= JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet rs=selectStatement.executeQuery("select * from songs");
        if(rs.next()){
            songsList.add(new songs(rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(6)));
        }
        return songsList;
    }
}
