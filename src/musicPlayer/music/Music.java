package musicPlayer.music;

import musicPlayer.exceptions.InvalidOperationException;
import musicPlayer.user.*;

import java.util.ArrayList;

public class Music {
    private static ArrayList<Music> allMusics = new ArrayList<>();

    private String title;

    private User singer;

    private int numberOfStream;

    //Getters
    public static ArrayList<Music> getAllMusics() {
        return allMusics;
    }

    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    //Constructor
    public Music(String title, User singer) {
        for (Music music: allMusics)
            if (music.title.equals(title) && music.singer.equals(singer))
                throw new InvalidOperationException("Music already exist");

        this.title = title;
        this.singer = singer;
        this.numberOfStream = 0;

        allMusics.add(this);
    }

    //Methods
    public void play() {
        numberOfStream ++;
        System.out.println("Now playing: " + title + "\n" +
                "by " + singer.getUsername());
    }

    public static ArrayList<Music> search(String title) {
        ArrayList<Music> results = new ArrayList<>();
        for (Music music : allMusics)
            if (music.title.equals(title))
                results.add(music);
        return results.isEmpty() ? null: results;
    }

    public static Music search(String title, String singerName) {
        for (Music music : allMusics)
            if (music.title.equals(title) && music.singer.getUsername().equals(singerName))
                return music;
        return null;
    }
}