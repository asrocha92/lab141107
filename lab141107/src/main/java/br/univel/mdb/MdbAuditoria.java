package br.univel.mdb;

import br.univel.model.*;
import br.univel.persistence.HibernateUtil;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.hibernate.Session;

import br.univel.classe.Venda;

@MessageDriven(name = "MdbAuditoria", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/VendasTOPIC"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MdbAuditoria implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(MdbAuditoria.class.toString());

	public void onMessage(Message rcvMessage) {
		ObjectMessage obj = null;
		try {

			if(rcvMessage instanceof ObjectMessage){
				obj = (ObjectMessage) rcvMessage;
				Venda venda = (Venda) obj.getObject();
				LOGGER.info("MBD - Auditoria");
				LOGGER.info(venda.toString());

			// # 10
				Log l = new Log();
				l.setMdb("MdbAuditoria");
				l.setData(new Date().toString());
				l.setHora(new Date().toString());

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
	}

}
