package server.commands;

import common.data.Request;
import common.data.Response;

public interface Command {
    
    public Response execute(Request request);
    
}

