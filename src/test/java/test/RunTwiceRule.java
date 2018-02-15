package test;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RunTwiceRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description desc) {
        return new RunTwiceStatement(base, desc);
    }


    public class RunTwiceStatement extends Statement {

        private final Statement base;
        private Description desc;

        public RunTwiceStatement(Statement base, Description desc) {
            this.base = base;
            this.desc = desc;
        }

        @Override
        public void evaluate(){
            if (desc.getAnnotation(Unstable.class)!=null) {
                int a = desc.getAnnotation(Unstable.class).value();
                if (a != 0) {
                    int b =0;
                    int c = 0;
                for (int j = 1; j <= a; j++) {
                    try {
                        base.evaluate();
                    } catch (Throwable t) {
                        System.out.println("Failed on attempt " + desc);
                        c++;
                    }
                    if (c!=b)
                        b = c;
                    else break;

                }
            }

            }
        }

    }



}
