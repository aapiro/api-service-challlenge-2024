package bdd.steps;

import bdd.TestConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class)
public class PriceQuerySteps {

    private final RestTemplate restTemplate;

    private ResponseEntity<String> responseEntity;

    public PriceQuerySteps(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Given("I query the price for product {int} from brand {int} at {string}")
    public void iQueryThePriceForProductFromBrandAt(int productId, int brandId, String applicationDate) {
        String url = String.format("http://localhost:8080/api-business-price/api/v1/brand-id/%d/product-id/%d?applicationDate=%s", brandId, productId, applicationDate);

        HttpHeaders headers = new HttpHeaders();
        headers.set("applicationDate", applicationDate);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    @Then("I receive a status {int}")
    public void iReceiveAStatus(int expectedStatus) {
        assertEquals(expectedStatus, responseEntity.getStatusCodeValue());
    }

    @Then("the start price date is {string}")
    public void theStartPriceDateIs(String expectedStartDate) throws JSONException {
        // Obtén el valor de la fecha de inicio desde la respuesta y compáralo con el valor esperado
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        String startPriceDate = jsonObject.getString("startPriceDate");
        assertEquals(expectedStartDate, startPriceDate);
    }

    @Then("the end price date is {string}")
    public void theEndPriceDateIs(String expectedEndDate) throws JSONException {
        // Obtén el valor de la fecha de fin desde la respuesta y compáralo con el valor esperado
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        String endPriceDate = jsonObject.getString("endPriceDate");
        assertEquals(expectedEndDate, endPriceDate);
    }

    @Then("the product ID is {int}")
    public void theProductIdIs(int expectedProductId) throws JSONException {
        // Obtén el valor del ID de producto desde la respuesta y compáralo con el valor esperado
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        int productId = jsonObject.getInt("productId");
        assertEquals(expectedProductId, productId);
    }

    @Then("the brand ID is {int}")
    public void theBrandIdIs(int expectedBrandId) throws JSONException {
        // Obtén el valor del ID de marca desde la respuesta y compáralo con el valor esperado
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        int brandId = jsonObject.getInt("brandId");
        assertEquals(expectedBrandId, brandId);
    }

    @Then("I receive a price of {double} with price list {int}")
    public void iReceiveAPriceOfWithPriceList(double expectedPrice, int expectedPriceList) throws JSONException {
        // Obtén el valor del precio y la lista de precios desde la respuesta y compáralos con los valores esperados
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        double price = jsonObject.getDouble("price");
        int priceList = jsonObject.getInt("priceList");

        assertEquals(expectedPrice, price, 0.001);
        assertEquals(expectedPriceList, priceList);
    }
}
