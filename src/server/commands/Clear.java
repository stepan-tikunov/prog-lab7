package server.commands;

import server.CollectionManager;
import server.DataProvider;
import server.UserManager;
import common.models.User;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда Clear
 */
public class Clear implements Command {

    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        String output;
        if (collectionManager.clear(user.getLogin())) {
            output = "============= Operation success (Clear) =============\n\n";
        } else {
            output = "\n============== Operation error (Clear) ==============\n\n";
        }
        return new Response(request.getCommand(), null, output);
    }
    

    @Override
    public String toString() {
        return "clear - Clear the collection";
    }

}
