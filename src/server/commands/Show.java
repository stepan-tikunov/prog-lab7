package server.commands;

import server.CollectionManager;
import server.DataProvider;
import server.UserManager;
import common.models.User;
import common.models.Vehicle;
import common.data.Request;
import common.data.Response;
import java.util.Comparator;
import java.util.Scanner;
/**
 * Команда Show
 */
public class Show implements Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    
    @Override
    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        String output =  "============= Executing command (Show) ==============\n"
                + collectionManager.show()
                + "============= Operation success (Show) ==============\n\n";
        return new Response(request.getCommand(), null, output);
    }
        
    @Override
    public String toString() {
        return "show - Print to standard output all elements of the collection in string representation";
    }
}
