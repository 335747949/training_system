package com.ruoyi.common.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginUtil {
    public static <T> Map<String, Object> getPagingResultMap(List<T> list, Integer currPageNo, Integer pageSize) {
        Map<String, Object> retMap = new HashMap<>();

        if (list.isEmpty()) {
            retMap.put("result", Collections.emptyList());
            retMap.put("pageNo", 0);
            retMap.put("pageRowNum", 0);
            retMap.put("totalRowNum", 0);
            retMap.put("totalPageNum", 0);

            return retMap;
        }

        int totalRowNum = list.size();
        int totalPageNum = (totalRowNum - 1) / pageSize + 1;

        int realPageNo = currPageNo;
        if (currPageNo > totalPageNum) {
            realPageNo = totalPageNum;
        } else if (currPageNo < 1) {
            realPageNo = 1;
        }

        int fromIdx = (realPageNo - 1) * pageSize;
        int toIdx = realPageNo * pageSize > list.size() ? list.size() : realPageNo * pageSize;

        List<T> result = list.subList(fromIdx, toIdx);

        retMap.put("result", result);
        retMap.put("pageNo", realPageNo);
        retMap.put("pageRowNum", result.size());
        retMap.put("totalRowNum", totalRowNum);
        retMap.put("totalPageNum", totalPageNum);

        return retMap;
    }
}
