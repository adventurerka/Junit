package test;

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class Count extends TestWatcher {

    private int a;

    public Count() {}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    protected void starting(Description description) {
        if (description.getAnnotation(Unstable.class) != null) {
            setA(description.getAnnotation(Unstable.class).value());
        }
    }

}
