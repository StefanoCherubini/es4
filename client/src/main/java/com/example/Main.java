package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException{
        Scanner sc = new Scanner(System.in);
        boolean playAgain;

        do {
            // Avvio della connessione per una nuova partita
            Socket s = new Socket("localhost", 3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String n = "";
            String ris = "";
            String tentativi = "";


            do {
                System.out.println("Inserire numero");
                n = sc.nextLine();
                out.writeBytes(n + "\n");
                ris = in.readLine();

                if (ris.equals("!")) {
                    System.out.println("Inserire un numero da 0 a 100");
                } else if (ris.equals("<")) {
                    System.out.println("Numero troppo piccolo");
                } else if (ris.equals(">")) {
                    System.out.println("Numero troppo grande");
                } else {
                    tentativi = in.readLine();
                    System.out.println("HAI INDOVINATO in " + tentativi + " tentativi.");
                }

            } while (!ris.equals("="));

            // Chiusura della connessione per la partita corrente
            s.close();

            // Chiedere se si vuole giocare di nuovo
            System.out.println("Vuoi giocare un'altra partita? (s/n)");
            String response = sc.nextLine().trim().toLowerCase();
            playAgain = response.equals("s");

        } while (playAgain);

        System.out.println("Grazie per aver giocato!");
        sc.close();
    }
}
