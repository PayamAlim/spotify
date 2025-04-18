import musicPlayer.exceptions.InvalidOperationException;
import musicPlayer.music.Music;
import musicPlayer.music.Playlist;
import musicPlayer.user.*;
import musicPlayer.user.userBehavior.*;

public class Main {
    public static void main(String[] args) {
        //Test Users interaction
        User user1 = null;
        User user2 = null;
        User user3 = null;
        try {
            user1 = new User("Payam", "123456789");
            user2 = new User("MMD", "876543210");
            user3 = new User("Ali", "01233210");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("* test wrong inputs");
        User wrongUser1 = null;
        User wrongUser2 = null;
        try {
            wrongUser1 = new User("Payam", "1234444444444");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
        try {
            wrongUser2 = new User("new MMD", "8765");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("* test random adding user");
        try {
            User.addUser(null);
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("* test random follower");
        try {
            user1.addFollower(user2);
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("* test random follow");
        try {
            user1.follow(null);
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("* test buy premium");
        try {
            user1.buyPremium(4);
            if (user1.getBehavior() instanceof PremiumBehavior)
                System.out.println("Success");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("* test buy premium for premium");
        user1.buyPremium(4);
        System.out.println(((PremiumBehavior)user1.getBehavior()).getMonth());

        System.out.println("* test following");
        try {
            user2.follow(user3);
            if (user2.getFollowingList().contains(user3) && user3.getFollowerList().contains(user2))
                System.out.println("Success");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        //test music
        User singer1 = new User("Dariush", "444444444");
        User singer2 = new User("Moein", "444444444");
        User singer3 = new User("Siavash", "444444444");

        Music music1 = new Music("Shahre Gham", singer1);
        Music music2 = new Music("Bibigol", singer2);
        Music music3 = new Music("Parseh", singer3);


        System.out.println("* test add playlist to account");
        user1.createPlaylist("Best");
        try {
            user2.createPlaylist("BAD");
        }
        catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
        Playlist best = user1.getPlaylists().get(0);
        best.addMusic(music3, "123456789");
        best.addMusic(music1, "123456789");
        best.editTitle("Mahshar", "123456789");
        best.removeMusic(music1, "123456789");

        if (best.searchInPlaylist("Shahre Gham") == null) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
        Music music = best.searchInPlaylist("Parseh", "Siavash");
        music.play();
    }
}