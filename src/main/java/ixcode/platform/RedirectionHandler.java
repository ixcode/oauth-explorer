package ixcode.platform;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RedirectionHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(RedirectionHandler.class);
    private List<Redirection> redirections;

    public RedirectionHandler(List<Redirection> redirections) {
        this.redirections = redirections;
    }


    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (Redirection redirection : this.redirections) {
            if (redirection.handles(target)) {
                redirection.redirect(target, request, response);
                break;
            }
        }
    }
}