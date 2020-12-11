package calculadora.socket;

import calculadora.rmi.IOperacaoAvancada;
import calculadora.rmi.IOperacaoBasica;
import calculadora.socket.ClientHandler;

import java.io.*;
import java.net.*;
import java.rmi.Naming;

// Server class
public class Middleware {
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(5056);
        Socket s = null;

        IOperacaoBasica operacaoBasica;
        IOperacaoAvancada operacaoAvancada;
        try {
            // server is listening on port 5056
            operacaoBasica = (IOperacaoBasica)Naming.lookup("rmi://localhost:2020/OperacaoBasicaServer");
            operacaoAvancada = (IOperacaoAvancada) Naming.lookup("rmi://localhost:2021/OperacaoAvancadaServer");

            while (true) {
                s = null;

                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("Um cliente conectado ao middleware : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos, operacaoBasica, operacaoAvancada);

                // Invoking the start() method
                t.start();

            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            s.close();
            e.printStackTrace();
        }


        // running infinite loop for getting
        // client request

    }
}