import br.univel.ServicoEntrega;

public class TesteEntrada {
	public static void main(String[] args) {
		ServicoEntrega servico = new ServicoEntrega();
		servico.doGet(1, "rua cassi, n:815", "200", "05/10/216");
		System.out.println(servico.toString());
	}
}
