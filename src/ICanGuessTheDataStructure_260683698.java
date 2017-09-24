import java.util.*;

/**
 * Created by emol on 9/19/17.
 */
public class ICanGuessTheDataStructure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (sc.hasNextInt()) {
            int cnt;
            try {
                cnt = sc.nextInt();
            } catch (Exception e) {
                return;
            }

            // possible result
            boolean isQueue = true;
            boolean isStack = true;
            boolean isPriorityQueue = true;

            // possible data structure
            Queue<Integer> queue = new LinkedList<Integer>();
            Stack<Integer> stack = new Stack<Integer>();
            PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

            while (cnt > 0 && sc.hasNextInt()) {
                int cmd;
                int val;
                try {
                    cmd = sc.nextInt();
                    val = sc.nextInt();
                } catch (Exception e) {
                    return;
                }


                // process cmd
                switch (cmd) {
                    case 1:
                        if (isQueue) queue.add(val);
                        if (isPriorityQueue) pQueue.add(-val);
                        if (isStack) stack.add(val);
                        // in java PriorityQueue, smaller number comes first
                        break;
                    case 2:
                        if (queue.size() <= 0 || queue.poll() != val) isQueue = false;
                        if (pQueue.size() <= 0 || pQueue.poll() != -val) isPriorityQueue = false;
                        if (stack.size() <= 0 || stack.pop() != val) isStack = false;

                }
                cnt --;
            }

            if (isPriorityQueue && !isQueue && !isStack) System.out.println("priority queue");
            else if (!isPriorityQueue && isQueue && !isStack) System.out.println("queue");
            else if (!isPriorityQueue && !isQueue && isStack) System.out.println("stack");
            else if (!isPriorityQueue && !isQueue && !isStack) System.out.println("impossible");
            else System.out.println("not sure");
    }
}
}
