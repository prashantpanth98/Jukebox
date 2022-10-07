package Jukbx;

import Jukbx.DAO.JukeboxConnection;
import Jukbx.DAO.songsDAO;
import Jukbx.Model.songs;
import Jukbx.Service.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

public class MainJukebox
{
    public static void main(String[] args)
    {
        songsService songServc = new songsService();
        playlistService playServc = new playlistService();
        playlistContentService playContServc = new playlistContentService();

        ArrayList<songs> songslist = new ArrayList<>();
        Hashtable<String, Integer> playlist = new Hashtable<>();
        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("Welcome to Jukebox!\n");
                songslist = songServc.getAllSongs();
                songServc.displaySongs(songslist);
                playlist = playServc.getAllPlaylist();
            String pass;
            do
            {
                pass=null;
                int choice=0;
                    System.out.println("\nChoose a Option\n1. Song\n2. Playlist\n3. Player\n4. Exit");
                     choice = sc.nextInt();
                switch (choice)
                {

                    case 1: String count1="Yes";
                        do {
                            System.out.println("Choose a Option\n1. Add a Song\n2. Search Song by Name\n3. Search Song by Genre" +
                                    "\n4. Search Song by Artist\n5. Search Song by Album\n6. Main Menu\n7. Exit");
                            int option = sc.nextInt();

                            switch (option) {
                                case 1:
                                    songsDAO songsDao = new songsDAO();

                                    sc.nextLine();
                                    System.out.println("Enter the Song Name");
                                    String songname = sc.nextLine();
                                    System.out.println("Enter the Artist Name");
                                    String artistname = sc.nextLine();
                                    System.out.println("Enter the Genre");
                                    String songgenre = sc.nextLine();
                                    System.out.println("Enter the Name of Album");
                                    String album = sc.nextLine();
                                    System.out.println("Enter the total duration of Song");
                                    String songduration = sc.nextLine();

                                    songs Song = new songs(songname, artistname, songgenre, album, songduration);
                                    try {
                                        boolean flag1 = songServc.addSong(Song, songslist);
                                        System.out.println(songServc.getAllSongs());
                                    } catch (JukeboxException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter the Name of a song");
                                    sc.nextLine();
                                    songname = sc.nextLine();
                                    Song = songServc.getSongbyName(songslist, songname);
                                    try {
                                        if (Song != null) {
                                            System.out.println(Song);
                                        } else {
                                            throw new JukeboxException("Song not present");
                                        }
                                    } catch (JukeboxException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter any Genre");
                                    sc.nextLine();
                                    songgenre = sc.nextLine();
                                    ArrayList genrelist = null;
                                    try {
                                        genrelist = songServc.getSongbyGenre(songgenre, songslist);
                                        if (genrelist.isEmpty() == false) {
                                            songServc.displaySongs(genrelist);
                                        } else {
                                            throw new JukeboxException("Genre not present");
                                        }
                                    } catch (JukeboxException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 4:
                                    System.out.println("Enter the name of Artist");
                                    sc.nextLine();
                                    artistname = sc.nextLine();
                                    ArrayList artistlist = null;
                                    try {
                                        artistlist = songServc.getSongbyArtist(artistname, songslist);
                                        if (artistlist.isEmpty() == false) {
                                            songServc.displaySongs(artistlist);
                                        } else {
                                            throw new JukeboxException("Artist not present");
                                        }
                                    } catch (JukeboxException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    System.out.println("Enter the name of Album");
                                    sc.nextLine();
                                    album = sc.nextLine();
                                    ArrayList albumlist = new ArrayList<>();
                                    try {
                                        albumlist = songServc.getSongbyAlbum(songslist, album);
                                        if (albumlist.isEmpty() == false) {
                                            songServc.displaySongs(albumlist);
                                        } else {
                                            throw new JukeboxException("Artist not present");
                                        }
                                    } catch (JukeboxException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 6:
                                    System.out.println("Returning to Main Menu!");
                                    pass = "Y";
                                    count1 = "";
                                    break;
                                case 7:
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Please choose correct Option!");
                            }
                            if (option != 6) {
                                System.out.println("Do you want to continue : Press 'Yes'");
                                count1 = sc.nextLine();
                            }

                        }while(count1.equalsIgnoreCase("Yes"));
                        break;
                    case 2:
                        String count2="Yes";
                        do {
                        System.out.println("Choose a Option\n1. Show all Playlists\n2. Create Playlist\n3. Add Song to Playlist\n" +
                                "4. Add Album to Playlist\n5. Display Playlist content\n6. Main Menu\n7. Exit");
                        int opt = sc.nextInt();

                        switch (opt) {
                            case 1:
                                sc.nextLine();
                                Set<String> playlistnameSet = null;
                                playlistnameSet = playlist.keySet();
                                    System.out.println("All Playlists below :-");
                                    if (!playlistnameSet.isEmpty() || playlistnameSet != null) {
                                        for (String key : playlistnameSet) {
                                            System.out.println(key);
                                        }
                                    }
                                break;
                            case 2:sc.nextLine();
                                System.out.println("Enter a Playlist name to create");
                                String playlistname = sc.nextLine();
                                try {
                                    if (playServc.addPlaylist(playlistname, playlist) == true) {
                                        if(!playlistname.isEmpty() || playlistname!=null) {
                                            System.out.println(playServc.getAllPlaylist());
                                        } else throw new JukeboxException("Playlist name can not ne empty or null");}
                                } catch (JukeboxException e) { System.out.println(e.getMessage()); }
                                break;
                            case 3:
                                sc.nextLine();
                                System.out.println("Enter existing Song Name to add in Playlist");
                                String songname = sc.nextLine();
                                System.out.println("Enter existing Playlist name");
                                playlistname = sc.nextLine();
                                try {
                                    boolean songinPlaylist = playContServc.addSongToPlaylist(songslist, playlist, songname, playlistname);
                                    if(songinPlaylist == true) {
                                        System.out.println(songname + " added to the Playlist i.e," + playlistname);
                                    }
                                    else { throw new JukeboxException("Song is already added"); }
                                }catch(JukeboxException e) { System.out.println(e.getMessage()); }
                                break;
                            case 4:
                                sc.nextLine();
                                System.out.println("Enter existing Album Name to add in Playlist");
                                String album = sc.nextLine();
                                System.out.println("Enter existing Playlist name");
                                playlistname = sc.nextLine();
                                try{
                                boolean albuminPlaylist = playContServc.addAlbumToPlaylist(songslist, playlist, album, playlistname);
                                if (albuminPlaylist == true) {
                                    System.out.println("'"+album+"'"+ " added to the Playlist i.e," + playlistname);
                                }
                                else { throw new JukeboxException("Album is already added"); }
                                } catch (JukeboxException e) { System.out.println(e.getMessage()); }
                                break;
                            case 5:
                                sc.nextLine();
                                System.out.println("Enter the Playlist Name for viewing it's content");
                                playlistname = sc.nextLine();
                                try {
                                    ArrayList<songs> playlistcontent = playContServc.playlistContent(playlistname, playlist, songslist);
                                    songServc.displaySongs(playlistcontent);
                                }catch (JukeboxException e){
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 6:
                                System.out.println("Returning to Main");
                                pass="Y";
                                count2="";
                                break;
                            case 7:
                                System.exit(0);
                                break;
                            default: System.out.println("Enter correct Choice!");
                        }
                        if(opt!=6)
                        {
                            System.out.println("Do you want to continue : Press 'Yes'");
                            count2 = sc.nextLine();
                        }
                        }while(count2.equalsIgnoreCase("Yes"));
                        break;
                    case 3: String count3="Yes";
                        do {
                            System.out.println("1) Play Song\n2) Main Menu\n3) Exit");
                            sc.nextLine();
                            int option2 = sc.nextInt();
                            switch (option2) {
                                case 1:
                                    System.out.println("Enter the Playlist Name to play");
                                    sc.nextLine();
                                    String playlistname = sc.nextLine();
                                    playerService plyrServc = new playerService();
                                    ArrayList<Integer> songidtoplay = new ArrayList<>();
                                    try {
                                        ArrayList<songs> songsToPlay = playContServc.playlistContent(playlistname, playlist, songslist);
                                        Iterator<songs> songsIterator = songsToPlay.iterator();
                                        while (songsIterator.hasNext()) {
                                            songidtoplay.add(songsIterator.next().getSongid());
                                        }
                                    }catch (ArrayIndexOutOfBoundsException e) { System.out.println(e.getMessage()); }
                                    catch (JukeboxException e) { System.out.println(e.getMessage()); }
                                    int idToPlay = 0;
                                    int optn=0;
                                        do {
                                            plyrServc.playSong(songidtoplay.get(idToPlay));
                                                while (true) {
                                                   // try {
                                                    System.out.println("1. Pause\n2. Resume\n3. Restart\n4. Stop\n5. Next\n6. Previous");
                                                    optn = sc.nextInt();
                                                    plyrServc.chooseOperation(optn);
                                                    if (optn == 4) {
                                                        break;
                                                    }
                                                    if (optn == 5) {
                                                        idToPlay++;
                                                        break;
                                                    }
                                                    if (optn == 6) {
                                                        --idToPlay;
                                                        break;
                                                    }
                                                    if (optn > 6) {
                                                        System.out.println("Incorrect Input");
                                                    }
                                                }
                                                sc.close();
//                                                catch(IndexOutOfBoundsException e){
//                                                        System.out.println("No More Previous Songs or Further Songs Available!");
//                                                    }
//                                                }
                                        }while(optn == 5 || optn == 6);
                                    break;
                                case 2:
                                    System.out.println("Returning to Main");
                                    pass = "Y";
                                    count3="";
                                    break;
                                case 3: System.exit(0);
                                break;
                                default: System.out.println("Provide Correct Input");
                            }
                            if(option2!=2) {
                                System.out.println("Do you want to continue : Press 'Yes'");
                                count3 = sc.nextLine();
                            }
                        }while(count3.equalsIgnoreCase("Yes"));
                        break;
                    case 4: System.exit(0); break;

                    default: System.out.println("Enter correct Choice!");
                }
                if(pass==null)
                {
                    System.out.println("Do you want to continue : Press 'Y'");
                    pass=sc.nextLine();
                }
            }while(pass.equalsIgnoreCase("Y"));
        }
//        catch (IndexOutOfBoundsException e){
//            System.out.println(e.getMessage());
//        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Data Already Exist");
        }
        catch (Exception e)
        { System.out.println(e.getMessage()); }
    }
}
