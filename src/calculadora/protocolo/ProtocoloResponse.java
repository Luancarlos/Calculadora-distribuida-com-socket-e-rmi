package calculadora.protocolo;


public class ProtocoloResponse {
    private CODE code;
    private String mensagem;
    private String strinResponse;

    public ProtocoloResponse(CODE code, String mensagem) {
        this.code = code;
        this.mensagem = mensagem;
        this.strinResponse = code.name() +  ":"  +  code.mensagem(mensagem);
    }

    public ProtocoloResponse() {};

    public void converterStringValores(String strinResponse) {
        String[] valores = strinResponse.split(":");
        if (valores.length == 2) {
            this.code       = valores[0] == CODE.C10.name() ? CODE.C10 : CODE.C20;
            this.mensagem   = valores[1];
            this. strinResponse = strinResponse;
        }
    }

    public void conveterValoresEmString() {
        this.strinResponse = code.name() +  ":"  +  code.mensagem(mensagem);
    }

    public CODE getCode() {
        return code;
    }

    public void setCode(CODE code) {
        this.code = code;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStrinResponse() {
        return strinResponse;
    }

    public void setStrinResponse(String strinResponse) {
        this.strinResponse = strinResponse;
    }
}
