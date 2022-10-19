package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.User;
import common.models.Vehicle;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда RemoveLower
 */
public class RemoveLower implements Command{
    private final CollectionManager collectionManager;
    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        if (request.object == null) {
            return new Response(request.getCommand(), null, "========= Executing command (RemoveLower) ===========\n", Vehicle.class);
        } else {
            String output = "";
            Vehicle vehicle = (Vehicle) request.getObject();
            if (collectionManager.removeLower(vehicle, user.getLogin())) {
                output = "========= Operation success (RemoveLower) ===========\n\n";
            } else {
                output = "\n========== Operation error (RemoveLower) ============\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }
    }

    @Override
    public String toString() {
        return "remove_lower {element} - Remove from the collection all elements smaller than the given one";
    }

}
