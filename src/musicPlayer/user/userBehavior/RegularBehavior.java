package musicPlayer.user.userBehavior;

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
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
    }
}