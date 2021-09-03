package com.lvan.blackholeaop.log;

import lombok.Getter;

/**
 * @author lvan
 * @date 2021/9/3
 */
@Getter
public enum RecordLogSwitch {
    BEFORE, AFTER_RETURNING, AFTER_THROWING, ALL, NONE;
}
