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
        Socket s = new Socket("localhost",3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        String n = "";
        String ris = "";
        String tentativi = "";

        Scanner sc  = new Scanner(System.in);
        do{
            System.out.println("Inserire numero");
                n = sc.nextLine();
                out.writeBytes(n + "\n");
                ris=in.readLine();
                
                if(ris.equals("!"))
                {
                System.out.println("Inserire un numero da 0 a 100");
                }
                else if(ris.equals("<"))
                    System.out.println("Numero troppo piccolo");
                else if(ris.equals(">"))
                    System.out.println("Numero troppo grande");
                else{
                tentativi = in.readLine();
                System.out.println("HAI INDOVINATO in " + tentativi + " tentativi.");
                }
            
            
        }while(!ris.equals("="));
        s.close();
    }
}