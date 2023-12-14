import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void calCGPA(double earnedCredit, double cGPA, double currSemCredit, double gpa){
        System.out.println("Your CGPA is: " + (earnedCredit * cGPA + currSemCredit * gpa) / (earnedCredit + currSemCredit));
    }

    public static double calGPA(List<SimpleEntry<Integer, String>> inputs, int credit){
        double currTotalPoints = 0;
        Map<String, Double> gpaScale = Map.ofEntries(
                Map.entry("A", 4.0),
                Map.entry("A-", 3.7),

                Map.entry("B+", 3.4),
                Map.entry("B", 3.0),
                Map.entry("B-", 2.7),
                Map.entry("C+", 2.4),
                Map.entry("C", 2.0),
                Map.entry("C-", 1.7),
                Map.entry("D+", 1.4),
                Map.entry("D", 1.0),
                Map.entry("D-", 0.7),
                Map.entry("F", 0.0),
                Map.entry("WF", 0.0)
        );

        for (SimpleEntry<Integer, String> entry : inputs) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            currTotalPoints += gpaScale.get(value) * key;
        }

        double currGPA = currTotalPoints/credit;
        System.out.println("Your current semester GPA is: " + currGPA);
        return currGPA;
    }

    public static int calCredit(List<SimpleEntry<Integer, String>> inputs){
        int c = 0;
        for (SimpleEntry<Integer, String> entry : inputs) {
            Integer key = entry.getKey();
            c += key;
        }
        return c;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Thank you for using BYU-Hawaii cGPA Calculator." +
                "\nEnter the credit you have earned (exclude current semester): ");
        double earnedCredit = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter your current cGPA: ");
        double cgpa = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter each of current semester's courses credit and grade use following format");
        System.out.println("3 A");
        System.out.println("Enter '-1' when you finish");

        List<SimpleEntry<Integer, String>> inputs = new ArrayList<>();
        //inputs.add(new SimpleEntry<>(3, 'A'));

        int classNum = 1;
        while(true){
            System.out.print("Class " + classNum +": ");
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("-1")){
                break;
            } else if (input.length < 3){
                System.out.println("Missing Information. Please double check input");
                continue;
            }

            try{
                int classCredit = Integer.parseInt(input[0]);
                inputs.add(new SimpleEntry<>(classCredit, input[1].toUpperCase()));
            } catch (NumberFormatException e) {
                System.out.println("Missing Class Credit");
            }

            classNum += 1;
        }

        int currentSemCredit = calCredit(inputs);
        double gpa = calGPA(inputs, currentSemCredit);
        calCGPA(earnedCredit, cgpa, currentSemCredit,gpa);
    }
}