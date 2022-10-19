package server.commands;

import server.CollectionManager;
import common.data.Request;
import common.data.Response;
import java.util.Scanner;
/**
 * Команда Exit
 */
public class Exit implements Command{
    
    public Exit() {
    }

    @Override
    public Response execute(Request request) {
        return null;
    }

    @Override
    public String toString() {
        return "exit - Exit from the serverlication";
    }


}
