package com.milk.consoleapp.model.dao.impementation;

import com.milk.consoleapp.controller.TeamController;
import com.milk.consoleapp.model.dao.implementation.TeamDAOImpl;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import com.milk.consoleapp.model.entity.Team;
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
public class TeamControllerTest {

    @Mock
    private TeamDAOImpl teamDAO;

    @InjectMocks
    private TeamController controller;
    
    private Set<Developer> getDevelopers(){
        Set<Skill> skills = new HashSet<>();
        skills.add(new Skill(1, "Java"));
        skills.add(new Skill(1, "PHP"));
        Set<Developer> developers = new HashSet<>();
        developers.add(new Developer(1, "John", "Doe", skills));
        developers.add(new Developer(2, "Mike", "Bar", skills));
        return developers;
    }
    
    private Team getTeam() {
        return new Team(1, "TestTeam", getDevelopers());
    }
    
    @Test
    public void testGetAllTeams(){

        when(teamDAO.getAll()).thenReturn(Lists.newArrayList(getTeam()));
        assertEquals(1, controller.viewAllTeams().size());
        assertEquals("TestTeam", controller.viewAllTeams().get(0).getName());
        assertEquals(2, controller.viewAllTeams().get(0).getDevelopers().size());

        verify(teamDAO, atMost(5)).getAll();

    }

    @Test
    public void testGetTeamById() {

        when(teamDAO.getById(1)).thenReturn(getTeam());
        assertEquals(1, (int) controller.viewTeamByID(1).getId());
        assertEquals("TestTeam", controller.viewTeamByID(1).getName());
        assertEquals(2, controller.viewTeamByID(1).getDevelopers().size());

        verify(teamDAO, atMost(5)).getById(1);

    }

    @Test
    public void testSvaTeam() {

        when(teamDAO.save(getTeam())).thenReturn(getTeam());
        assertEquals(1, (int) controller.save(getTeam()).getId());
        assertEquals("TestTeam", controller.save(getTeam()).getName());
        assertEquals(2, controller.save(getTeam()).getDevelopers().size());

        verify(teamDAO, atMost(5)).save(getTeam());

    }


    @Test
    public void testUpdateTeamById() {

        when(teamDAO.update(getTeam())).thenReturn(new Team(1, "UpdatedTeam", getDevelopers()));
        assertEquals(1, (int) controller.updateTeamForId(getTeam()).getId());
        assertEquals("UpdatedTeam", controller.updateTeamForId(getTeam()).getName());
        assertEquals(2, controller.updateTeamForId(getTeam()).getDevelopers().size());

        verify(teamDAO, atMost(3)).update(getTeam());

    }

    @Test
    public void testDeleteTeam() {

        doNothing().when(teamDAO).deleteById(1);
        controller.deleteTeamById(1);
        verify(teamDAO).deleteById(1);

    }

}
