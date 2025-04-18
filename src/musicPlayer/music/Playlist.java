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

    public void playPlaylist() { //playlist getter
        for (Music music : playlist)
            music.play();
    }

    //Setters
    public void editTitle(String newTitle, String inputPassword) { //title setter
        validatePassword(inputPassword);

        this.title = newTitle;
        System.out.println("Playlist title updated to: " + newTitle);
    }

    public void addMusic(Music music, String inputPassword) { //playlist setter
        validatePassword(inputPassword);

        if (music == null)
            throw new InvalidOperationException("Cannot add null music to playlist");

        if (playlist.contains(music))
            throw new InvalidOperationException("Music already exists in the playlist!");

        playlist.add(music);
        System.out.println("Music added to playlist: " + music.getTitle());
    }

    public void removeMusic(Music music, String inputPassword) { //playlist setter
        validatePassword(inputPassword);

        if (!playlist.contains(music))
            throw new InvalidOperationException("Music does not exist in the playlist.");

        playlist.remove(music);
        System.out.println("Music removed from playlist: " + music.getTitle());
    }

    //Constructor
    public Playlist(String title, User owner) {
        if (owner == null)
            throw new InvalidOperationException("Cannot create playlist for null person");

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