package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.util.HibernateUtil;
import com.milk.consoleapp.model.dao.DeveloperDAO;
import com.milk.consoleapp.model.entity.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Jack Milk
 */
public class DeveloperDAOImpl implements DeveloperDAO {

    @Override
    public List<Developer> getAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT d FROM Developer d LEFT JOIN FETCH d.skills").list();
        }

    }

    @Override
    public Developer getById(Integer id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Developer d LEFT JOIN FETCH d.skills WHERE d.id = :id");
            query.setParameter("id", id);
            return (Developer) query.getSingleResult();
        }

    }

    @Override
    public Developer save(Developer developer) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction =  session.beginTransaction();
            session.save(developer);
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return getById(developer.getId());

    }

    @Override
    public Developer update(Developer developer) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction =  session.beginTransaction();
            session.update(developer);
            transaction.commit();

        }catch (Exception e) {

            e.printStackTrace();
        }

        return getById(developer.getId());

    }

    @Override
    public void deleteById(Integer id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction =  session.beginTransaction();
            session.delete(getById(id));
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
