package br.univel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.EJB.ProcessEntrega;
import br.univel.classe.Entrega;

/**
 * Definition of the two JMS destinations used by the quickstart
 * (one queue and one topic).
 */
//Ele ira criar automaticamente
/*
 * @JMSDestinationDefinitions(
    value = {
        @JMSDestinationDefinition(
            name = "java:/queue/SERVLETENTREGAQueue",
            interfaceName = "javax.jms.Queue",
            destinationName = "ServletEtradaQueue"
        ),
        @JMSDestinationDefinition(
            name = "java:/topic/SERVLETENTREGATopic",
            interfaceName = "javax.jms.Topic",
            destinationName = "ServletEntradaTopic"
        )
    })
*/

@WebServlet("/Entrega")
public class ServletEntrega extends HttpServlet{

	private static final long serialVersionUID = -6046928927589927910L;

	@EJB
	private ProcessEntrega processEntrega;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Processo de Entrega</h1>");
        try {

            Entrega entrega = new Entrega();
            entrega.setCod_entrega(1);
            entrega.setEndereco("Rua cassia, nº:815");
            entrega.setValorTotalMercadoria(new BigDecimal(200));
            entrega.setData_entregue("27/09/2016");
            entrega.setStatus(true);

            processEntrega.processarEntrega(entrega);
        } finally {
            if (out != null) {
                out.close();
            }
        }
	}

}
