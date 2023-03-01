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
    private static Random rand = new Random();
    private static List<String> columnList = Arrays.asList("Name", "Sex", "Family Name", "Mother", "Father", "Siblings", "DNA");
    private static List<String> eye_color = Arrays.asList("g", "b", "l", "c");
    private static List<String> skin_color = Arrays.asList("w", "b", "l", "y");
    private static List<String> height = Arrays.asList("d", "s", "m", "a", "t", "g");
    private static List<String> hair_color = Arrays.asList("g", "b", "r", "l");
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
        foo.replace("\n", "").replace("\r", "").replace("\n\r", "");
        Collections.addAll(bar, foo.split(";"));
        bar.remove(24);
        return bar;
    }
    
    private static String get_name(String sex)
    {
        String name = "";
        switch(sex)
        {
            case "Male":
                try {
                    name = Files.readAllLines(Paths.get("male.txt")).get(rand.nextInt(2944));   
                } catch (Exception e) {
                    System.out.println("ERROR ON READING MALE NAMES FILE");
                }
                break;
            case "Female":
            try {
                name = Files.readAllLines(Paths.get("female.txt")).get(rand.nextInt(5002));   
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

    private static String get_random_DNA()
    {
        String dna = "";
        dna += "E" + eye_color.get(rand.nextInt(eye_color.size()));
        dna += "S" + skin_color.get(rand.nextInt(skin_color.size()));
        dna += "H" + height.get(rand.nextInt(height.size()));
        dna += "A" + hair_color.get(rand.nextInt(hair_color.size()));
        return dna;
    }

    private static String get_parent_child_DNA(String mother_DNA, String father_DNA)
    {
        String child_dna = "";
        for(int i = 0; i < mother_DNA.length(); i++)
        {
            if(i % 2 == 0)
                child_dna += mother_DNA.charAt(i);
            else
            {
                if(rand.nextInt(101) > 55)
                    child_dna += mother_DNA.charAt(i);
                else
                    child_dna += father_DNA.charAt(i);
            }
        }
        return child_dna;
    }

    private static void store_data(ArrayList<String> dt) 
    {
        String foo = String.join(";", dt);
        try (FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            out.println(foo);
        }
        catch (Exception e) {
            System.out.println("ERROR ON WRITING TO FILE!");
        }
    }

    private static ArrayList<String> get_new_human()
    {
        String foo = "";
        ArrayList<String> dt = new ArrayList<String>();
        while(true)
        {
            System.out.println("Enter a new entry to the data.");
            for(int i = 0; i < mod - 1; i++)
            {
                System.out.println("Enter " + columnList.get(i));
                dt.add(scan.nextLine());
            }
            dt.add(get_random_DNA());
            if(!dt.get(1).equals("Male") || !dt.get(1).equals("Female"))
                System.out.println("ERROR human can be either male or female!");
            
            System.out.println("New entry is:\n" + dt + "\nCorrect Y/N ?");
            foo = scan.nextLine();
            if(foo.equals("Y") || foo.equals("y"))
                break;
            else
                dt.clear();
        }
        return dt;
    }

    private ArrayList<String> human(ArrayList<String> mother,ArrayList<String> father)
    {
        ArrayList<String> child = new ArrayList<String>();


        return child;
    }

    public static void main(String[] args) {
        ArrayList<String> dt = get_data();
        String foo, bar = "";
        foo = dt.get(15);
        bar = dt.get(23);
        System.out.println(OOP_First_Java.get_parent_child_DNA(foo, bar));
        
    }
}
