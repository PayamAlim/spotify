package musicPlayer.user.userBehavior;

import musicPlayer.music.*;
import musicPlayer.user.*;

public interface UserBehavior {
    void createPlaylist (String title, User owner);
    void playMusic (Music music);
    void buyPremium (User owner, int month);
}
