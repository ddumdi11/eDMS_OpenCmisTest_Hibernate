package de.cmis.test;

public class Tool {
	
	static org.hibernate.Session session = null;
	
	public void openSession () {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void closeSession () {
		session.close();
		HibernateUtil.shutdown();
	}

	public static void printAndLog(String messageString) {
		session.beginTransaction();
		System.out.println(messageString);
		String threadStringLong = Thread.currentThread().getStackTrace()[2].toString();
		String threadStringShort = threadStringLong.substring(13, threadStringLong.lastIndexOf("("));
		String namePackageAndTest = threadStringShort.substring(0, threadStringShort.lastIndexOf("."));
		String namePackage = namePackageAndTest.substring(0, namePackageAndTest.lastIndexOf("."));
		String nameTest = namePackageAndTest.substring(namePackageAndTest.lastIndexOf(".") + 1,
				namePackageAndTest.length());
		System.out.println("\tPackage Name: " + namePackage);
		System.out.println("\tTest Name: " + nameTest);
		
		//Add new TestMessage object
        TestMessageEntity message = new TestMessageEntity();
        message.setTestPackage(namePackage);
        message.setTestName(nameTest);
        message.setTestErgebnis(messageString);
        message.setTestDatum();		
		
		session.save(message);
        
        session.getTransaction().commit();

	}

}
