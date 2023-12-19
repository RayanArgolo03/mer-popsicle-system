package services;

public final class PrintEnumsService {
    public static <T extends Enum<T>> void printEnums(T[] enums) {
        int i = 1;
        for (T t : enums) {
            System.out.println(i + " - " + t);
            i++;
        }
    }
}
