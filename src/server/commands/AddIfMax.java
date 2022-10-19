package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.Coordinates;
import common.models.User;
import common.models.Vehicle;
import common.models.VehicleType;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда AddIfMax
 */
public class AddIfMax implements Command {

    private final CollectionManager collectionManager;
    
    public AddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        if (request.object == null) {
            return new Response(request.getCommand(), null, null, Vehicle.class);
        } else {
            String output = "";
            Vehicle vehicle = (Vehicle) request.getObject();
            vehicle.setOwner(user.getLogin());
            if (collectionManager.addIfMax(vehicle)) {
                output = "=========== Operation success (AddIfMax) ============\n\n";
            } else {
                output = "\n============ Operation error (AddIfMax) =============\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }
    }
    
    

    @Override
    public String toString() {
        return "add_if_max {element} - Add a new element to the collection if its value is greater than the value of the largest element in this collection";
    }

}
