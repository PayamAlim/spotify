package musicPlayer.user;

import musicPlayer.exceptions.InvalidOperationException;
import musicPlayer.user.userBehavior.*;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();

    private String username;

    private String password;

    private UserBehavior behavior;

    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();

    //Getters
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    //Setters
    public static void addUser(User user) {
        if (user == null)
            throw new InvalidOperationException("Can not add null user!");

        allUsers.add(user);
    }

    public void setUsername(String username) {
        for (User user: allUsers)
            if (user.username.equals(username))
                throw new InvalidOperationException("Username already exist!");

        this.username = username;
    }

    public void setPassword(String password) {
        if (password.length() < 8)
            throw new InvalidOperationException("Password's length must be (>= 8)");

        this.password = password;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public void addFollower (User user) {
        if (!user.followingList.contains(this))
            throw new InvalidOperationException(user.username + "doesn't follow you!");

        followerList.add(user);
    }

    //Constructor
    public User (String username, String password) {
        //Set and Validate
        this.setUsername(username);
        this.setPassword(password);

        behavior = new RegularBehavior();

        User.addUser(this);
    }

    //Methods
    public void follow (User user) {
        if (!allUsers.contains(user))
            throw new InvalidOperationException("User not found to follow!");

        followingList.add(user);

        user.addFollower(this);
    }

    public void buyPremium (int month) {
        behavior.buyPremium(this, month);
    }
}
