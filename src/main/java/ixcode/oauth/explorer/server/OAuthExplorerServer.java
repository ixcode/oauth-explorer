package ixcode.oauth.explorer.server;

import ch.qos.logback.classic.Level;
import ixcode.platform.HttpServer;
import ixcode.platform.RedirectTrailingSlashes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.INFO;
import static ixcode.platform.LogbackConfiguration.initialiseConsoleLogging;
import static java.lang.Integer.parseInt;

public class OAuthExplorerServer {

    private static final Logger log = LoggerFactory.getLogger(OAuthExplorerServer.class);

    public static void main(String[] args) {
        initialiseConsoleLogging();

        boolean useDefault = args.length == 0;

        String hostname = (useDefault) ? "localhost" : args[0];
        int port = (useDefault) ? 9393 : parseInt(args[1]);
        String webRoot = (useDefault) ? "./resources/public" : args[2];

        new HttpServer(OAuthExplorerServer.class.getSimpleName(),
                hostname, port,
                new RootServlet())
                .servingStaticContentFrom(webRoot)
                .withRedirection(new RedirectTrailingSlashes())
                .start();
    }

}


