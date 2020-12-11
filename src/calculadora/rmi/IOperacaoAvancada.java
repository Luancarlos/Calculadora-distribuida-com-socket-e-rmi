package calculadora.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperacaoAvancada extends Remote {
    Integer potenciacao(double n1, double n2) throws RemoteException;
    Integer radiacao(double n1) throws RemoteException;
}
