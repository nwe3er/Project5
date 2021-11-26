import java.util.*;
import java.io.*;
import java.time.Duration;

public class DriverP5 {
    public static void main(String[] args) {

        if (args.length == 4) {
            try {
                File f1 = new File(args[1]);
                File f2 = new File(args[2]);
                File f3 = new File(args[3]);

                f1.createNewFile();
                f2.createNewFile();
                f3.createNewFile();

                PrintWriter report = new PrintWriter(f1);
                PrintWriter unSorted = new PrintWriter(f2);
                PrintWriter sorted = new PrintWriter(f3);

                int size = Integer.parseInt(args[0]);

                ArrayList<Integer> list = QuickSorter.generateRandomList(size);

                unSorted.println(list);
                unSorted.close();

                ArrayList<Integer> copy1 = new ArrayList<>(list);

                Duration firstElement;

                firstElement = QuickSorter.timedQuickSort(copy1, QuickSorter.PivotStrategy.FIRST_ELEMENT);

                sorted.println(copy1);
                sorted.close();

                report.println("Array Size = " + size);
                report.println("FIRST_ELEMENT : " + firstElement);

                report.close();


            }
            catch (Exception exception) {
                System.out.println("File creation unsuccessful");
            }
        }
        else {
            System.out.println("You must insert valid Arguments in Command lines");
        }
    }
}
