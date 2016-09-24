package br.univel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import javax.swing.event.ListSelectionEvent;

import br.univel.EJB.ProcessVenda;
import br.univel.classe.Venda;

//Ele ira criar automaticamente
/*
@JMSDestinationDefinitions(
    value = {
        @JMSDestinationDefinition(
            name = "java:/queue/SERVLETVENDAQueue",
            interfaceName = "javax.jms.Queue",
            destinationName = "ServletVendaQueue"
        ),
        @JMSDestinationDefinition(
            name = "java:/topic/SERVLETVENDATopic",
            interfaceName = "javax.jms.Topic",
            destinationName = "ServletVendaTopic"
        )
    })
*/

@WebServlet("/Venda")
public class ServletVenda extends HttpServlet {

	private static final long serialVersionUID = -4465294623682762814L;

	@Inject
	private ProcessVenda processVenda;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write("<h1>Processar venda</h1>");
		try {
			Venda venda = new Venda();
			venda.setCod_venda(1);
			venda.setCpf_cliente("06548832512");
			List<String> itens = new ArrayList<String>();
			itens.add("Caderno");
			itens.add("Camisa");
			venda.setItens(itens);
			venda.setValor_total(new BigDecimal("200"));

			processVenda.processarVenda(venda);

		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
