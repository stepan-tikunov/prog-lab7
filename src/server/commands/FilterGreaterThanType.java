package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.User;
import common.models.VehicleType;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда FilterGreaterThanType
 */
public class FilterGreaterThanType implements Command{
    private final CollectionManager collectionManager;

    public FilterGreaterThanType(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    
    @Override
    public Response execute(Request request) {
        
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        String output = null;
        try {
            String vehicleType = request.getArgs()[0];
            VehicleType.valueOf(vehicleType);
            output =  "===== Executing command (FilterGreaterThanType) =====\n"
                    + collectionManager.FilterGreaterThanType(vehicleType)
                    + "===== Operation success (FilterGreaterThanType) =====\n\n";
        }
        catch (ArrayIndexOutOfBoundsException e) {
            output = "Invalid input! Type was not specified!\n\n";
        }
        catch (Exception e) {
            output = "Invalid input! Such type does not exist!\n\n";
        }
        return new Response(request.getCommand(), null, output);
    }
    

    @Override
    public String toString() {
        return "filter_greater_than_type type - Display elements whose type field value is greater than the given one";
    }

}
