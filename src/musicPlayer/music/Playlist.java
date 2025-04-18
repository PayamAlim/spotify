package musicPlayer.music;

import musicPlayer.exceptions.*;
import musicPlayer.user.*;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

    private String title;

    private ArrayList<Music> playlist = new ArrayList<>();

    private User owner;

    //Getters
    public String getTitle() {
        return title;
    }

    //Setters
    public void editTitle(String newTitle, String inputPassword) {
        validatePassword(inputPassword);

        this.title = newTitle;
        //System.out.println("Playlist title updated to: " + newTitle);
    }

    public void addMusic(Music music, String inputPassword) {
        validatePassword(inputPassword);

        if (playlist.contains(music))
            throw new InvalidOperationException("Music already exists in the playlist!");

        playlist.add(music);
        //System.out.println("Music added to playlist: " + music.title);
    }

    public void removeMusic(Music music, String inputPassword) {
        validatePassword(inputPassword);

        if (!playlist.contains(music))
            throw new InvalidOperationException("Music does not exist in the playlist.");

        playlist.remove(music);
        //System.out.println("Music removed from playlist: " + music.getTitle());
    }

    //Constructor
    public Playlist(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }

    //Methods
    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> results = new ArrayList<>();

        for (Music music : playlist)
            if (music.getTitle().equals(title))
                results.add(music);

        return results.isEmpty() ? null : results;
    }

    public Music searchInPlaylist(String title, String singerName) {
        for (Music music : playlist)
            if (music.getTitle().equals(title) && music.getSinger().getUsername().equals(singerName))
                return music;
        return null;
    }

    public void playPlaylist() {
        for (Music music : playlist)
            music.play();
    }

    public void shufflePlaylist() {
        Collections.shuffle(playlist);

        playPlaylist();
    }

    //Validating (Clean Code)
    private void validatePassword(String inputPassword) {
        if (!owner.getPassword().equals(inputPassword))
            throw new InvalidOperationException("Invalid password!");
    }
}