package musicPlayer.user.userBehavior;

import musicPlayer.exceptions.InvalidOperationException;
import musicPlayer.music.Music;
import musicPlayer.music.Playlist;
import musicPlayer.user.*;

public class PremiumBehavior implements UserBehavior{
    private int month;

    //Getters
    public int getMonth() {
        return month;
    }

    //Setters
    public void setMonth(int month) {
        if (month <= 0)
            throw new InvalidOperationException("Premium time must be positive!");

        this.month = month;
    }

    //Constructor
    public PremiumBehavior(int month) {
        this.setMonth(month);
    }

    @Override
    public void buyPremium(User owner, int month) {
        if (owner == null)
            throw new InvalidOperationException("Cannot buy premium for null user");

        this.month += month;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        if (owner == null)
            throw new InvalidOperationException("Cannot create play list for null user");

        Playlist newPlaylist = new Playlist(title, owner);

        owner.addPlaylist(newPlaylist);
    }

    @Override
    public void playMusic(Music music) {
        if (music == null)
            throw new InvalidOperationException("Cannot play null music!");
        music.play();
    }
}
