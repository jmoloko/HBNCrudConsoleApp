package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.util.HibernateUtil;
import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.entity.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Jack Milk
 */
public class SkillDAOImpl implements SkillDAO {

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

            Transaction transaction =  session.beginTransaction();
            session.save(skill);
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return getById(skill.getId());
    }

    @Override
    public Skill update(Skill skill) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaction =  session.beginTransaction();
            session.update(skill);
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return getById(skill.getId());

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
