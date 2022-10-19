package common.data;

import java.io.Serializable;

public class Response implements Serializable {
    
    public String command;
    public String output;
    public String[] args;
    public Class<? extends Serializable> type;
    public String login;
    public String hash;
    

    public Response(String command, String[] args, String output) {
        this.command = command;
        this.output = output;
    }

    public Response(String command, String[] args, String output, String login, String hash) {
        this.command = command;
        this.output = output;
        this.login = login;
        this.hash = hash;
    }
    
    public Response(String command, String[] args, String output, Class<? extends Serializable> type) {
        this.command = command;
        this.output = output;
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public String getOutput() {
        return output;
    }

    public Class<? extends Serializable> getObject() {
        return type;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
}
