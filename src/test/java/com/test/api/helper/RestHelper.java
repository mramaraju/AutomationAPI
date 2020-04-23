package com.test.api.helper;

import com.fw.framework.Enums;
import com.fw.framework.infra.TestContext;
import com.fw.helper.ReadPropertyFile;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


public class RestHelper {


    TestContext context;

    public RestHelper(TestContext context) {

        this.context = context;

    }

    ReadPropertyFile RF = new ReadPropertyFile();

    public void statusCheck(String ApiCategroy) {
        try {
            RestAssured.baseURI = RF.apiUrl;
            RequestSpecification httpRequest = RestAssured.given();
            Response response = httpRequest.get(ApiCategroy);
            ResponseBody JsonBody = response.getBody();
            System.out.println("No browser been launched -" + JsonBody.asString());
            int statuscode = response.getStatusCode();

            if (statuscode == 200) {
                context.setTestStatus(Enums.TestStatus.PASS, RestAssured.baseURI = RF.apiUrl + ApiCategroy + "\t" +"API Initialization is passed with status code of" + "\t" + statuscode);
            } else {
                context.setTestStatus(Enums.TestStatus.FAIL, RestAssured.baseURI = RF.apiUrl + ApiCategroy + "\t" + "API Initialization is Failed with status code of" + "\t" + statuscode);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void isBrandExist(String brand, String ApiCategroy) throws ParseException {
        ResponseObject response = RestConnection.sendRestRequest("GET", RF.apiUrl + ApiCategroy, null, null);
        JSONObject jObj = (JSONObject) new JSONParser(JSONParser.MODE_JSON_SIMPLE).parse(response.getResponse());
        JSONArray subCats = (JSONArray) jObj.get("Subcategories");
        for (Object subCat : subCats) {
            JSONObject subCatjObj = (JSONObject) subCat;
            if (subCatjObj.get("Name").toString().equalsIgnoreCase(brand)) {
                context.setTestStatus(Enums.TestStatus.PASS, brand + "\t" + "brand does Exist");
                return;
            }
        }
        context.setTestStatus(Enums.TestStatus.FAIL, brand + "\t" + "brand does not Exist");
    }

    public void isBrandNotExist(String brand, String ApiCategroy) throws ParseException {
        ResponseObject response = RestConnection.sendRestRequest("GET", RF.apiUrl + ApiCategroy, null, null);
        System.out.println(response);
        JSONObject jObj = (JSONObject) new JSONParser(JSONParser.MODE_JSON_SIMPLE).parse(response.getResponse());
        JSONArray subCats = (JSONArray) jObj.get("Subcategories");
        for (Object subCat : subCats) {
            JSONObject subCatjObj = (JSONObject) subCat;
            if (subCatjObj.get("Name").toString().equalsIgnoreCase(brand)) {
                context.setTestStatus(Enums.TestStatus.FAIL, brand + "\t" + "brand does Exist" + subCatjObj.get("Name"));
                return;
            }
        }
        context.setTestStatus(Enums.TestStatus.KTF, brand + "\t" + "brand does not Exist");
    }

    public void getNumOfSubCats(int NumOfSubcats, String ApiCategroy) throws ParseException {
        ResponseObject response = RestConnection.sendRestRequest("GET", RF.apiUrl + ApiCategroy, null, null);
        JSONObject jObj = (JSONObject) new JSONParser(JSONParser.MODE_JSON_SIMPLE).parse(response.getResponse());
        JSONArray subCats = (JSONArray) jObj.get("Subcategories");
        if (subCats.size() == NumOfSubcats) {
            context.setTestStatus(Enums.TestStatus.PASS, "Number of Subcategories size does match with expected value of NumOfSubcats = " + "\t" + subCats.size());
            return;

        }
        context.setTestStatus(Enums.TestStatus.FAIL, "Number of Subcategories size does not match with expected value of NumOfSubcats = " + "\t" + subCats.size());
    }

    public void getNumOfBrand(String brand, String ApiCategroy) throws ParseException {
        ResponseObject response = RestConnection.sendRestRequest("GET", RF.apiUrl + ApiCategroy, null, null);
        JSONObject jObj = (JSONObject) new JSONParser(JSONParser.MODE_JSON_SIMPLE).parse(response.getResponse());
        JSONArray subCats = (JSONArray) jObj.get("Subcategories");
        for (Object subCat : subCats) {
            JSONObject subCatjObj = (JSONObject) subCat;
            if (subCatjObj.get("Name").toString().equalsIgnoreCase(brand) && subCatjObj.containsKey("Count")) {
                context.setTestStatus(Enums.TestStatus.PASS, "Acutual number of car under" + "\t" + brand + "\t"+ "brand" + "\t" + subCatjObj.get("Count"));
                return;

            }
            //return (long) subCatjObj.get("Count");
        }
        context.setTestStatus(Enums.TestStatus.FAIL, brand + "\t" + "brand does not Exist");

    }
}


