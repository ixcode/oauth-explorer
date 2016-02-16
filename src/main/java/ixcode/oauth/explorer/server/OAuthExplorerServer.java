package ixcode.oauth.explorer.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileNotFoundException;

import static ch.qos.logback.classic.Level.INFO;
import static ixcode.platform.LogbackConfiguration.STANDARD_OPS_FORMAT;
import static ixcode.platform.LogbackConfiguration.initialiseConsoleLogging;
import static java.lang.String.format;

public class OAuthExplorerServer {

    private static final Logger log = LoggerFactory.getLogger(OAuthExplorerServer.class);
    private static FilterHolder CORSFilterClass;

    public static void main(String[] args) {
        String domainName = (args.length == 2) ? args[1] : "localhost";
        new OAuthExplorerServer("openam-server", 9009, args[0], domainName).start();
    }

    private String serverName;
    private final int httpPort;
    private final String openAmWarFilePath;
    private String domainName;
    private Server server;


    public OAuthExplorerServer(String serverName, int httpPort, String openAmWarFilePath, String domainName) {
        this.serverName = serverName;
        this.httpPort = httpPort;
        this.openAmWarFilePath = openAmWarFilePath;
        this.domainName = domainName;
    }

    public void start() {
        try {
            initialiseConsoleLogging(INFO, STANDARD_OPS_FORMAT);


            log.info(format("Starting Server [%s] on port %d", serverName, httpPort));

            this.server = new Server(httpPort);

            server.setHandler(rootHandler(server));


            server.start();

            log.info(format("Server [%s] started @ http://%s:%d/oauth-explorer", serverName, domainName, httpPort));

            server.join();
        } catch (Throwable t) {
            throw new RuntimeException(format("Could not start server [%s] (see cause)", serverName), t);
        }
    }

    private static Handler rootHandler(Server server) {
        HandlerList handlerList = new HandlerList();


        Handler[] handlers = new Handler[]{};

        handlerList.setHandlers(handlers);

        return handlerList;
    }




}