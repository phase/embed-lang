package xyz.jadonfowler.embed;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class Embed {
    public static File rootDir = new File("C:/embed");
    private static HttpServer server;
    private static int port = 80;

    public static void main(String[] args) {
        parseArguments(args);
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new ServerHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }
        catch (IOException e) {
            System.err.println("ERROR: Failed to create HTTP Server! ");
        }
    }

    private static void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equalsIgnoreCase("-d") && ++i < args.length) {
                rootDir = new File(args[i]);
            }
            else if (s.equalsIgnoreCase("-p") && ++i < args.length) {
                port = Integer.parseInt(args[i]);
            }
        }
    }
}