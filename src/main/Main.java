package main;

import main.memoria.Cache;
import main.util.FileManager;

import java.util.ArrayList;

public class Main extends FileManager {
    public static void main(String[] args) {
        ArrayList<Integer> val = fileReader("bin_100.bin");
        Cache cache = new Cache(256, 4, 1, 0);
        System.out.println(cache.getOffset());
        System.out.println(cache.getIndice());
        System.out.println(cache.getTag());
        System.out.println(Integer.toBinaryString(32 >> cache.getIndice()));

    }
}
