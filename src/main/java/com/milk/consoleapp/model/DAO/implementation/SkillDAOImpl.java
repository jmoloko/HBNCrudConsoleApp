package com.milk.consoleapp.model.DAO.implementation;

import com.milk.consoleapp.HibernateUtil;
import com.milk.consoleapp.model.DAO.SkillDAO;
import com.milk.consoleapp.model.entity.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Jack Milk
 */
public class SkillDAOImpl implements SkillDAO {

    private Transaction transaction = null;

    @Override
    public List<Skill> getAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Skill").list();
        }

    }

    @Override
    public Skill getById(Integer id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Skill.class, id);
        }

    }

    @Override
    public Skill save(Skill skill) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return getById(skill.getId());
    }

    @Override
    public Skill update(Skill skill) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =  session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return getById(skill.getId());

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
