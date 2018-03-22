package org.sample.validator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;

import org.junit.Test;

/**
 * {@link CommonValidator}のテストクラス
 */
public class CommonValidatorTest {

    /**
     * コンストラクタのテスト
     * @throws Exception テスト失敗
     */
    @Test
    public void testCommonValidator() throws Exception {
        Class<?> clazz = CommonValidator.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        assertThat(constructors[0].newInstance(new Object[] {}), notNullValue());
    }

    /**
     * {@link CommonValidator#validateMaxLength(Object, int)}のテスト
     * @throws Exception テスト失敗
     */
    @Test
    public void testValidateMaxLength() throws Exception {
        assertThat(CommonValidator.validateMaxLength(null, 1), is(true));
        assertThat(CommonValidator.validateMaxLength("", 0), is(true));
        assertThat(CommonValidator.validateMaxLength("123", 3), is(true));
        assertThat(CommonValidator.validateMaxLength("1234", 3), is(false));
    }

    /**
     * {@link CommonValidator#validateMinLength(Object, int)}のテスト
     * @throws Exception テスト失敗
     */
    @Test
    public void testValidateMinLength() throws Exception {
        assertThat(CommonValidator.validateMinLength(null, 3), is(true));
        assertThat(CommonValidator.validateMinLength("", 3), is(true));
        assertThat(CommonValidator.validateMinLength("12", 3), is(false));
        assertThat(CommonValidator.validateMinLength("123", 3), is(true));
    }

    /**
     * {@link CommonValidator#validateIntRange(Object, int, int)}のテスト
     * @throws Exception テスト失敗
     */
    @Test
    public void testValidateIntRange() throws Exception {
        assertThat(CommonValidator.validateIntRange(null, 1, 3), is(true));
        assertThat(CommonValidator.validateIntRange("", 1, 3), is(true));
        assertThat(CommonValidator.validateIntRange(0, 1, 3), is(false));
        assertThat(CommonValidator.validateIntRange("1", 1, 3), is(true));
        assertThat(CommonValidator.validateIntRange("3", 1, 3), is(true));
        assertThat(CommonValidator.validateIntRange(4, 1, 3), is(false));
        assertThat(CommonValidator.validateIntRange("a", 1, 3), is(false));
    }

    /**
     * {@link CommonValidator#validateDouble(Object)}のテスト
     * @throws Exception テスト失敗
     */
    @Test
    public void testValidateDouble() throws Exception {
        assertThat(CommonValidator.validateDouble(null), is(true));
        assertThat(CommonValidator.validateDouble(""), is(true));
        assertThat(CommonValidator.validateDouble(1), is(true));
        assertThat(CommonValidator.validateDouble(1.0), is(true));
        assertThat(CommonValidator.validateDouble("1."), is(true));
        assertThat(CommonValidator.validateDouble(1.0), is(true));
        assertThat(CommonValidator.validateDouble("1.0.0"), is(false));
        assertThat(CommonValidator.validateDouble(BigDecimal.ONE), is(true));
    }

    /**
     * {@link CommonValidator#validateRequired(Object)}のテスト
     * @throws Exception テスト失敗
     */
    @Test
    public void testValidateRequired() throws Exception {
        assertThat(CommonValidator.validateRequired(null), is(false));
        assertThat(CommonValidator.validateRequired(""), is(false));
        assertThat(CommonValidator.validateRequired(1), is(true));
        assertThat(CommonValidator.validateRequired(new Object()), is(true));
    }

}
