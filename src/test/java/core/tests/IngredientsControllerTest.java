package core.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
    mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void allIngredientsCanBeRetrived() throws Exception {
        mockMvc.perform(get("/ingredient/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"cucumber\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"name\": \"Garlic\"\n" +
                        "  }\n" +
                        "]"));
    }
}