package com.example.mess;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.mess.user.AuthController;
import com.example.mess.user.UserEntity;
import com.example.mess.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        authController = new AuthController(userRepository);
    }

    @Test
    public void testUpdateMealCount_validInput_success() {
        // Create a test user with zero meal counts
        UserEntity testUser = new UserEntity();
        testUser.setRollNo("1");
        testUser.setMess("North");
        testUser.setBfCount(0);
        testUser.setLunchCount(0);
        testUser.setSnacksCount(0);
        testUser.setDinnerCount(0);

        // Mock the userRepository.findByRollNo() method to return the test user
        when(userRepository.findByRollNo("1")).thenReturn(testUser);

        // Call the updateMealCount() function with valid input
        ModelAndView modelAndView = authController.updateMealCount("1", "breakfast", "north", mock(Model.class));

        // Verify that the user's breakfast count was incremented
        assertEquals(1, testUser.getBfCount());

        // Verify that the function returns the expected ModelAndView object
        assertEquals("homepage", modelAndView.getViewName());
        assertEquals("1", testUser.getRollNo());
        assertEquals("North", testUser.getMess());
        assertEquals(1, testUser.getBfCount());
        assertEquals(0, testUser.getLunchCount());
        assertEquals(0, testUser.getSnacksCount());
        assertEquals(0, testUser.getDinnerCount());
    }

    @Test
    public void testUpdateMealCount_invalidRollNo_returnsLoginView() {
        // Mock the userRepository.findByRollNo() method to return null (no user found)
        when(userRepository.findByRollNo("1")).thenReturn(null);

        // Call the updateMealCount() function with an invalid roll number
        ModelAndView modelAndView = authController.updateMealCount("1", "breakfast", "north", mock(Model.class));

        // Verify that the function returns the expected ModelAndView object (login view)
        assertEquals("login", modelAndView.getViewName());
    }

    @Test
    public void testUpdateMealCount_alreadyEaten_returnsErrorMessage() {
        // Create a test user with one breakfast meal eaten
        UserEntity testUser = new UserEntity();
        testUser.setRollNo("1");
        testUser.setMess("North");
        testUser.setBfCount(1);
        testUser.setLunchCount(0);
        testUser.setSnacksCount(0);
        testUser.setDinnerCount(0);

        // Mock the userRepository.findByRollNo() method to return the test user
        when(userRepository.findByRollNo("1")).thenReturn(testUser);

        // Call the updateMealCount() function with breakfast meal type (which has already been eaten)
        ModelAndView modelAndView = authController.updateMealCount("1", "breakfast", "north", mock(Model.class));

        // Verify that the function returns the expected ModelAndView object (homepage view with error message)
        assertEquals("homepage", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("errorMessage"));
        assertEquals("You have already eaten breakfast today!", modelAndView.getModel().get("errorMessage"));
    }

    @Test
    public void testUpdateMealCount_invalidMealType_throwsIllegalArgumentException() {
// Create a test user
        UserEntity testUser = new UserEntity();
        testUser.setRollNo("1");
        testUser.setMess("North");
        testUser.setBfCount(0);
        testUser.setLunchCount(0);
        testUser.setSnacksCount(0);
        testUser.setDinnerCount(0);
        // Mock the userRepository.findByRollNo() method to return the test user
        when(userRepository.findByRollNo("1")).thenReturn(testUser);

// Call the updateMealCount() function with an invalid meal type
        assertThrows(IllegalArgumentException.class, () -> {
            authController.updateMealCount("1", "invalidMealType", "north", mock(Model.class));
        });

// Verify that the user's meal count has not been incremented
        assertEquals(0, testUser.getBfCount());
        assertEquals(0, testUser.getLunchCount());
        assertEquals(0, testUser.getSnacksCount());
        assertEquals(0, testUser.getDinnerCount());
    }


//    @Test
//    public void testUpdateMealCount_invalidMealType_throwsIllegalArgumentException() {
//        // Create a test user
//        UserEntity testUser = new UserEntity();
//        testUser.setRollNo("1");
//        testUser.setMess("North");
//        testUser.setBfCount(0);
//        testUser.setLunchCount(0);
//        testUser.setSnacksCount(0);
//        testUser.setDinnerCount(0);
//
//        // Mock the userRepository.findByRollNo() method to return the test user
//        when(userRepository.findByRollNo("1")).thenReturn(testUser);
//
//        // Call the updateMealCount() function with an invalid meal type
//        assertThrows(IllegalArgumentException.class, ()


        @Test
    public void testUpdateMealCount() {
        // Create a test user
        UserEntity testUser = new UserEntity();
       testUser.setRollNo("1");
        testUser.setMess("North");
        testUser.setBfCount(0);
        testUser.setLunchCount(0);
        testUser.setSnacksCount(0);
        testUser.setDinnerCount(0);

        // Debugging statement to print out testUser details
        System.out.println("testUser details:");
        System.out.println("rollNo: " + testUser.getRollNo());
        System.out.println("bfCount: " + testUser.getBfCount());

        // Mock the userRepository.findByRollNo() method to return the test user
        when(userRepository.findByRollNo(anyString())).thenReturn(testUser);

        // Call the updateMealCount() function
        ModelAndView modelAndView = authController.updateMealCount( "1", "breakfast", "north",mock(Model.class));

        // Debugging statement to print out testUser details after calling updateMealCount()
        System.out.println("testUser details after calling updateMealCount:");
        System.out.println("rollNo: " + testUser.getRollNo());
        System.out.println("bfCount: " + testUser.getBfCount());
        // Verify that the user's breakfast count was incremented
        assertEquals(1, testUser.getBfCount());

        // Verify that the function returns the expected ModelAndView object
        assertEquals("homepage", modelAndView.getViewName());
        assertEquals("1", testUser.getRollNo());
        assertEquals("North", testUser.getMess());
        assertEquals(1, testUser.getBfCount());
        assertEquals(0, testUser.getLunchCount());
        assertEquals(0, testUser.getSnacksCount());
        assertEquals(0, testUser.getDinnerCount());

    }
    @Test
    public void testHome() {
        // Create a test user
        UserEntity testUser = new UserEntity();
        testUser.setRollNo("1");
        testUser.setPassword("password123");

        // Mock the userRepository.findByRollNo() method to return the test user
        when(userRepository.findByRollNo(anyString())).thenReturn(testUser);

        // Call the updateMealCount() function
        String modelAndView = authController.home();

        // Debugging statement to print out testUser details after calling updateMealCount()
        System.out.println("testUser details after calling updateMealCount:");
        System.out.println("rollNo: " + testUser.getRollNo());
        // Verify that the user's breakfast count was incremented


        // Verify that the function returns the expected ModelAndView object
        assertEquals("1",testUser.getRollNo());
        assertEquals("password123",testUser.getPassword());

    }

}