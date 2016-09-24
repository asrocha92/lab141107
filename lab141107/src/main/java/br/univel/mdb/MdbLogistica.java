package br.univel.mdb;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.hibernate.Session;

import br.univel.classe.Entrega;
import br.univel.model.Log;
import br.univel.persistence.HibernateUtil;

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

				// # 10
				Log l = new Log();
				l.setMdb("MDBLogistica");
				l.setData(new Date().toString());
				l.setHora(new Date().toString());

				LOGGER.info("MBD - Logistica");

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


				Session session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        session.persist(l);
		        session.getTransaction().commit();
		        session.close();

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
