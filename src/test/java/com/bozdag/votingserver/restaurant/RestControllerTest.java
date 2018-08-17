package com.bozdag.votingserver.restaurant;

import com.bozdag.votingserver.VotingServerApplication;
import com.bozdag.votingserver.domain.Restaurant;
import com.bozdag.votingserver.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotingServerApplication.class)
@WebAppConfiguration
public class RestControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<Restaurant> restaurants = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.restaurantRepository.deleteAllInBatch();
        restaurants.add(restaurantRepository.save(new Restaurant("Duble", "Doner", "Bilkent Ankara")));
        restaurants.add(restaurantRepository.save(new Restaurant("Il Forno", "Pizza", "Bilkent Ankara")));
    }

    @Test
    public void userNotFound() throws Exception{
        mockMvc.perform(
                post("/restaurants/aspava")
                    .content(json(new Restaurant(null, null, null)))
                    .contentType(contentType))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void readSingleRestaurant()throws Exception {
        mockMvc.perform(
                get("/restaurants/" + restaurants.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(restaurants.get(0).getId().intValue())));
    }

    @Test
    public void readRestaurants() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(restaurants.get(0).getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(restaurants.get(1).getId().intValue())));
    }

    @Test
    public void createRestaurant() throws Exception {
     String jsRestaurant = json(new Restaurant("NumNum", "Cafe", "Tepe Prime Ankara"));
     mockMvc.perform( post("/restaurants")
                        .contentType(contentType)
                        .content(jsRestaurant))
             .andExpect(status().isCreated());
    }

    // Converts object to json
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
