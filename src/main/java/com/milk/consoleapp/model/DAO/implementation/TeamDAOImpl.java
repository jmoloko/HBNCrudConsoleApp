package com.milk.consoleapp.model.DAO.implementation;

import com.milk.consoleapp.HibernateUtil;
import com.milk.consoleapp.model.DAO.TeamDAO;
import com.milk.consoleapp.model.entity.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Jack Milk
 */
public class TeamDAOImpl implements TeamDAO {

    Transaction transaction = null;

    @Override
    public List<Team> getAll() {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Team ").list();
        }

    }

    @Override
    public Team getById(Integer id) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Team.class, id);
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
