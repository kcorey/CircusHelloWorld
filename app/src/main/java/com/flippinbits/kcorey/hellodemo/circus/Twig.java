package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.BuildConfig;

import java.util.Arrays;

public class Twig {
    public static void d (String ... args) {
        if (BuildConfig.DEBUG) {
            if (args.length == 0) {
                System.out.println("Twig.d called with null arguments.");
            } else if (args.length == 1) {
                System.out.println(args[0]);
            } else {
                System.out.println(String.format(args[0], Arrays.asList(args).subList(2, 99).toArray()));
            }
        }
    }
}



