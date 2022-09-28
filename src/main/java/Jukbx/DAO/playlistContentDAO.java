package Jukbx.DAO;

import java.sql.*;
import java.util.ArrayList;

public class playlistContentDAO
{
    playlistContentDAO playlistcontentDAO=new playlistContentDAO();

    public boolean addSongstoPlaylist(int playlistid,int songid) throws SQLException
    {
        boolean flag=false;
        PreparedStatement insertStatement=JukeboxConnection.getJukeboxConnection().
                prepareStatement("insert into  playlistcontent values(?,?);");
        insertStatement.setInt(1,playlistid);
        insertStatement.setInt(1,songid);
        int result=insertStatement.executeUpdate();
        if(result==1){
            flag=true;
        }
        return flag;
    }

    public ArrayList<Integer> viewSongsinPlaylist(int playlistid,int songid) throws SQLException
    {
        ArrayList<Integer> playlist=new ArrayList<>();
        PreparedStatement selectStatement=JukeboxConnection.getJukeboxConnection().
                prepareStatement("select * from playlistcontent playlistid = ?;");
        selectStatement.setInt(1,playlistid);
        selectStatement.setInt(2,songid);
        ResultSet rs=selectStatement.executeQuery();
        while(rs.next()){
            playlist.add(playlistid,songid);
        }
        return playlist;
    }
}
