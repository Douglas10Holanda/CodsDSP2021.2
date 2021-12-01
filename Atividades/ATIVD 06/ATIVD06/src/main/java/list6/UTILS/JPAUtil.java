package list6.UTILS;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static EntityManager getEntityManager(){
        entityManager = threadLocal.get();
        if(entityManager == null){
            entityManager = entityManagerFactory.createEntityManager();
            threadLocal.set(entityManager);
        }
        return entityManager;
    }

    public static void closeEntityManager(){
        entityManager = threadLocal.get();
        if (entityManager != null) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            if (entityTransaction.isActive()) {
                entityTransaction.commit();
            }
            entityManager.close();
            threadLocal.set(null);
        }
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        if (entityTransaction.isActive()) {
            entityTransaction.commit();
        }
    }

    public static void rollback() {
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        if (entityTransaction.isActive()) {
            entityTransaction.rollback();
        }
    }
}
