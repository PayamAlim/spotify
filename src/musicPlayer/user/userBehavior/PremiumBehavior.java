package musicPlayer.user.userBehavior;

import musicPlayer.user.*;

public class PremiumBehavior implements UserBehavior{
    private int month;

    //Getters
    public int getMonth() {
        return month;
    }

    //Setters
    public void setMonth(int month) {
        this.month = month;
    }

    //Constructor
    public PremiumBehavior(int month) {
        this.setMonth(month);
    }

    @Override
    public void buyPremium(User owner, int month) {
        this.month += month;
    }
}
