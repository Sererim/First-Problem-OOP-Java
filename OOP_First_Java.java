import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class OOP_First_Java {

    private static File file = new File("data.txt");
    private static List<String> columList = Arrays.asList("Name", "Sex", "Family Name", "Mother", "Father", "Siblings", "DNA");
    private static int mod = 7;
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
            System.out.println("ERROR ON READING DATA");
        }
        Collections.addAll(bar, foo.split(";"));
        return bar;
    }
    
    private ArrayList<String> human(ArrayList<String> mother,ArrayList<String> father)
    {
        ArrayList<String> child = new ArrayList<String>();


        return child;
    }

    private static String get_name(String sex)
    {
        Random rand = new Random();
        int foo = 0;
        String name = "";
        switch(sex)
        {
            case "Male":
                foo = rand.nextInt(2944);
                try {
                    name = Files.readAllLines(Paths.get("male.txt")).get(foo);   
                } catch (Exception e) {
                    System.out.println("ERROR ON READING MALE NAMES FILE");
                }
                break;
            case "Female":
            foo = rand.nextInt(5002);
            try {
                name = Files.readAllLines(Paths.get("female.txt")).get(foo);   
            } catch (Exception e) {
                System.out.println("ERROR ON READING MALE NAMES FILE");
            }
                break;
            default:
                System.out.println("ERROR ON GIVING NAME");
                break;
        }
        return name;
    }

    public static void main(String[] args) {
        System.out.println(OOP_First_Java.get_name("Female"));
    }
}
