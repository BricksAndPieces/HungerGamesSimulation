package util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader {

    public static JSONObject read(final String fileName) throws IOException{
        final BufferedReader br = new BufferedReader(new FileReader(fileName));
        final StringBuilder sb = new StringBuilder();

        String line = br.readLine();
        while(line != null) {
            sb.append(line);
            line = br.readLine();
        }

        return new JSONObject(sb.toString());
    }
}