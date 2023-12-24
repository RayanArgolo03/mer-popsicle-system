package services;

import java.util.Scanner;

public final class ReadService {
    private final static Scanner sc = new Scanner(System.in);

    public static <T extends Enum<T>> T readEnum(String title, Class<T> enumClass) {

        T[] enums = enumClass.getEnumConstants();

        System.out.println(title);
        PrintEnumsService.printEnums(enums);
        int choose = readInt("Your choice: ");

        while (!validChoice(enums.length, choose)) {
            System.out.println("Invalid!");
            PrintEnumsService.printEnums(enums);
            choose = readInt("Your choice: ");
        }

        return enums[choose - 1];
    }

    public static int readInt(String title) {
        System.out.print("Enter with " + title + "in number: ");
        return sc.nextInt();
    }

    public static String readString(String title) {
        System.out.print("Enter " + title + ": ");
        return sc.next();
    }

    private static boolean validChoice(int total, int choose) {
        return choose > 0 && choose <= total;
    }

}
