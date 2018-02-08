package test;

import org.testng.Assert;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NegativeTests {


    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString()+"/new/";
    File file = new File(path);

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        file.mkdir();
    }

    @Test(groups = "negative", alwaysRun = true)
    public void createFileWithEmptyName() throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+" ", "");
        for (File s:file.listFiles())
        System.out.println("List: "+s);
        Assert.assertTrue(file.listFiles().length==0);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        new FileCreator().deleteDir(file);
    }

}
