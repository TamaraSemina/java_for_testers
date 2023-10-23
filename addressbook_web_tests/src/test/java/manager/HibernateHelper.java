package manager;

import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import java.util.List;


public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        SessionFactory sessionFactory = new Configuration()
//                        .addAnnotatedClass(Book.class)
//                        .addAnnotatedClass(Author.class)
                        // Postgres SQL
                        .setProperty(AvailableSettings.JAKARTA_JBDC_URL, "jdbc:mysql://localhost/addressbook")
                        .setProperty(AvailableSettings.JAKARTA_JBDC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JBDC_PASSWORD, "")
                        .buildSessionFactory();
    }

    public List<GroupData> getGroupList() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupData", GroupData.class).list();
        })
    }
}
