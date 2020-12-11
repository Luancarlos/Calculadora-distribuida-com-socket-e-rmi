package calculadora.protocolo;

public enum CODE {
    C10 {
        @Override
        public String mensagem(String msg) {
            return msg == null ? "Operação realizada com sucesso" : msg;
        }

    }, // code sucesso
    C20 {
        @Override
        public String mensagem(String msg) {
            return msg == null ? "Ocorreu um erro ao efetuar a operação" : msg;
        }
    }; // code erro

    public abstract String mensagem(String msg);
}
