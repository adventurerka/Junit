package test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class NegativeTests implements MyCategories{


    @Rule
    public TemporaryFolder tmpdir = new TemporaryFolder();

    @Test
    @Category(Negative.class)
    public void createFileWithEmptyName() throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(tmpdir.getRoot()+" ", "");
        for (File s:tmpdir.getRoot().listFiles())
        System.out.println("List: "+s);
        Assert.assertTrue(tmpdir.getRoot().listFiles().length==0);
    }

}
