package sortDictionaries;

import java.io.*;
import java.text.*;
import java.util.regex.Pattern;

/**
 * @author uberspot
 */
public class sortDictionaries {

    public static void main(String[] args) {
       transformDictionary("el_gr");
       transformDictionary("en_us");
       transformDictionary("fr_fr");
       transformDictionary("pl_pl");
    }
    
    public static void transformDictionary(String fileName) {
         try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
                PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileName + "_sorted")));
                String line = in.readLine();
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                while(line!=null) {
                        char[] l = line.toCharArray();
                        java.util.Arrays.sort(l);

                        String nfdNormalizedString = Normalizer.normalize((new String(l)), Normalizer.Form.NFD); 
			            String deaccented = pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();

                        out.println(deaccented.hashCode());
                        line = in.readLine();
                }
                in.close();
                out.flush();
                out.close();
        } catch (UnsupportedEncodingException e) {
                        System.out.println("UnsupportedEncoding: " + e.getMessage());
        } catch (IOException e) {
                        System.out.println("IO Error: " + e.getMessage());
        } 
    }
}