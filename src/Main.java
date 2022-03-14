import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int choice, count = 0, calories, carbs, meatType, cookingTemp, organicType, total;
        String name;
        boolean organic;

        PaleoFood[] journal = new PaleoFood[100];

        Scanner keyboard = new Scanner(System.in);

        File binaryFile = new File("FoodJournal.dat");

        System.out.println("~~~~~~~~~~~Welcome to the Paleo Food Journal~~~~~~~~~~~");

        if (binaryFile.exists() && binaryFile.length() > 1L)
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                journal = (PaleoFood[]) fileReader.readObject();
                System.out.println("\n~~~Food Recorded in Journal~~~");
                while (journal[count] != null)
                    System.out.println(journal[count++]);
                total = totalCalories(journal, count);
                System.out.println("\nTotal calories consumed = " + total);
                System.out.println("Average calories consumed = " + total / count);
                System.out.println("Food with most calories = " + foodWithMostCalories(journal, count));
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else
            System.out.println("\n[No food eaten. You must be hungry.]");

        do {
            System.out.println("\n******* Options Menu *******");
            System.out.println("Enter (1) to record a meat");
            System.out.println("Enter (2) to record a produce");
            System.out.println("Enter (3) to quit");
            choice = keyboard.nextInt();

            switch (choice)
            {
                case 1:
                    try {
                        keyboard.nextLine();
                        System.out.print("What is the name of the meat eaten? ");
                        name = keyboard.nextLine();
                        System.out.print("How many calories was it? ");
                        calories = keyboard.nextInt();
                        System.out.print("Enter (1) for animal or (2) for seafood: ");
                        meatType = keyboard.nextInt();
                        if (meatType < 1 || meatType > 2)
                            throw new MysteryMeatException();
                        System.out.print("Enter the cooking temperature: ");
                        cookingTemp = keyboard.nextInt();

                        journal[count++] = new Meat(name, calories, meatType, cookingTemp);

                    }
                    catch (MysteryMeatException e)
                    {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    keyboard.nextLine();
                    System.out.print("What is the name of the produce eaten? ");
                    name = keyboard.nextLine();
                    System.out.print("How many calories was it? ");
                    calories = keyboard.nextInt();
                    System.out.print("How many carbohydrates? ");
                    carbs = keyboard.nextInt();
                    System.out.print("Enter (1) for organic or (2) for non-organic: ");
                    organicType = keyboard.nextInt();

                    organic = (organicType == 1);

                    journal[count++] = new Produce(name, calories, carbs, organic);

                    break;

                case 3:
                    System.out.println("\n~~~Food Recorded in Journal~~~");

                    while (journal[count] != null)
                        System.out.println(journal[count++]);
                    total = totalCalories(journal, count);

                    System.out.println("\nTotal calories consumed = " + total);
                    System.out.println("Average calories consumed = " + total / count);
                    System.out.println("Food with most calories = " + foodWithMostCalories(journal, count));

                    System.out.println("Eat healthy and enjoy your weekend!");

                    break;
            }

        } while (choice != 3);

        keyboard.close();

        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(journal);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static int totalCalories(PaleoFood[] journal, int count)
    {
        int total = 0;

        for (int i = 0; i < count; i++) {
            total += journal[i].getCalories();
        }

        return total;
    }

    public static PaleoFood foodWithMostCalories(PaleoFood[] journal, int count)
    {
        int max = Integer.MIN_VALUE;
        PaleoFood maxFood = null;

        for (int i = 0; i < count; i++) {
            if (journal[i].getCalories() > max)
            {
                max = journal[i].getCalories();
                maxFood = journal[i];
            }

        }

        return maxFood;

    }
}
