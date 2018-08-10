package thread.ThreadSchool.publish;


import annoations.annoations.NotRecommend;
import annoations.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NotThreadSafe
@NotRecommend
/*
对象未构造之前不可以发布
 */
public class Escape {
    private static final Logger log = LoggerFactory.getLogger(Escape.class);
    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
