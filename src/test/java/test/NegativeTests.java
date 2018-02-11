package test;

import org.junit.*;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NegativeTests implements MyCategories{


    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString()+"/new/";
    File file = new File(path);

    @Before
    public void setUp(){
        file.mkdir();
    }

    @Test
    @Category(Negative.class)
    public void createFileWithEmptyName() throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+" ", "");
        for (File s:file.listFiles())
        System.out.println("List: "+s);
        Assert.assertTrue(file.listFiles().length==1);
    }

    @After
    public void tearDown() {
        new FileCreator().deleteDir(file);
    }

}
