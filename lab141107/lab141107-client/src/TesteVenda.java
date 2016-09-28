import br.univel.ServicoVenda;

public class TesteVenda {
	public static void main(String[] args) {
		ServicoVenda service = new ServicoVenda();
		service.doGet(1, "09784562340");
		System.out.println(service);
	}
}
