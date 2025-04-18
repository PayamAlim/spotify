package musicPlayer.user.userBehavior;

import musicPlayer.exceptions.InvalidOperationException;
import musicPlayer.music.Music;
import musicPlayer.user.*;

public class RegularBehavior implements UserBehavior {
    private int playingLimit;

    //Getters
    public int getPlayingLimit() {
        return playingLimit;
    }

    //Setters
    public void setPlayingLimit(int playingLimit) {
        this.playingLimit = playingLimit;
    }

    //Construuctors
    public RegularBehavior() {
        this.setPlayingLimit(5);
    }

    @Override
    public void createPlaylist(String title, User owner) {
        throw new InvalidOperationException("Buy Premium to create playlist");
    }

    @Override
    public void playMusic(Music music) {
        if (playingLimit == 0)
            throw new InvalidOperationException("Your free playing limit has reached");

        playingLimit --;
    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
    }
}