package calculadora.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperacaoBasica extends Remote {
    Integer adicao(double n1, double n2) throws RemoteException;
    Integer subtracao(double n1, double n2) throws RemoteException;
    Integer divisao(double n1, double n2) throws RemoteException;
    Integer multiplicacao(double n1, double n2) throws RemoteException;
}
