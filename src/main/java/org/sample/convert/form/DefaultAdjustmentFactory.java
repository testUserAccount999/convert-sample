package org.sample.convert.form;

import org.sample.util.ConvertProperties;
import org.sample.util.StringUtils;

public class DefaultAdjustmentFactory implements AdjustmentFactory {

    private static final String ADJUSTMENT_KEY = "org.sample.convert.format.adjustment";

    @Override
    public Adjustment create() {
        Adjustment adjustment = null;
        String className = ConvertProperties.get(ADJUSTMENT_KEY);
        if(StringUtils.isBlankOrNull(className)) {
            throw new IllegalStateException("プロパティファイルに必要な設定がありません。キー名=" + ADJUSTMENT_KEY);
        }
        try {
            adjustment = (Adjustment) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("対象のクラスが見つかりません。", e);
        }
        return adjustment;
    }

}
