package com.proyect.CompilAir.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.proyect.CompilAir.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.proyect.CompilAir.models.ERole.USER;

import com.proyect.CompilAir.repositories.IUserRepository;

class UserServiceTest {

private UserService userService;
    private IUserRepository iUserRepository;

    @BeforeEach
    public void setUp() {
        iUserRepository = mock(IUserRepository.class);
        userService = new UserService(iUserRepository);
    }

    @Test
    public void test_Get_All_Users() {

        List<User> mockProjects = new ArrayList<>();
        mockProjects.add(new User(1L, USER, "password1","user1@example.com", "Jacky"));
        mockProjects.add(new User(2L, USER, "password1","user2@example.com", "Fran"));
       
        when(iUserRepository.findAll()).thenReturn(mockProjects);

        ArrayList<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Jacky", result.get(0).getUsername());
        assertEquals("Fran", result.get(1).getUsername());
      

        verify(iUserRepository, times(1)).findAll();
    }

    @Test
    public void test_Get_User_By_Id() {

        User mockProject = new User(1L, USER, "password1","user1@example.com", "Jacky");
        Long userId = 1L;

        when(iUserRepository.findById(userId)).thenReturn(Optional.of(mockProject));

        Optional<User> result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals("Jacky", result.get().getUsername());

        verify(iUserRepository, times(1)).findById(userId);
    }
    @Test
    public void test_Create_User() {

        User newUser = new User(1L, USER, "password1","user1@example.com", "Jacky");

        when(iUserRepository.save(newUser)).thenReturn(newUser);

        User result = userService.createUser(newUser);

        assertNotNull(result);
        assertEquals("Jacky", result.getUsername());

        verify(iUserRepository, times(1)).save(newUser);
    }
    @Test
    public void test_Update_User() {

        User user = new User(1L, USER, "password1","user1@example.com", "Jacky");

        userService.updateUser(user);

        verify(iUserRepository, times(1)).save(user);
    }

    @Test
    public void test_Delete_User_Success() {

        Long userId = 1L;

        String result = userService.deleteUser(userId);

        verify(iUserRepository, times(1)).deleteById(userId);

        assertEquals("User has been deleted", result);
    }

    @Test
    public void test_Delete_if_Users_Not_Found() {

        Long userId = 1L;
        doThrow(new RuntimeException("User not found")).when(iUserRepository).deleteById(userId);

        String result = userService.deleteUser(userId);

        verify(iUserRepository, times(1)).deleteById(userId);

        assertEquals("User not found", result);
    }
}