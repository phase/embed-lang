package xyz.jadonfowler.embed.engine;

import java.io.*;
import java.nio.file.*;
import com.sun.net.httpserver.*;

public class Engine {
    public static byte[] parseFileRequest(File f, HttpExchange t) {
        try {
            String uri = t.getRequestURI().toString();
            if (uri.endsWith(".html") || uri.endsWith(".em")) {
                Headers h = t.getResponseHeaders();
                h.add("Content-Type", "text/html");
                String content = new String(Files.readAllBytes(Paths.get(f.getPath())));
                String s = parse(content);
                return s.getBytes();
            }
            else {
                return Files.readAllBytes(Paths.get(f.getPath()));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parse(String content) {
        return content;
    }
}