
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Bobby on 11.06.2017.
 */


public class Tagging {

    public static void main(String[] args) {

        List<String> seqPlzStadt = (new TxtToString("C:\\Users\\Bobby\\Desktop\\Uni\\Semester 4\\WINF_Projekt_BIG_DATA\\seqPlzStadt.txt").getListe() );
        List<String> plz = new ArrayList<String>();
        List<String> stadt = new ArrayList<String>();

        List<String> nr = new ArrayList<String>();
        List<String> straße = new ArrayList<String>();

        String inFile = null;


        inFile = args[0];


        try {

            FileInputStream fis = new FileInputStream(inFile);
            String content = IOUtils.toString(fis,"UTF-8");

            // suche nach PLZ und Stadt


            String plzPattern = "((\\D\\-)?\\d{5}\\s)";

            Pattern p = Pattern.compile(plzPattern);
            Matcher m = p.matcher(content);

            while (m.find()) {
                int nextStart = m.end();
                int nextCounter = nextStart;
                int nextEnd;

                while (content.charAt(nextCounter) != ' '  && content.charAt(nextCounter) != '\r' && content.length() > nextCounter ) {

                    nextCounter = nextCounter + 1;
                }
                nextEnd = nextCounter - 1;

                String next = content.substring(nextStart , nextEnd);
                String plzOhneSpace = content.substring(m.start(), m.end() - 1);
                String plzStadt = m.group(1) + next;
                System.out.println(next);
                System.out.println(plzStadt);




                // HausNr.
                int preEnd = m.start() - 1;
                int preCounter = preEnd - 1;


                while (content.charAt(preCounter) != ' ' ) {
                        preCounter = preCounter - 1;
                }

                int preStart = preCounter + 1;
                String tempNr = content.substring(preStart,preEnd);


                // Straße
                preEnd = preStart - 1;
                preCounter = preEnd - 1;

                while (content.charAt(preCounter) != ' ' ) {
                    preCounter = preCounter  - 1;
                }

                preStart = preCounter + 1;
                String tempStraße = content.substring(preStart, preEnd);


                if(seqPlzStadt.contains(plzStadt)){
                    plz.add(plzOhneSpace);
                    stadt.add(next);
                    straße.add(tempStraße);
                    nr.add(tempNr);

                }
            }

            fis.close();


            System.out.print(plz);
            System.out.print(stadt);
            System.out.print(straße);
            System.out.print(nr);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("Unexcepted Exception");
            e.printStackTrace();
        }

    }
}
