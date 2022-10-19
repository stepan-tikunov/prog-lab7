package common.models;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Comparable<User>, Serializable  {

    private String login;
    private String hash;

    public User() {
        this.login = "";
        this.hash = "";
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public int compareTo(User o) {
        if (this == o)
            return 0;
        if (o == null || getClass() != o.getClass())
            return -1;

        User other = (User) o;
        return this.login.compareTo(other.login);
    }
    

    public static User of(String login, String hash) {
        User user = new User();
        user.login = login;
        user.hash = hash;
        return user;
    }
    
    public static User input(Scanner scanner, Boolean script) {
        UserInput input = new UserInput(scanner, script);
        User user = input.resultElement(0);
        return user;
    }
    
    
}
