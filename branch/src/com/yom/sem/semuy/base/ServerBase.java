package com.yom.sem.semuy.base;

import com.franz.agraph.jena.AGGraphMaker;
import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;
import org.openrdf.OpenRDFException;
import org.openrdf.repository.RepositoryException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerBase {
    private static String SERVER_URL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String CATALOG_ID = "";

    private static List<AGRepositoryConnection> toClose = new ArrayList<AGRepositoryConnection>();
    public static AGServer server;

    public void init() {
        connection();
        server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
    }

    // Return graph marker, if not, will create new
    public static AGGraphMaker getMarker(String repository_id) {
        try {
            AGCatalog catalog = server.getCatalog(CATALOG_ID);

            AGRepository repository;
            if (catalog.hasRepository(repository_id)) {
                repository = catalog.openRepository(repository_id);
            } else {
                repository = catalog.createRepository(repository_id);
                repository.initialize();
            }

            AGRepositoryConnection conn = repository.getConnection();
            AGGraphMaker marker = new AGGraphMaker(conn);
            return marker;
        } catch (RepositoryException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OpenRDFException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    private static void connection() {
        String configFile = ClassLoader.getSystemResource("").getPath() + File.separator + "database.properties";
        DatabaseConfig database = new DatabaseConfig(configFile);
        SERVER_URL = database.getValue("SERVER_URL");
        USERNAME = database.getValue("USERNAME");
        PASSWORD = database.getValue("PASSWORD");
        CATALOG_ID = database.getValue("CATALOG");
    }

    // Close all repositories connections
    public static void closeAll() {
        while (toClose.isEmpty() == false) {
            AGRepositoryConnection conn = toClose.get(0);
            close(conn);
            while (toClose.remove(conn)) {}
        }
    }

    // Close repository connection
    private static void close(AGRepositoryConnection conn) {
        try {
            conn.close();
        } catch (RepositoryException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
