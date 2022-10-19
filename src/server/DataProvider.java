package server;

import common.data.Request;
import common.data.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.commands.*;

/**
 * Класс, который хранит все возможные команды и вызывает их по ключу.
 */
public class DataProvider {
    public static final HashMap<String, Command> commands = new HashMap<>();
    private CollectionManager collectionManager;
    
    public DataProvider() {
        collectionManager = new CollectionManager();
        commands.put("help", new Help());                                       // done
        commands.put("clear", new Clear(collectionManager));                    // done
        commands.put("add", new Add(collectionManager));                        // done
        commands.put("info", new Info(collectionManager));                      // done
        commands.put("add_if_max", new AddIfMax(collectionManager));
        commands.put("remove_greater", new RemoveGreater(collectionManager));   // done
        commands.put("remove_lower", new RemoveLower(collectionManager));       // done
        commands.put("show", new Show(collectionManager));                      // done
        commands.put("update", new Update(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager));        // done
        commands.put("max_by_id", new MaxById(collectionManager));              // done
        commands.put("print_ascending", new PrintAscending(collectionManager)); // done
        commands.put("filter_greater_than_type", new FilterGreaterThanType(collectionManager)); // done
        commands.put("exit", new Exit());                      // done
        commands.put("execute_script", new ExecuteScript(this, collectionManager));
        commands.put("user_register", new UserRegister());
        commands.put("user_login", new UserLogin());
        
    }

    public Response execute(Request request) {
        
        /**
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        if (commands.containsKey(request.getCommand())) {
            return commands.get(request.getCommand()).execute(request);
        } else {
            return new Response(request.getCommand(), null, "Command not found!\n\n");
        }
    }
    
    /**
    public void doSave() {
        collectionManager.save();
    }
    */
    
}
