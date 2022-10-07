package Jukbx.DAO;

import java.sql.*;
import java.util.ArrayList;

public class playlistContentDAO
{
    public boolean addSongstoPlaylist(int playlistid,int songid) throws SQLException
    {
        boolean flag=false;
        PreparedStatement insertStatement=JukeboxConnection.getJukeboxConnection().
                prepareStatement("insert into  playlistcontent(playlistid,songid) values(?,?);");
        insertStatement.setInt(1,playlistid);
        insertStatement.setInt(2,songid);
        int result=insertStatement.executeUpdate();
        if(result==1){
            flag=true;
        }
        return flag;
    }
    public static ArrayList<Integer> viewSongsinPlaylist(int playlistid) throws SQLException
    {
        ArrayList<Integer> playlist=new ArrayList<>();
        PreparedStatement selectStatement=JukeboxConnection.getJukeboxConnection().
                prepareStatement("select * from playlistcontent where playlistid = ?;");
        selectStatement.setInt(1,playlistid);
        ResultSet rs=selectStatement.executeQuery();
        while(rs.next())
        {
            playlist.add(rs.getInt(2));
        }
        return playlist;
    }
}


// System.out.println("Flag= "+flag+" , Result ="+result);
//selectStatement.setInt(2,songid);
