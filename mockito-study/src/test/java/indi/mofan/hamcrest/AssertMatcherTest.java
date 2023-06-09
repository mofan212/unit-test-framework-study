package indi.mofan.hamcrest;

import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


/**
 * @author mofan 2020/12/21
 */
public class AssertMatcherTest {

    @Test
    public void test1() {
        int i = 10;

        MatcherAssert.assertThat(i, equalTo(10));
        MatcherAssert.assertThat(i, not(equalTo(20)));
        MatcherAssert.assertThat(i, is(10));
        MatcherAssert.assertThat(i, not(is(20)));
        // not -- is? is -- not? All can!
        MatcherAssert.assertThat(i, is(not(20)));
    }

    @Test
    public void test2() {
        double price = 2.12;

        // either or
        MatcherAssert.assertThat(price, either(equalTo(2.12)).or(equalTo(1.12)));
        // both and
        MatcherAssert.assertThat(price, both(not(equalTo(1.12))).and(not(equalTo(2.11))));
        // anyOf
        MatcherAssert.assertThat(price, anyOf(is(2.12), not(1.12), is(6.20)));
        // allOf
        MatcherAssert.assertThat(price, allOf(is(2.12), not(is(1.12)), not(2.11)));

        MatcherAssert.assertThat(Stream.of(1, 2, 3).allMatch(i -> i > 0), equalTo(true));
    }

    @Test
    @Ignore
    public void test3() {
        double price = 2.12;

        // 下述验证将不会通过
        MatcherAssert.assertThat("the double value assertion failed", price,
                either(equalTo(2.22)).or(equalTo(1.12)));
    }
}
