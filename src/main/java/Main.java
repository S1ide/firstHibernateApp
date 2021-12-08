import Keys.Key;
import Keys.PurchaseKey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class Main {
    protected static SessionFactory factory;

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        factory = metadata.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
        CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
        Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
        Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);
        query.from(Purchase.class);
        List<Purchase> purchases = session.createQuery(query).getResultList();

        purchases.forEach(purchase -> {
            PurchaseKey purchaseKey = purchase.getKey();
            String courseName = purchaseKey.getCourseName();
            String studentName = purchaseKey.getStudentName();
            courseCriteriaQuery.where(builder.equal(courseRoot.get("name"), courseName));
            studentCriteriaQuery.where(builder.equal(studentRoot.get("name"), studentName));
            int courseId = session.createQuery(courseCriteriaQuery).getResultList().get(0).getId();
            int studentId = session.createQuery(studentCriteriaQuery).getResultList().get(0).getId();
            Key key= new Key(courseId, studentId);
            session.save(new LinkedPurchase(key));
        });
        transaction.commit();
        session.close();
        factory.close();
    }
}
