package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.asynchttpclient.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class ReqresTest {
    private final static String URL = "https://reqres.in";

    @Test
    public void checkAvatarAndIdTest(){

        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL+"/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }
    @Test
    public void checkAvatarAndIdTeste(){
        Map<String,String> map = new HashMap<>();

        Response response = (Response) given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL+"/api/users?page=2")
                .then().statusCode(200).log().all()
                .extract().response();
        JsonPath jsonPath = (JsonPath) response;
        System.out.println(jsonPath.get("data, id").toString());
//users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
//Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }
    private final static String URLG = "https://reqres.in/";
    @Test
    public void checkAvatarAndIdTestO() {
        Specification.installSpecification(Specification.requestSpec(URL),Specification.responsSpecOk200());
        List<UserData> users = given()
                .when()
                .get( "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
@Test
public void succesRegTest(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responsSpecOk200());
Integer id = 4;
String token = "QpwL5tke4Pnpja7X4";
Register user = new Register("eve.holt@reqres.in", "pistol");
SuccessReg successReg = given()
        .body(user)
        .when()
        .post("api/register")
        .then().log().all()
        .extract().as(SuccessReg.class);
Assert.assertEquals(id, successReg.getId());
Assert.assertEquals(token, successReg.getToken());
    }

    @Test
    public void  unSuccessRegTest(){
        Specification.installSpecification(Specification.requestSpec(URL),Specification.responsSpecError400());
        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class );
        Assert.assertEquals( unSuccessReg.getError(),"Missing password");
    }
    @Test    //Basis for tests
    public void putUpdate() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "morpheus");

        user.put("job", "zion resident");
        given()
                .baseUri("https://reqres.in/")
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .put("api/users/2")
                .then().statusCode(200).log().all();
    }
    @Test
    public void sortedYearsTest(){
        Specification.installSpecification(Specification.requestSpec(URL),Specification.responsSpecOk200());
List<ColorsData> colors = given()
        .when()
        .get("api/unknown")
        .then().log().all()
        .extract().body().jsonPath().getList("data",ColorsData.class);
List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
List<Integer>sortedYears = years.stream().sorted().collect(Collectors.toList());
Assert.assertEquals(years,sortedYears);
        System.out.println(years);
        System.out.println(sortedYears);
    }

    @Test
    public void deleteUserTest(){
        Specification.installSpecification(Specification.requestSpec(URL),Specification.responsSpecUnique(204));
given()
        .when()
        .delete("/api/users/2")
        .then().log().all();
    }

    @Test
    public void timeTest(){
        Specification.installSpecification(Specification.requestSpec(URL),Specification.responsSpecOk200());
UserTime user = new UserTime("morpheus","zion resident");
UserTimeResponse response = given()
        .body(user)
        .when()
        .put("api/users/2")
        .then().log().all()
        .extract().as(UserTimeResponse.class);
String regex = "(.{5})$";
String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");
        System.out.println(currentTime);
Assert.assertEquals(currentTime,response.getUpdateAt().replaceAll(regex,""));
        System.out.println(response.getUpdateAt().replaceAll(regex, ""));
    }
//    @Test
////    public void getUsersTest() {
////        baseURI = "https://api.example.com";
////        Response response = (Response) given()
////                .when()
////                .get("/api/users");
////        int statusCode = response.getStatusCode();
////        Assert.assertEquals(statusCode, 200, "Некорректный код состояния");
////        String contentType = response.getContentType();
////        Assert.assertEquals(contentType, "application/json", "Некорректный формат ответа");
////        String responseBody = response.getBody().asString();
////        System.out.println("Тело ответа: " + responseBody);
//    }
}
