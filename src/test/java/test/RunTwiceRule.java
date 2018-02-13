package test;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RunTwiceRule implements TestRule {
    private int a;

    public RunTwiceRule() {}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
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
            if (new Count().getA()==0){
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    System.out.println("Failed: " + desc);
                }
            }

            for (int j=1;j<=new Count().getA();j++){
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    setA(j);
                    System.out.println("Failed on attempt: " + desc);
                }
            }
        }

    }



}
