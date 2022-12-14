package server.commands;

import server.CollectionManager;
import server.DataProvider;
import server.UserManager;
import common.models.User;
import common.models.VehicleType;
import common.data.Request;
import common.data.Response;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.System.exit;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Команда ExecuteScript
 */
public class ExecuteScript implements Command {
    private final CollectionManager collectionManager;
    private final DataProvider dataProvider;
    private final ExecuteScriptFileNames checkPath = new ExecuteScriptFileNames();

    public ExecuteScript(DataProvider dataProvider, CollectionManager collectionManager) {
        this.dataProvider = dataProvider;
        this.collectionManager = collectionManager;
    }

    
    @Override
    public Response execute(Request request) {
        User user = UserManager.getInstance().get(request.getLogin(), request.getHash());
        if (user == null) {
            return new Response(request.getCommand(), null, "To execute commands login first!\n\n");
        }
        
        String login = user.getLogin();
        String hash = user.getHash();
        
        
        String output;
        try {
            String filename = request.getArgs()[0];
            Path path = Paths.get(filename);
            Path pathAbs = path.toAbsolutePath();
            
            if (checkPath.checkPath(pathAbs)) {
                output = "Calling scripts is looped!\n\n";
            } else {
                String command = null;
                String[] arguments = null;
                Response response;
                Scanner scanner = new Scanner(path);
                boolean skipInput = false;
                String input = null;

                StringBuilder sb = new StringBuilder();
                
                while (scanner.hasNext()) {
                    if (!skipInput) {
                        input = scanner.nextLine();
                        String[] tokens = input.trim().split("\\s+");
                        command = tokens[0];
                        arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
                        request = new Request(command, arguments, login, hash);
                        sb.append("Command: ").append(input).append("\n");
                    }
                    skipInput = false;
                    response = dataProvider.execute(request);
                    if (response != null) {
                        if (response.getLogin() != null && response.getHash() != null) {
                            login = response.getLogin();
                            hash = response.getHash();
                        }
                        if (response.getObject() == null) {
                            if (response.getOutput() != null) {
                                sb.append(response.getOutput());
                            }
                        } else {
                            if (response.getOutput() != null) {
                                sb.append(response.getOutput());
                            }
                            Method readMethod = response.type.getMethod("input", Scanner.class, Boolean.class);
                            Object object = readMethod.invoke(null, scanner, true);
                            if (object == null) {
                                return new Response(request.getCommand(), null, "Oops, uncorrect script\n\n");
                            }
                            request = new Request(command, arguments, (Serializable)object, login, hash);
                            skipInput = true;
                        }
                    }
                }
                output = "========= Executing command (ExecuteScript) =========\n";
                output = output + sb.toString();
                output = output + "\n========== Operation success (ExecuteScript) ========\n\n";
            }
            checkPath.clearList();
        }
        catch (ArrayIndexOutOfBoundsException e){
            output = "Invalid input! Type was not specified!\n\n";
        }
        catch (IOException e) {
            output = "Couldn't find script file!\n\n";
        }
        catch (Exception e) {
            e.printStackTrace();
            output = "Oops, uncorrect script\n\n";
        }
        return new Response(request.getCommand(), null, output);
    }
    
    @Override
    public String toString() {
        return "execute_script file_name - Read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode";
    }

}
