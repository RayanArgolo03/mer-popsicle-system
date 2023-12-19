package services;

import java.util.Scanner;

public final class ReadService {
    private final static Scanner sc = new Scanner(System.in);

    public static <T extends Enum<T>> T readEnum(String title, Class<T> enumClass) {

        T[] enums = enumClass.getEnumConstants();

        System.out.println(title);
        PrintEnumsService.printEnums(enums);
        System.out.print("Your choice: ");
        int choose = readInt();

        while (!validChoice(enums.length, choose)) {
            System.out.println("Invalid!");
            PrintEnumsService.printEnums(enums);
            System.out.print("Your choice: ");
            choose = readInt();
        }

        return enums[choose - 1];
    }

    public static int readInt() {
        return sc.nextInt();
    }

    public static String readString() {
        return sc.next();
    }

    private static boolean validChoice(int total, int choose) {
        return choose > 0 && choose <= total;
    }

}
