package calculadora.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperacaoBasica extends UnicastRemoteObject implements IOperacaoBasica {
    protected OperacaoBasica() throws RemoteException {
        super();
    }

    @Override
    public Integer adicao(double n1, double n2) {
        return (int) (n1 + n2);
    }

    @Override
    public Integer subtracao(double n1, double n2) {
        return (int) (n1 - n2);
    }

    @Override
    public double divisao(double n1, double n2) {
        return (n2 == 0 ? 0 : n1 / n2);
    }
    @Override
    public Integer multiplicacao(double n1, double n2) {
        return (int) (n1 * n2);
    }
}
