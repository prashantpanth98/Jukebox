package Jukbx.Model;

public class songs {
    private int songid;
    private String songname;
    private String artistname;
    private String songgenre;
    private String album;
    private String songduration;

    public songs() {
    }
    public songs(int songid, String songname, String artistname, String songgenre, String album, String songduration) {
        this.songid = songid;
        this.songname = songname;
        this.artistname = artistname;
        this.songgenre = songgenre;
        this.album = album;
        this.songduration = songduration;
    }
    public songs(String songname, String artistname, String songgenre, String album, String songduration) {
        this.songname = songname;
        this.artistname = artistname;
        this.songgenre = songgenre;
        this.album = album;
        this.songduration = songduration;
    }

    public int getSongid() { return songid; }
    public void setSongid(int songid) { this.songid = songid; }

    public String getSongname() { return songname; }
    public void setSongname(String songname) { this.songname = songname; }

    public String getArtistname() { return artistname; }
    public void setArtistname(String artistname) { this.artistname = artistname; }

    public String getSonggenre() { return songgenre; }
    public void setSonggenre(String songgenre) { this.songgenre = songgenre; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public String getSongduration() { return songduration; }
    public void setSongduration(String songduration) { this.songduration = songduration; }


    @Override
    public String toString() {
        return String.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s",songid,songname,artistname,songgenre,album,songduration);
    }
}
