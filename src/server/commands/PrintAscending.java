package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.User;
import common.models.Vehicle;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда PrintAscending
 */
public class PrintAscending implements Command{
    private final CollectionManager collectionManager;

    public PrintAscending(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    
    @Override
    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        String output =  "======== Executing command (PrintAscending) =========\n"
                + collectionManager.printAscending()
                + "======== Operation success (PrintAscending) =========\n\n";
        return new Response(request.getCommand(), null, output);
    }

    @Override
    public String toString() {
        return "print_ascending - Display the elements of the collection in ascending order";
    }
}
