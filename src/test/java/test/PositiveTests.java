package test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static test.DataSource.Type.RESOURCE;

@RunWith(DataProviderRunner.class)
public class PositiveTests implements MyCategories{

    @Rule
    public TemporaryFolder tmpdir = new TemporaryFolder();


    @DataProvider
    public static Object[][] fileContent() {
        List mylist = new ArrayList<Object[]>();
        for(int i=0;i<10;i++){
            mylist.add(new Object[]{new FileCreator().randomName(7),
                    new FileCreator().randomName(7)});
        }
        return (Object[][])mylist.toArray(new Object[][]{});
    }
    @Test
    @Category({Positive.class, Ignor.class})
    @UseDataProvider("fileContent")
    public void createFileWithData(String fileContent, String name) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(tmpdir.getRoot()+name+".txt", fileContent);
        Assert.assertEquals(fc.readFile(tmpdir.getRoot()+name+".txt"), fileContent);
    }

    @Test
    @Category({Positive.class})
    @UseDataProvider(value = "dataSourceLoader",location = UniversalDataProvider.class)
    @DataSource(value = "/fileName.data", type = RESOURCE)
    public void createFileAndChangeData (String name) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(tmpdir.getRoot()+name+".txt","POTATO!!!");
        fc.createNewFile(tmpdir.getRoot()+name+".txt", "APPLE!!!");
        Assert.assertEquals(fc.readFile(tmpdir.getRoot()+name+".txt"),"APPLE!!!");
    }

}
