import java.util.Scanner;

/**
 * Created by emol on 9/19/17.
 */
public class Oddities_260683698 {
    public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            long cnt = sc.nextLong();
            while (sc.hasNextLong() && cnt > 0) {
                long a = sc.nextLong();
                cnt --;
                System.out.println(a + " is " + (a % 2 == 0 ? "even" : "odd"));

            }
    }


}

