package test;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        PositiveTests.class,
        NegativeTests.class
})
@RunWith(Categories.class)
@Categories.ExcludeCategory(MyCategories.Ignor.class)
public class CurrentSuite {
}
