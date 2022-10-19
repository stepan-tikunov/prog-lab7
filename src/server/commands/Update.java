package server.commands;

import server.CollectionManager;
import server.UserManager;
import common.models.User;
import common.models.Vehicle;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда Update
 */
public class Update implements Command{

    private final CollectionManager collectionManager;
    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        Vehicle updateVehicle = null;
        String output = null;
        try {
            int id = Integer.parseInt(request.getArgs()[0]);
            updateVehicle = collectionManager.getById(id);
            if (updateVehicle == null) {
                output = "Object with id=" + id + " not found!\n\n";
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            output = "Invalid input! ID was not specified!\n\n";
        }
        catch (NumberFormatException e) {
            output = "Invalid input! ID must be a number!\n\n";
        }
        
        if (output != null) {
            return new Response(request.getCommand(), null, output);
        }
        
        if (request.object == null) {
            return new Response(request.getCommand(), request.getArgs(), "=========== Executing command (Update) ==============\n", Vehicle.class);
        } else {
            Vehicle vehicle = (Vehicle) request.getObject();
            if (collectionManager.updateElement(updateVehicle, vehicle, user.getLogin())) {
                output = "============ Operation success (Update) =============\n\n";
            } else {
                output = "============= Operation error (Update) ==============\n\n";
            }
            return new Response(request.getCommand(), null, output);
        }
    }

    /**
    public void execute(Scanner input, String args[], boolean silent) {
        try {
            final Integer id = Integer.parseInt(args[0]);
            if (!this.collectionManager.update_by_id(id) && !silent) {
                System.out.println("Object with id = " + id + " not found!");
            } else {
                Input vInput = new Input(input, silent);
                if(collectionManager.add(vInput.resultElement(collectionManager.getNewId()))) {
                    if(!silent){System.out.println("Operation success (Show)");}
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            if(!silent) {
                System.out.println("Invalid input! id not entered");
            }
        }
        catch (NumberFormatException e) {
            if(!silent) {
                System.out.println("Invalid input! id was entered incorrectly");
            }
        }
    }
    */

    @Override
    public String toString() {
        return "update id {element} - Update the value of the collection element whose id is equal to the given one";
    }
}
