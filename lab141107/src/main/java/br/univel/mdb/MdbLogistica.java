package br.univel.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.univel.classe.Entrega;
import br.univel.classe.Venda;

@MessageDriven(name = "MdbLogistica", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "Queue/QueuePedidos"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1") })
public class MdbLogistica implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(MdbLogistica.class.toString());

	// quem manda a msg é intermedia, um serviço que fica dentro do servidor
	public void onMessage(Message rcvMessage) {
		ObjectMessage obj = null;
		try {

			if(rcvMessage instanceof ObjectMessage){
				obj = (ObjectMessage) rcvMessage;
				Entrega entrega = (Entrega) obj.getObject();
				LOGGER.info("MBD - Logistica");
//				LOGGER.info(venda.toString());
//				// --------------- inserção do código------------------
//				//--------------- Inicia o processo da chamada de processos Vinculados a VendasTopic
//		        Context ctx = new InitialContext(System.getProperties());
//				TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
//				TopicConnection connection = factory.createTopicConnection();
//				TopicSession session = connection.createTopicSession(
//				false, Session.AUTO_ACKNOWLEDGE);
//				Topic topic = (Topic) ctx.lookup("topic/VendasTOPIC");
//				TopicPublisher publisher = session.createPublisher(topic);
//				rcvMessage = session.createObjectMessage();
//				publisher.publish(rcvMessage);

				// --------------- inserção do código------------------
				LOGGER.info("Iniciando despache...");
				LOGGER.info("Aguarde...");
				try {
					for (int i = 0; i < 3; i++) {
						LOGGER.info(".");
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				LOGGER.info(entrega.toString());
				LOGGER.info("Foi despachado.");

				// --- Fecha a sessão que o cliente inicio
//				session.close();
//				connection.close();
			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
/*		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Iniciado o processo de Logistica da venda -> " + msg.getText());

				//--------------- Inicia o processo da chamada de processos Vinculados a VendasTopic
		        Context ctx = new InitialContext(System.getProperties());
				TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
				TopicConnection connection = factory.createTopicConnection();
				TopicSession session = connection.createTopicSession(
				false, Session.AUTO_ACKNOWLEDGE);
				Topic topic = (Topic) ctx.lookup("topic/VendasTOPIC");
				TopicPublisher publisher = session.createPublisher(topic);
				msg = session.createTextMessage();
				publisher.publish(msg);

				// --------------- inserção do código------------------
				LOGGER.info("Iniciando despache...");
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				LOGGER.info("Foi despachado.");

				// --- Fecha a sessão que o cliente inicio
				session.close();
				connection.close();

			} else {
				LOGGER.warning("Erro no processo: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

}
