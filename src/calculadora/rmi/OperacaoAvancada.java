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
    public Integer radiacao(double n1) throws RemoteException {
        return (int) Math.sqrt(n1);
    }
}
