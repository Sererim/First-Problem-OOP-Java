import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class OOP_First_Java {

    private static Random rand = new Random();
    private static ArrayList<String> columnList = new ArrayList<String>(Arrays.asList("Name", "Sex", "Family Name", "Mother", "Father", "Siblings", "DNA"));
    private static List<String> eye_color = Arrays.asList("g", "b", "l", "c");
    private static List<String> skin_color = Arrays.asList("w", "b", "l", "y");
    private static List<String> height = Arrays.asList("d", "s", "m", "a", "t", "g");
    private static List<String> hair_color = Arrays.asList("g", "b", "r", "l");
    private static Scanner scan = new Scanner(System.in);
    private ArrayList<String> All = new ArrayList<>();

    private static ArrayList<String> get_data()
    {
        String foo = "";
        try {
            foo = new String(Files.readAllBytes(Paths.get("data.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> bar = new ArrayList<String>();
        foo = foo.replace("\n", "").replace("\r", "").replace("\r\n", "").replaceAll("\n\r", "");
        bar = new ArrayList<String>(Arrays.asList(foo.split(";")));
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

    private static void store_data(ArrayList<String> dt) 
    {
        String foo = String.join(";", dt);
        try (FileWriter fw = new FileWriter("data.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            out.println(foo);
        }
        catch (Exception e) {
            System.out.println("ERROR ON WRITING TO FILE!");
        }
    }

    private ArrayList<String> get_all_humans(ArrayList<String> dt)
    {
        for(int i = 8; i < dt.size(); i++)
            All.add(dt.get(i));
        return All;
    }

    private void search()
    {
        String foo = "";
        int k = 2;
        System.out.println("\nEnter a name to read geneological data.");
        foo = scan.nextLine();
        for(int i = 0; i < All.size(); i += 8)
        {
            if(foo.equals(All.get(i)))
            {
                for(int j = i + 2; j < (i + 6) && j < All.size(); j++)
                {
                    System.out.println(columnList.get(k) + " : " + All.get(j));
                    k++;
                }
            }
        }
    }

    private ArrayList<String> human()
    {
        ArrayList<String> child = new ArrayList<String>();
        String foo = "";
        System.out.println("Enter Y/y if you want to enter name manually.");
        if(foo.equals("Y") || foo.equals("y"))
        {
            System.out.println("Enter name.");
            child.add(scan.nextLine());
            System.out.println("Enter sex of the human.");
            foo = scan.nextLine();
            if(foo.equals("Male") || foo.equals("Female"))
                child.add(foo);
            else
            {
                child.clear();
                return child;
            }
        }
        else
        {
            if(rand.nextInt(11) > 6)
            {
                child.add(get_name("Female"));
                child.add("Female");
            }
            else
            {
                child.add(get_name("Male"));
                child.add("Male");
            }
        }
        System.out.println("If you want manually enter names of parents and siblings enter Y/y");
        if(foo.equals("Y") || foo.equals("y"))
        {
            for(int i = 2; i < 6; i++)
            {
                System.out.println("Enter " + columnList.get(i));
                child.add(scan.nextLine());        
            }
        }
        else
        {
            child.add("Secondborn");
            child.add(get_name("Female"));
            child.add(get_name("Male"));
            child.add("-");
        }
        child.add(get_random_DNA());
        System.out.println(child);
        return child;
    }

    public static void main(String[] args) {
        String foo = "";
        System.out.println("Program is running");
        OOP_First_Java humFirst = new OOP_First_Java();
        humFirst.get_all_humans(get_data());
        while(true)
        {
            System.out.println("Enter:\n1. To search in data.\n2. To generate new human.\n3. To read all data.\n4. To terminate the program.");
            foo = scan.nextLine();
            switch(foo)
            {
                case "1":
                    humFirst.search();
                    break;
                case "2":
                if(!humFirst.All.addAll(humFirst.human()))
                    System.out.println("ERROR ON ADDING TO ALL!");
                    break;
                case "3":
                    System.out.println(humFirst.All);
                    break;
                case "4":
                    break;
            }
            if(foo.equals("4"))
                break;
        }
        if(!columnList.addAll(humFirst.All))
            System.out.println("ERROR ON CREATING DATA TO SAVE.");
        else
            store_data(columnList);
        scan.close();
    }
}
