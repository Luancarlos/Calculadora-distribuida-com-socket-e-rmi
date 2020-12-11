package calculadora.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server2 {
    public static void main(String[] args) {
        try {
            int port = 2021;
            Registry registry = LocateRegistry.createRegistry(port);

            OperacaoAvancada operacaoAvancada = new OperacaoAvancada();
            registry.rebind("OperacaoAvancadaServer", operacaoAvancada);
            System.out.println("Server rodando na porta: " + port);
        } catch(Exception e) {
            System.out.println("Server erro "+ e.getMessage());
        }
    }
}
