package com.example;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class Controllo extends Thread{
    Socket s;

    Controllo(Socket s){
        this.s = s;
    }
    
    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String stringNum = "";
            int intNum = 0;
            Random random = new Random();
            int numero = random.nextInt(100);
            System.out.println(numero);
            int tent = 0; 
            boolean ind = false;

            do{
                tent++;
                stringNum = in.readLine();
                intNum = Integer.parseInt(stringNum);
                if(intNum > 100 || intNum < 0)
                {
                    out.writeBytes("!" + "\n" ); 
                }
                
                else if(intNum == numero)
                {
                    ind = true;
                    out.writeBytes("=" + "\n" );
                    out.writeBytes(tent + "\n" );
                }
                  
                else if(intNum > numero)
                    out.writeBytes(">" + "\n" );
                else if(intNum < numero)
                    out.writeBytes("<" + "\n");
            }while(!ind);

        }catch (IOException e){ 
            e.printStackTrace();
        }
        System.out.println("Client disconnesso");
    }
}
