package com.premalaanand.tests.crud;

import com.premalaanand.base.BaseTest;
import com.premalaanand.endpoints.APIConstants;
import com.premalaanand.modules.PayloadManager;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class TestCreateToken extends BaseTest {

    @Test(groups = "reg", priority = 1)
//@TmsLink("https://bugz.atlassian.net/browse/TS-1")
    @Owner("premala")
    @Description("TC#2  - Create Token and Verify")
    public void testTokenPOST() {
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.setAuthPayload())
                .post();



        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        String token = payloadManager.getTokenFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(token);
    }


}
