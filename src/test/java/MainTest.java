import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    //Проверяем распарсился ли CSV файл
    @Test
    public void testParceCSV() {

        //assert
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> expectationList = new ArrayList<>();
        expectationList.add(new Employee(1, "John", "Smith", "USA", 25));
        expectationList.add(new Employee(2, "Inav", "Petrov", "RU", 23));
        List<Employee> employeeLst = Main.parceCSV(columnMapping, fileName);

        //act
        Assertions.assertEquals(expectationList.get(0).hashCode(), employeeLst.get(0).hashCode());
        Assertions.assertEquals(expectationList.get(1).hashCode(), employeeLst.get(1).hashCode());
    }

    //Проверяем преобразовался ли список в строку формата JSON
    @Test
    public void testListToJason() {

        //assert
        String expectedString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Inav\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "John", "Smith", "USA", 25));
        employeeList.add(new Employee(2, "Inav", "Petrov", "RU", 23));
        String jsonActual = Main.listToJason(employeeList);

        //act
        Assertions.assertEquals(expectedString, jsonActual);
    }


    //Проверяем создался ли файл
    @Test
    public void testWriteString() throws FileNotFoundException {

        //assert
        String jsonFileName = "data.json";
        String testString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Inav\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";

        Main.writeString(testString, jsonFileName);
        File file = new File(jsonFileName);

        //act
        Assertions.assertTrue(file.exists());
    }



}
