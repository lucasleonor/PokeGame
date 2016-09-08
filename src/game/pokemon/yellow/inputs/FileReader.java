package game.pokemon.yellow.inputs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Lucas G R Leonor
 */
public class FileReader {

    public static BufferedReader getPageBR(String file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    public static JsonObject getContent(String file) {
        try {
            StringBuilder sb;
            try (BufferedReader br = getPageBR(file)) {
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
            return Json.createReader(new StringReader(new Scanner(sb.toString()).useDelimiter("\\A").next())).readObject();
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
