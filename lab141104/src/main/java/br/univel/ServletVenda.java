package br.univel;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Definition of the two JMS destinations used by the quickstart
 * (one queue and one topic).
 */
//Ele ira criar automaticamente
@JMSDestinationDefinitions(
    value = {
        @JMSDestinationDefinition(
            name = "java:/queue/SERVLETVENDAQueue", 
            interfaceName = "javax.jms.Queue",
            destinationName = "ServletVendaQueue"
        ),
        @JMSDestinationDefinition(
            name = "java:/topic/SERVLETVENDAQueue",
            interfaceName = "javax.jms.Topic",
            destinationName = "ServletVendaTopic"
        )
    })

@WebServlet("/Venda")
public class ServletVenda extends HttpServlet{
	
	private static final long serialVersionUID = -4465294623682762814L;

	private static final int MSG_COUNT = 5;

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/queue/SERVLETVENDAQueue")
	private Queue queue;

	@Resource(lookup = "java:/topic/SERVLETVENDATopic")
	private Topic topic;

	protected void getVenda(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Primeira fase com <strong>JMS 2.0</strong> e <strong>EJB 3.2 Message-Driven Bean</strong> utilizando WildFly 9.</h1>");
        try {
            boolean useTopic = req.getParameterMap().keySet().contains("topic");
            final Destination destination = useTopic ? topic : queue;

            out.write("<p>Processo <em>" + destination + "</em></p>");
            out.write("<h2>As mensagem seram enviadas ao destino:</h2>");
            for (int i = 0; i < MSG_COUNT; i++) {
                String text = "This is message " + (i + 1);
                context.createProducer().send(destination, text);
                out.write("Mensagem (" + i + "): " + text + "</br>");
            }
            out.write("<p><i>As mensagens seram apresentadas no console</i></p>");
        } finally {
            if (out != null) {
                out.close();
            }
        }

	}

}
