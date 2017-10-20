import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by emol on 10/5/17.
 */
public class StringMatching_260683698 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            String pat = sc.nextLine();
            String txt = sc.nextLine();
            ArrayList<Integer> result = KMPSearch(pat, txt);
            for (int i : result){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> KMPSearch(String pat, String txt){
        ArrayList<Integer> result = new ArrayList<>();
        int patLen = pat.length();
        int txtLen = txt.length();

        // create lps[]
        int lps[] =  new int[patLen];
        int patIndex = 0;

        // preprocess the pattern
        computeLPSArray(pat, patLen, lps);

        int i = 0;  // index for txt[]
        while (i < txtLen){
            if (pat.charAt(patIndex) == txt.charAt(i)){
                patIndex++;
                i++;
            }
            if (patIndex == patLen){
                result.add(i - patIndex);
                patIndex = lps[patIndex - 1];
            }

            else if (i < txtLen && pat.charAt(patIndex) != txt.charAt(i)){
                if (patIndex != 0){
                    patIndex = lps[patIndex - 1];
                }
                else  i ++;
            }
        }
        return result;

    }

    private static void computeLPSArray(String pat, int patLen, int lps[]){
        int prefixLen = 0;
        int i = 1;
        lps[0] = 0;

        while (i < patLen){
            if (pat.charAt(i) == pat.charAt(prefixLen)){
                prefixLen ++;
                lps[i] = prefixLen;
                i++;
            }
            else {
                if (prefixLen != 0) prefixLen = lps[prefixLen - 1];
                else {
                    lps[i] = prefixLen;
                    i ++;
                }
            }
        }
    }

}
