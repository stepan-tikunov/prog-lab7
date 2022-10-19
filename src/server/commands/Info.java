package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.User;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда Info
 */
public class Info implements Command{
    
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        String output = "============= Executing command (Info) ==============\n";
        output = output + collectionManager.info();
        output = output + "\n============== Operation success (Info) =============\n\n";
        return new Response(request.getCommand(), null, output);
    }

    @Override
    public String toString() {
        return "info - Print information about the collection to the standard output stream (type, initialization date, number of elements, etc.)";
    }


}
