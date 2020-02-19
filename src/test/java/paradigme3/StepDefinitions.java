package paradigme3;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import validatingforminput.WebController;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class StepDefinitions {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
    }

    @Given("The form is available and I inform my name and age")
    public void the_form_is_available_and_I_inform_my_and(io.cucumber.datatable.DataTable dataTable) throws Exception {

        for (Map<Object, Object> personFormList : dataTable.asMaps(String.class, String.class)) {

            MockHttpServletRequestBuilder createPerson = post("/")
                    .param("name", (String) personFormList.get("name"))
                    .param("age", (String) personFormList.get("age"));

            this.mockMvc.perform(createPerson)
                    .andExpect(model().hasNoErrors());
        }


    }


    @When("User try to submit the form with just name")
    public void user_try_to_submit_your_name_and_age(DataTable dataTable) throws Exception {

        for (Map<Object, Object> row : dataTable.asMaps(String.class, String.class)) {

            MockHttpServletRequestBuilder createPerson = post("/")
                    .param("name", (String) row.get("name"));
                   // .param("age", (String) row.get("age"));

            this.mockMvc.perform(createPerson)
                    .andExpect(model().hasErrors());


        }

    }

    @Then("User informs invalid name and age")
    public void user_informs_not_good_name_and_age(io.cucumber.datatable.DataTable dataTable) throws Exception {
        for (Map<Object, Object> row : dataTable.asMaps(String.class, String.class)) {

            MockHttpServletRequestBuilder createPerson = post("/")
                    .param("name", (String) row.get("name"))
                    .param("age", (String) row.get("age"));

            this.mockMvc.perform(createPerson)
                    .andExpect(model().hasErrors());


        }

    }

}