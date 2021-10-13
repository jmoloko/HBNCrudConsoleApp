package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.util.HibernateUtil;
import com.milk.consoleapp.model.dao.TeamDAO;
import com.milk.consoleapp.model.entity.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Jack Milk
 */
public class TeamDAOImpl implements TeamDAO {

    Transaction transaction = null;

    @Override
    public List<Team> getAll() {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT t FROM Team t LEFT JOIN FETCH t.developers d LEFT JOIN FETCH d.skills").list();
        }

    }

    @Override
    public Team getById(Integer id) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Team t LEFT JOIN FETCH t.developers d LEFT JOIN FETCH d.skills WHERE t.id = :id");
            query.setParameter("id", id);
            return (Team) query.getSingleResult();
        }

    }

    @Override
    public Team save(Team team) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.save(team);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return getById(team.getId());

    }

    @Override
    public Team update(Team team) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.update(team);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return getById(team.getId());

    }

    @Override
    public void deleteById(Integer id) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {

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
