package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PositiveTests {

    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString()+"/new/";
    File file = new File(path);

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        file.mkdir();
    }

    @DataProvider
    public Iterator<Object[]> fileContent() {
         ContentDataBase contentDataBase = new ContentDataBase();
         return contentDataBase.fileContent().iterator();
    }

    @Test(groups = "positive",dataProvider = "fileContent")
    public void createFileWithData(String fileContent) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+"text.txt", fileContent);
        Assert.assertEquals(fc.readFile(path+"text.txt"), fileContent);
    }

    @Test(groups = {"positive", "stop"})
    public void createFileAndChangeData () throws IOException {
        FileCreator fc = new FileCreator();
        String name = fc.randomName(7);
        fc.createNewFile(path+name+".txt","POTATO!!!");
        fc.createNewFile(path+name+".txt", "APPLE!!!");
        Assert.assertEquals(fc.readFile(path+name+".txt"),"APPLE!!!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
    new FileCreator().deleteDir(file);
    }


}
