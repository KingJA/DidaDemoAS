package com.dida.first.utils;

import java.util.LinkedHashMap;

public class ParamsManager {
    private static ParamsManager mParamsManager;
    private static LinkedHashMap<String, String> mLinkedHashMap = new LinkedHashMap<String, String>();

    private ParamsManager() {
    }

    public static ParamsManager getInstance() {
        if (mParamsManager == null) {
            synchronized (ParamsManager.class) {
                if (mParamsManager == null) {
                    mParamsManager = new ParamsManager();
                }
            }
        }
        return mParamsManager;
    }

    public enum Param {
        PG_COMMENT
    }

    public static LinkedHashMap<String, String> getParamMap(Param param) {
        mLinkedHashMap.clear();
        switch (param) {
            case PG_COMMENT:
                break;
            default:
                break;
        }
        return mLinkedHashMap;
    }
}
