package musicPlayer.user.userBehavior;

import musicPlayer.user.*;

public interface UserBehavior {
    void createPlaylist (String Title, User Owner);
    void buyPremium (User owner, int month);
}
