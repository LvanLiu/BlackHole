package com.lvan.blackholeaop.log.context;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.lvan.blackholeaop.context.AbstractAspectContext;
import com.lvan.blackholeaop.log.RecordLogSwitch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author lvan
 * @date 2021/9/3
 */
@Getter
@Setter
public abstract class AbstractLogAspectContext extends AbstractAspectContext implements LogAspectContext {

    private Boolean recordAllLog;
    private Boolean recordNothing;

    protected AbstractLogAspectContext(JoinPoint joinPoint) {
        super(joinPoint);
    }

    public boolean isEnableRecordSwitch(final RecordLogSwitch expectRecordSwitch) {

        if (isRecordAllLog()) {
            return true;
        }
        if (isRecordNothing()) {
            return false;
        }
        return ArrayUtil.contains(acquireRecordLogSwitches(), expectRecordSwitch);
    }

    protected boolean isRecordAllLog() {

        RecordLogSwitchHelper recordLogSwitchHelper =
                new RecordLogSwitchHelper(this::getRecordAllLog, this::setRecordAllLog);
        return recordLogSwitchHelper.fetchRecordFromCache(RecordLogSwitch.ALL);
    }

    protected boolean isRecordNothing() {

        RecordLogSwitchHelper recordLogSwitchHelper =
                new RecordLogSwitchHelper(this::getRecordNothing, this::setRecordNothing);
        return recordLogSwitchHelper.fetchRecordFromCache(RecordLogSwitch.NONE);
    }

    protected abstract RecordLogSwitch[] acquireRecordLogSwitches();

    @AllArgsConstructor
    protected class RecordLogSwitchHelper {

        private Supplier<Boolean> getterRecordSwitchFunction;
        private Consumer<Boolean> setterRecordSwitchFunction;

        public boolean fetchRecordFromCache(RecordLogSwitch recordLogSwitch) {

            if (ObjectUtil.isNull(getterRecordSwitchFunction.get())) {
                boolean isRecord = ArrayUtil.contains(acquireRecordLogSwitches(), recordLogSwitch);
                setterRecordSwitchFunction.accept(isRecord);
            }

            return getterRecordSwitchFunction.get();
        }
    }
}
