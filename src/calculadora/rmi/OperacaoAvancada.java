package calculadora.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperacaoAvancada extends UnicastRemoteObject implements IOperacaoAvancada {

    protected OperacaoAvancada() throws RemoteException {
        super();
    }

    @Override
    public Integer potenciacao(double n1, double n2) throws RemoteException {
        return (int) Math.pow(n1, n2);
    }

    @Override
    public double radiacao(double n1) throws RemoteException {
        return Math.sqrt(n1);
    }

    @Override
    public double portecentagem(double n1, double n2) throws RemoteException {
        return ((n1 * n2) / 100);
    }
}
