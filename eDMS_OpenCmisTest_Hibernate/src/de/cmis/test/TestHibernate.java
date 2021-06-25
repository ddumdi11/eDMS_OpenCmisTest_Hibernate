package de.cmis.test;

 
public class TestHibernate {
     
    public static void main(String[] args) {
    	org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        //Add new TestMessage object
        TestMessageEntity message = new TestMessageEntity();
        message.setTestPackage("paket");
        message.setTestName("Name");
        message.setTestErgebnis("toll!");
        message.setTestDatum();
         
        session.save(message);
         
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}