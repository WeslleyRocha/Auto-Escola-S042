package br.com.senai.s042.autoescolas042.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encriptador {

    public static void main (String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = encoder.encode("admin");
        System.out.println(hash);
    }
}
