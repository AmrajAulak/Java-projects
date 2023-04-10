// import java.util.Scanner; /* Required Import*/ 
// import java.util.HashMap; 
// import java.util.Map;
// import java.util.ArrayList;
// import java.util.List;
import java.util.*;


class Slot_Machine
{
    static final HashMap<String, Integer> frequency = new HashMap<String, Integer>();

    static {
    frequency.put("A", 2);
    frequency.put("B", 3);
    frequency.put("C", 4);
    frequency.put("D", 5);
    }

    public static float get_Deposit(Scanner scan) { 
        //Scanner scan = new Scanner(System.in); // Create Reader 
        System.out.print("Enter the amount to deposit: "); // Ask the user for something
        float deposit = scan.nextFloat(); // Read value from user
        //System.out.print(deposit); 
        return deposit;
      }

    public static float get_Bet(Scanner scan) { 
        System.out.print("Enter the amount to bet: "); 
        float bet = scan.nextFloat(); 
        return bet;
    }

    public static int get_Lines(Scanner scan) { 
        System.out.print("Enter te number of lines (maximum of 3): "); 
        int lines = scan.nextInt(); 
        return lines;
    }

    public static ArrayList<String> generate_symbols(){
        ArrayList<String> reel_symbols = new ArrayList<String>();

        for (Map.Entry<String, Integer> set :
             frequency.entrySet()) {
            for (int i = 0; i < set.getValue(); i++) {
                reel_symbols.add(set.getKey());
            }
        }

        return reel_symbols;

    }

    public static String[][] spin() { 
        int rows = 3;
        int columns = 3;
        String[][] displayed_columns = new String[3][3];

        for (int j = 0; j < columns; j++) {
            ArrayList<String> reel_symbols = generate_symbols();
            Random random = new Random();

            for (int i = 0; i < rows; i++) {
                int randomIndex = random.nextInt(reel_symbols.size());
                displayed_columns[i][j] = reel_symbols.get(randomIndex); 
                reel_symbols.remove(randomIndex);
                System.out.print(displayed_columns[i][j]);
            }
            System.out.print("\n");
        }
       
        return displayed_columns;


    }

    public static void print(String[][] displayed_columns){
        int rows = 3;
        int columns = 3;
        for (int i = 0; i < rows; i++) {
            if (i != 0){
                System.out.print("-------------" + "\n");
            }
            System.out.print("| ");
            for (int j = 0; j < columns; j++) {
                System.out.print(displayed_columns[i][j] + (" | "));
            }
            System.out.println();
        }
        System.out.println();

    }
    public static void main(String args[])
    {
        
        String[][] displayed_columns = spin();
        print(displayed_columns);

        Scanner scan = new Scanner(System.in); // Create Reader 
        float balance = get_Deposit(scan);
        float bet = get_Bet(scan);
        while (bet > balance){ 
            System.out.print("Insufficient balance! Please try again: " + "\n");
            bet = get_Bet(scan);
        }
        
        int lines = get_Lines(scan);

        while (lines < 1 || lines > 3){
            System.out.print("The number of lines must be 1, 2 or 3." + "\n");
            lines = get_Lines(scan);
        }


    }
}