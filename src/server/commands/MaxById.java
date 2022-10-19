package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.User;
import common.models.Vehicle;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда MaxById
 */
public class MaxById implements Command{

    private final CollectionManager collectionManager;

    public MaxById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        Vehicle vehicle = collectionManager.getMaxById();
        String output = "=========== Executing command (MaxById) =============\n"
                + ((vehicle!=null)?vehicle.toString():"Collection is empty!")
                + "\n============ Operation success (MaxById) ============\n\n";
        return new Response(request.getCommand(), null, output);
    }

    @Override
    public String toString() {
        return "max_by_id - Display any object from the collection whose id field value is the maximum";
    }

}

