package xyz.jadonfowler.embed;

import java.io.*;
import com.sun.net.httpserver.*;
import xyz.jadonfowler.embed.engine.*;

public class ServerHandler implements HttpHandler {
    @Override public void handle(HttpExchange t) throws IOException {
        String uri = t.getRequestURI().toString();
        File f = new File(Embed.rootDir, uri);
        byte[] data;
        if (f.exists()) {
            data = Engine.parseFileRequest(f, t);
        }
        else {
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "text/html");
            String s = "<h1>404</h1><p><code> " + uri + "</code> not found!</p>";
            data = s.getBytes();
        }
        t.sendResponseHeaders(200, data.length);
        OutputStream os = t.getResponseBody();
        os.write(data, 0, data.length);
        os.close();
    }
}