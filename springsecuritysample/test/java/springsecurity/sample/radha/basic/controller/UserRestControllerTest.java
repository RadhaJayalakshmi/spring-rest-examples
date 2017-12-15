package springsecurity.sample.radha.basic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springsecurity.sample.radha.basic.TestContext;
import springsecurity.sample.radha.basic.WebAppContext;
import springsecurity.sample.radha.basic.model.User;
import springsecurity.sample.radha.basic.service.UserService;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestController userRestController;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    private MediaType getApplicationJsonUTF8Type() {
        return new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8")
        );
    }


    @Test
    public void getAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(new User(12L, "Tom Hanks" , 45 , 12000), new User(13L, "Bruce Willis" , 65 , 45000)));

        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(getApplicationJsonUTF8Type())) // Using custom MediaType with Charset definition
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(12)))
                .andExpect(jsonPath("$[0].name", is("Tom Hanks")))
                .andExpect(jsonPath("$[0].age", is(45)))
                .andExpect(jsonPath("$[0].salary", is(12000.0)))
                .andExpect(jsonPath("$[1].id", is(13)))
                .andExpect(jsonPath("$[1].name", is("Bruce Willis")))
                .andExpect(jsonPath("$[1].age", is(65)))
                .andExpect(jsonPath("$[1].salary", is(45000.0)));

        verify(userService, times(1)).findAll();
    }

    @org.junit.Test
    public void getUser() throws Exception {
        when(userService.find(12L)).thenReturn(new User(12L, "Tom Hanks" , 45 , 12000));

        mockMvc.perform(get("/user/12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(getApplicationJsonUTF8Type())) // Using custom MediaType with Charset definition
                .andExpect(jsonPath("$.id", is(12)))
                .andExpect(jsonPath("$.name", is("Tom Hanks")))
                .andExpect(jsonPath("$.age", is(45)))
                .andExpect(jsonPath("$.salary", is(12000.0)));

        verify(userService, times(2)).find(12);
    }

    @org.junit.Test
    public void createUser() throws Exception {

        ObjectMapper map = new ObjectMapper();
        byte[] payload = map.writeValueAsString(new User(12L, "Tom Hanks" , 45 , 12000)).getBytes();

        mockMvc.perform(post("/user/").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isCreated());

        verify(userService, times(1)).save(new User(12L, "Tom Hanks" , 45 , 12000));
    }

    @Test
    public void updateUser() throws Exception {

        when(userService.find(12L)).thenReturn(new User(12L, "Tom Hanks" , 45 , 12000));
        ObjectMapper map = new ObjectMapper();
        String payload = map.writeValueAsString(new User(12L, "Tom Hanks" , 45 , 34000));

        mockMvc.perform(put("/user/12").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isOk())
                .andExpect(content().contentType(getApplicationJsonUTF8Type())) // Using custom MediaType with Charset definition
                .andExpect(jsonPath("$.id", is(12)))
                .andExpect(jsonPath("$.name", is("Tom Hanks")))
                .andExpect(jsonPath("$.age", is(45)))
                .andExpect(jsonPath("$.salary", is(34000.0)));

        verify(userService, times(1)).update(new User(12L, "Tom Hanks" , 45 , 12000));
    }

    @org.junit.Test
    public void deleteUser() throws Exception {

        when(userService.find(12L)).thenReturn(new User(12L, "Tom Hanks" , 45 , 12000));
        mockMvc.perform(delete("/user/12"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).delete(12);
    }

}