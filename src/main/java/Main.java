import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String jsonFileName = "data.json";

        List<Employee> employeeList = parceCSV(columnMapping, fileName);
        String json = listToJason(employeeList);
        writeString(json, jsonFileName);

    }

    //Парсинг CSV в список объектов
    public static List<Employee> parceCSV(String[] columnMapping, String fileName) {

        List<Employee> employeeList = null;

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader).withMappingStrategy(strategy).build();
            employeeList = csv.parse();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        return employeeList;

    }


    //Преобразование списка объектов в строку Jason
    public static String listToJason(List employeeList) {

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(employeeList, listType);

    }


    //Запись строки Json в файл
    public static void writeString(String string, String fileName) {

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(string);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
