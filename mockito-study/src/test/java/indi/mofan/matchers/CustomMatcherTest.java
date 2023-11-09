package indi.mofan.matchers;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static indi.mofan.utils.CompareNumberMatcher.gt;
import static indi.mofan.utils.CompareNumberMatcher.lt;
import static org.hamcrest.CoreMatchers.both;

/**
 * @author mofan 2020/12/21
 */
public class CustomMatcherTest {
    @Test
    public void test1() {

        // 10 > 5 ?
        MatcherAssert.assertThat(10, gt(5));
        // 10 < 20 ?
        MatcherAssert.assertThat(10, lt(20));
        // 5 < 10 < 20 ?
        MatcherAssert.assertThat(10, both(gt(5)).and(lt(20)));
    }
}
