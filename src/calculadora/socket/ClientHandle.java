package calculadora.socket;

import calculadora.protocolo.ProtocoloRequest;
import calculadora.protocolo.ProtocoloResponse;
import calculadora.rmi.IOperacaoAvancada;
import calculadora.rmi.IOperacaoBasica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import static calculadora.protocolo.CODE.*;

// ClientHandler class
class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    final IOperacaoBasica operacaoBasica;
    final IOperacaoAvancada operacaoAvancada;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, IOperacaoBasica operacaoBasica, IOperacaoAvancada operacaoAvancada) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.operacaoBasica = operacaoBasica;
        this.operacaoAvancada = operacaoAvancada;
    }

    @Override
    public void run()
    {
        String received;

        while (true) {
            try {
                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                if (received != null) {
                    ProtocoloRequest protocolo = new ProtocoloRequest();
                    ProtocoloResponse protocoloResponse = new ProtocoloResponse();

                    protocolo.converterStringValue(received);

                    switch (protocolo.getOperador()) {
                        case "^" :
                            int po = operacaoAvancada.potenciacao(protocolo.getValor1(), protocolo.getValor2());

                            protocoloResponse.setCode(C10);
                            protocoloResponse.setMensagem(String.valueOf(po));
                            break;

                        case "%" :
                            double porcent = operacaoAvancada.portecentagem(protocolo.getValor1(), protocolo.getValor2());

                            protocoloResponse.setCode(C10);
                            protocoloResponse.setMensagem(String.valueOf(porcent));
                            break;

                        case "+" :
                            int soma = operacaoBasica.adicao(protocolo.getValor1(), protocolo.getValor2());

                            protocoloResponse.setCode(C10);
                            protocoloResponse.setMensagem(String.valueOf(soma));
                            break;

                        case "-" :
                            int sub = operacaoBasica.subtracao(protocolo.getValor1(), protocolo.getValor2());

                            protocoloResponse.setCode(C10);
                            protocoloResponse.setMensagem(String.valueOf(sub));
                            break;

                        case "*" :
                            int mult = operacaoBasica.multiplicacao(protocolo.getValor1(), protocolo.getValor2());

                            protocoloResponse.setCode(C10);
                            protocoloResponse.setMensagem(String.valueOf(mult));
                            break;

                        case "√" :
                            double raiz = operacaoAvancada.radiacao(protocolo.getValor2());

                            protocoloResponse.setCode(C10);
                            protocoloResponse.setMensagem(String.valueOf(raiz));
                            break;

                        case "/" :
                            if (protocolo.getValor2() == 0) {
                                protocoloResponse.setCode(C20);
                                protocoloResponse.setMensagem("Não é um numero");
                            } else {
                                double div = operacaoBasica.divisao(protocolo.getValor1(), protocolo.getValor2());
                                protocoloResponse.setCode(C10);
                                protocoloResponse.setMensagem(String.valueOf(div));
                            }

                            break;

                        default:
                            protocoloResponse.setCode(C20);
                            protocoloResponse.setMensagem(protocoloResponse.getCode().mensagem(null));
                            break;
                    }

                    protocoloResponse.conveterValoresEmString();
                    dos.writeUTF(protocoloResponse.getStrinResponse());

                } else {
                    break;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}