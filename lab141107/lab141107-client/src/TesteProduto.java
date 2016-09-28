
import br.univel.ServicoProduto;
import br.univel.model.produto;

public class TesteProduto {

	public static void main(String[] args) {
		ServicoProduto produto = new ServicoProduto();
		try {
			produto p = produto.create("4654654654", "Refrigerante cola cola", 9);
			System.out.println(p.toString());

			p = null;
			p = produto.update(new Long(1), "45454545", "Refrigerante cola cola 0 acuçar", 10);
			System.out.println(p.toString());

			p = null;
			p = produto.read(new Long(1));
			System.err.println(p.toString());

			String retorno = produto.delete(new Long(1));
			System.out.println(retorno);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
