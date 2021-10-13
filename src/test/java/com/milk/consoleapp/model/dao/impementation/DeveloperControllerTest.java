package com.milk.consoleapp.model.dao.impementation;

import com.milk.consoleapp.controller.DeveloperController;
import com.milk.consoleapp.model.dao.implementation.DeveloperDAOImpl;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;


/**
 * @author Jack Milk
 */

@RunWith(MockitoJUnitRunner.class)
public class DeveloperControllerTest {

    @Mock
    private DeveloperDAOImpl developerDAO;

    @InjectMocks
    private DeveloperController controller;

    private Developer getDeveloper() {
        return new Developer(1, "John", "Doe", getSkills());
    }
    
    private Set<Skill> getSkills() {
        Set<Skill> skills = new HashSet<>();
        skills.add(new Skill(1, "Java"));
        skills.add(new Skill(1, "PHP"));
        return skills;
    }

    @Test
    public void testGetAllDeveloper() {

        when(developerDAO.getAll()).thenReturn(Lists.newArrayList(getDeveloper()));
        assertEquals(1, controller.viewAllDevelopers().size());
        assertEquals("John", controller.viewAllDevelopers().get(0).getFirstName());
        assertEquals("Doe", controller.viewAllDevelopers().get(0).getLastName());
        assertEquals(2, controller.viewAllDevelopers().get(0).getSkills().size());

        verify(developerDAO, times(4)).getAll();
    }

    @Test
    public void testGetDeveloperById() {

        when(developerDAO.getById(1)).thenReturn(getDeveloper());
        assertEquals(1, (int) controller.viewDeveloperByID(1).getId());
        assertEquals("John", controller.viewDeveloperByID(1).getFirstName());
        assertEquals("Doe", controller.viewDeveloperByID(1).getLastName());
        assertEquals(2, controller.viewDeveloperByID(1).getSkills().size());

        verify(developerDAO, times(4)).getById(1);
    }

    @Test
    public void testSaveDeveloper() {

        when(developerDAO.save(getDeveloper())).thenReturn(getDeveloper());
        assertEquals(1, (int) controller.save(getDeveloper()).getId());
        assertEquals("John", controller.save(getDeveloper()).getFirstName());
        assertEquals("Doe", controller.save(getDeveloper()).getLastName());
        assertEquals(2, controller.save(getDeveloper()).getSkills().size());

        verify(developerDAO, times(4)).save(getDeveloper());
    }

    @Test
    public void testUpdateDeveloper(){

        when(developerDAO.update(getDeveloper())).thenReturn(new Developer(1, "Jack", "Milk", getSkills()));
        assertEquals(1, (int) controller.updateDeveloperForId(getDeveloper()).getId());
        assertEquals("Jack", controller.updateDeveloperForId(getDeveloper()).getFirstName());
        assertEquals("Milk", controller.updateDeveloperForId(getDeveloper()).getLastName());
        assertEquals(2, controller.updateDeveloperForId(getDeveloper()).getSkills().size());

        verify(developerDAO, atMost(5)).update(getDeveloper());

    }

    @Test
    public void testDeleteDeveloper() {

        doNothing().when(developerDAO).deleteById(1);
        controller.deleteDeveloperById(1);
        verify(developerDAO).deleteById(1);

    }

}
