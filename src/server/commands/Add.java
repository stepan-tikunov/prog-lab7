package server.commands;

import server.CollectionManager;
import common.models.Coordinates;
import common.models.Vehicle;
import common.models.VehicleType;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
import server.UserManager;
import common.models.User;


/**
 * Команда Add
 */
public class Add implements Command{
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    
    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        if (request.object == null) {
            return new Response(request.getCommand(), null, "============= Executing command (Add) ===============\n", Vehicle.class);
        } else {
            String output = "";
            Vehicle vehicle = (Vehicle) request.getObject();
            vehicle.setOwner(user.getLogin());
            if (collectionManager.add(vehicle)) {
                output = "============== Operation success (Add) ==============\n\n";
            } else {
                output = "=============== Operation error (Add) ===============\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }
        
    }
    
    @Override
    public String toString() {
        return "add {element} - Add a new element to the collection";
    }
}

