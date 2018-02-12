package test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UniversalDataProvider {

    @DataProvider
    public static Object[][] dataSourceLoader(FrameworkMethod testMethod) throws IOException {
        DataSource ds = testMethod.getAnnotation(DataSource.class);
        if(ds == null){
            throw new Error("Test method has no @DataSource annotation: "+testMethod.getName());
        }
        switch (ds.type()){
            case FILE:
                return loadDataFromFile(ds.value());
            case RESOURCE:
                return loadDataFromResource(ds.value());

            default:
                    throw new Error("Data source type is not supported: "+ds.type());
        }

    }
    private static Object[][]loadDataFromResource(String value) throws IOException {
        return loadDataFromInputStream (UniversalDataProvider.class.getResourceAsStream(value));
    }
    private static Object[][]loadDataFromFile(String value) throws IOException {
        return loadDataFromInputStream (new FileInputStream(new File(value)));
    }
    private static Object[][] loadDataFromInputStream (InputStream input) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        List<Object[]> fileNameData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line!=null) {
            fileNameData.add(new Object[]{line});
            line = in.readLine();
        }
        in.close();

        return (Object[][]) fileNameData.toArray(new Object[][]{});

    }

}
