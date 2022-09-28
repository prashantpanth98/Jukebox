package Jukbx;

import Jukbx.DAO.JukeboxConnection;

import java.sql.SQLException;

public class mainC {
    public static void main(String[] args) {
        JukeboxConnection jc = new JukeboxConnection();
        try {
            jc.getJukeboxConnection();
            if(JukeboxConnection.getJukeboxConnection().isClosed()==false){
                System.out.println("Yes Connection");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}