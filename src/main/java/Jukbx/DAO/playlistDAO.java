package Jukbx.DAO;

import java.sql.*;
import java.util.Hashtable;

public class playlistDAO
{
    boolean createPlaylist(int playlistid,String playlistname) throws SQLException
    {
        boolean flag =false;
            PreparedStatement insertStatement=JukeboxConnection.getJukeboxConnection().
                    prepareStatement("insert into playlist(playlistid,playlistname) values(?,?);");
            insertStatement.setInt(1,playlistid);
            insertStatement.setString(2,playlistname);
            int result=insertStatement.executeUpdate();
            if(result==1) {
                flag=true;
            }
            return flag;
    }

    public Hashtable<String,Integer> viewAllPlaylist() throws SQLException
    {
        Hashtable<String,Integer> playlist=new Hashtable<>();
        Statement selectStatement=JukeboxConnection.getJukeboxConnection().createStatement();
        ResultSet rs=selectStatement.executeQuery("select * from playlist");
        if(rs.next()){
            playlist.put(rs.getString(2),rs.getInt(1));
        }
        return playlist;
    }
}
