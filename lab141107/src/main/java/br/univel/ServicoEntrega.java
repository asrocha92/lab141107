package br.univel;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.univel.EJB.ProcessEntrega;
import br.univel.classe.Entrega;




@WebService
public class ServicoEntrega {

	@EJB
	ProcessEntrega processEntrega;

	@WebMethod(operationName="entregar")
	@WebResult(name="statusEntrega")
	public String doGet(@WebParam(name="codigo")int cod ,
						@WebParam(name="endereco")String endereco,
						@WebParam(name="valor")String valor,
						@WebParam(name="data")String data){
		System.out.println("Recebendo locitação do navegador");
        try {

            Entrega entrega = new Entrega();
            entrega.setCod_entrega(1);
            entrega.setEndereco(endereco);
            entrega.setValorTotalMercadoria(new BigDecimal(valor));
            entrega.setData_entregue("27/09/2016");
            entrega.setStatus(true);

            processEntrega.processarEntrega(entrega);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return "Foi despachada à Entrega.";
	}

}