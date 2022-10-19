package server.commands;

import server.UserManager;
import common.models.User;
import common.data.Request;
import common.data.Response;

public class UserLogin implements Command{

    public UserLogin() {
    }
    
    public Response execute(Request request) {
        if (request.object == null) {
            return new Response(request.getCommand(), null, "========== Executing command (User Login) ===========\n", User.class);
        } else {
            String login = null;
            String hash = null;
            String output = "";
            User user = (User) request.getObject();
            User auser = UserManager.getInstance().get(user.getLogin(), user.getHash());
            if (auser == null) {
                output = "Please check credentials!\n";
                output = output + "============ Operation error (User Login) ===========\n\n";
            } else {
                login = user.getLogin();
                hash = user.getHash();
                output = output + "========== Operation success (User Login) ===========\n\n";
            }
            return new Response(request.getCommand(), null, output, login, hash);
        }
    }
    
    @Override
    public String toString() {
        return "user_login - Authorize with login and password";
    }
}

