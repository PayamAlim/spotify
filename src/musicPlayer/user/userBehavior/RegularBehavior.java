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

    //Construuctors
    public RegularBehavior() {
        this.playingLimit = 5;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        throw new InvalidOperationException("Buy Premium to create playlist");
    }

    @Override
    public void playMusic(Music music) {
        if (music == null)
            throw new InvalidOperationException("Cannot play null music!");

        if (playingLimit == 0)
            throw new InvalidOperationException("Your free playing limit has reached");

        music.play();

        playingLimit --;
        System.out.println("- Playing limit: " + playingLimit);
    }

    @Override
    public void buyPremium(User owner, int month) {
        if (owner == null)
            throw new InvalidOperationException("Cannot buy premium for null user");

        owner.setBehavior(new PremiumBehavior(month));
    }
}