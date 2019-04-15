package hungergames.userinterface;

import hungergames.Constants;
import util.JSONFileReader;

import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {
        System.out.println(JSONFileReader.read(Constants.JSON_FILE_NAME));
    }
}