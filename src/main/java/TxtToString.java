import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bobby on 11.06.2017.
 */
public class TxtToString {

    private List<String> liste = new ArrayList<String>();

    public TxtToString(String path)
    {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {

                liste.add(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String s : liste) {
            System.out.println(s);
        }

    }

    public List<String> getListe() {
        return liste;
    }

}