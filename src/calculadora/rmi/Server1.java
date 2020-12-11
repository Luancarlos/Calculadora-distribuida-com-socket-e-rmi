package calculadora.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server1 {
    public static void main(String[] args) {
        try {
            int port = 2020;
            Registry registry = LocateRegistry.createRegistry(port);

            OperacaoBasica operacaoBasica = new OperacaoBasica();
            registry.rebind("OperacaoBasicaServer", operacaoBasica);
            System.out.println("Server rodando na porta: " + port);
        } catch(Exception e) {
            System.out.println("Server erro "+ e.getMessage());
        }
    }
}
