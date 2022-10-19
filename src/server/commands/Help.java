package server.commands;

import server.DataProvider;
import common.data.Request;
import common.data.Response;
import java.util.Comparator;

/**
 * Команда Help
 */

public class Help implements Command{
    
    @Override
    public Response execute(Request request) {
        String output = DataProvider.commands
                .values()
                .stream()
                .sorted(Comparator.comparing(command -> command.toString()))
                .map(Command::toString)
                .reduce("", (result, element) -> result + element + "\n");
        
        output =  "============= Executing command (Help) ==============\n"
                + output
                + "============== Operation success (Help) =============\n\n";
        return new Response(request.getCommand(), null, output);
        
        
    }

    @Override
    public String toString() {
        return "help - Show this help";
    }
}

