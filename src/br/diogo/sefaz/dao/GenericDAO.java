package br.diogo.sefaz.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.diogo.sefaz.dao.util.HibernateUtil;

public class GenericDAO<T> {
	private Object object;

    public GenericDAO(Object object) {
        this.object = object;
    }

    /**
     * Save an object in the Database
     *
     */
    public Object create() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            object = session.save(object);
            tx.commit();
            //session.close();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    System.out.println("Erro de acesso - > Transaction rollback not succesfull create: " + e1);
                }
            }
            throw e;
        }
        return object;
    }

    /**
     * Save if not exists or Update an object in the Database
     *
     * @param objectUpdate
     */
    public void saveOrUpdate(T objectUpdate) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(objectUpdate);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Erro de acesso - > Transaction rollback not succesfull save or update: " + e1);
                }
            }
            throw e;
        }
    }
    
    public void update(T objectUpdate) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.update(objectUpdate);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Erro de acesso - > Transaction rollback not succesfull update: " + e1);
                }
            }
            throw e;
        }
    }

    /**
     * Delete an object in the Database
     *
     * @param objectDelete
     */
    public void delete(T objectDelete) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.delete(objectDelete);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Erro de acesso - > Transaction rollback not succesfull delete: " + e1);
                }
            }
            throw e;
        }
    }

    /**
     * <b> Return all results </b>
     *
     * @return
     */
    public List<T> findALL() {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List<T> objectList = session.createCriteria(object.getClass()).list();
            tx.commit();
            if (!objectList.isEmpty()) {
                return objectList;
            }
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Erro de acesso - > Transaction rollback not succesfull findALL: " + e1);
                }
            }
            throw e;
        }
        return null;
    }

    /**
     * <b> Select row from object where field = fieldContent </b>
     *
     * @param <T>
     * @param field
     * @param fieldContent
     * @return
     */
    public <T> Object findByFieldEq(String field, T fieldContent) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();

            object = (Object) session.createCriteria(object.getClass())
                    .add(Restrictions.eq(field, fieldContent))
                    .uniqueResult();

            tx.commit();

            return object;
            
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Access Error - > Transaction rollback not succesfull findByFieldEq: " + e1);
                }
            }
            throw e;
        }
    }

    /**
     * <b> SELECT * FROM object WHERE field = value </b>
     *
     * @param field
     * @param value
     * @return
     */
    public List<T> findListByFieldEq(String field, Object value) {
    	Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<T> objects = null;
        try {
        	tx = session.beginTransaction();
        	
        	objects = session.createCriteria(object.getClass())
                    .add(Restrictions.eq(field, value)).list();
            
            tx.commit();

        } catch (RuntimeException e) {
        	if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Access Error - > Executing query: " + e);
                }
        	}
        }

        return objects;
    }

    /**
     *
     *
     * @param sql The query to be executed.
     * @param parameters The values of parameters to be passed.
     * @param keys The keys of values.
     * @return The number of updated registers.
     */
    public int executeQueryUpdate(String sql, Object[] parameters, String... keys) {
        int updated = 0;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            for (int i = 0; i < keys.length; i++) {
                query.setParameter(keys[i], parameters[i]);
            }
            updated = query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                	System.out.println("Access Error - > Transaction rollback not succesfull executeQueryUpdate: " + e1);
                }
            }
            throw e;
        }
        return updated;
    }
}
