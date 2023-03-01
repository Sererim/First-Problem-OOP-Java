import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;



public class OOP_First_Java {

    private static File file = new File("data.txt");
    private static List<String> columList = Arrays.asList("Name", "Sex", "Family Name", "Mother", "Father", "Siblings", "DNA");
    private static int mod = 6;
    private static Scanner scan = new Scanner(System.in);

    private static ArrayList<String> get_data()
    {
        String foo = "";
        int i = 0;
        ArrayList<String> bar = new ArrayList<String>();
        try (FileReader fr = new FileReader(file)) {
            while((i = fr.read()) != -1)
                foo += (char)i;
        } catch (Exception e) {
            System.out.println("ERROR ON READING DARA");
        }
        Collections.addAll(bar, foo.split(";"));
        return bar;
    }
    

    public static void main(String[] args) {
        System.out.println(OOP_First_Java.get_data());
    }
}
