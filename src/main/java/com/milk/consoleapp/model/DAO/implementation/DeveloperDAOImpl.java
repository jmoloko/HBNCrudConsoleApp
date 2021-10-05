package com.milk.consoleapp.model.DAO.implementation;

import com.milk.consoleapp.HibernateUtil;
import com.milk.consoleapp.model.DAO.DeveloperDAO;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Jack Milk
 */
public class DeveloperDAOImpl implements DeveloperDAO {

    private Transaction transaction = null;

    @Override
    public List<Developer> getAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Developer").list();
        }

    }

    @Override
    public Developer getById(Integer id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Developer.class, id);
        }

    }

    @Override
    public Developer save(Developer developer) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return getById(developer.getId());

    }

    @Override
    public Developer update(Developer developer) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return getById(developer.getId());

    }

    @Override
    public void deleteById(Integer id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.delete(getById(id));
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
