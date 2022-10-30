package main.util;

import java.util.Random;

public class Util {
    public static int log2(int input){
        return (int)(Math.log(input) / Math.log(2));
    }

    public static int getRandom(int upperBound){
        Random random = new Random();
        return random.nextInt(upperBound);
    }

    public static void printHelp(){
        System.out.println("TO DO");
    }

    public static Boolean isInteger(String input) {
        if (input == null) {
            return false;
        } else {
            int length = input.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (input.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            while (i < length) {
                char c = input.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
                i++;
            }
            return true;
        }
    }
}
