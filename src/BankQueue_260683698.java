import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by emol on 10/19/17.
 */
public class BankQueue_260683698 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int clientCnt = sc.nextInt();
        int totalTime = sc.nextInt();

        Client[] allClients = new Client[clientCnt];
        for (int i = 0; i < clientCnt; i++){
            int amt = sc.nextInt();
            int time = sc.nextInt();
            allClients[i] = new Client(amt, time);
        }
        Bank b = new Bank(allClients, totalTime);
        System.out.println(b.getMaxRev());
    }
}

class Bank{
    Client[] allClients;
    Client[] selectedClient;
    int totalTime;

    public Bank(Client[] clients, int totalTime){
        this.allClients = clients;
        this.totalTime = totalTime;
    }

    public int getMaxRev(){
        Arrays.sort(allClients);

        selectedClient = new Client[this.totalTime];
        int sum = 0;

        for (int i = 0; i < allClients.length; i++){
            Client c = allClients[i];
            if (selectedClient[c.time] == null) {
                selectedClient[c.time] = c;
                sum += c.amount;
            }
            else {
                // try to find the nearest available time
                int j = c.time - 1;
                while (j >= 0){
                    if (selectedClient[j] == null){
                        selectedClient[j] = c;
                        sum += c.amount;
                        break;
                    }
                    else j--;
                }
            }
        }
        return sum;
    }



}


class Client implements Comparable<Client>{
    int amount;
    int time;
    public Client(int amount, int time){
        this.amount = amount;
        this.time = time;
    }

    public int compareTo(Client c) {
        return c.amount - this.amount;
    }
}
