package com.tidavid1.Studywith.global.common.response.service;

import com.tidavid1.Studywith.global.common.response.CommonCode;
import com.tidavid1.Studywith.global.common.response.model.CommonResult;
import com.tidavid1.Studywith.global.common.response.model.ListResult;
import com.tidavid1.Studywith.global.common.response.model.SingleResult;
import java.util.List;

public class ResponseFactory {
    // 단일 결과 처리
    public static <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 복수 결과 처리
    public static <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과 반환
    public static CommonResult getSuccessResult(){
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과 반환
    public static CommonResult getFailResult(int code, String message) {
        CommonResult result = new CommonResult();
        setFailResult(result, code, message);
        return result;
    }

    private static void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonCode.SUCCESS.getCode());
        result.setMessage(CommonCode.SUCCESS.getMessage());
    }

    private static void setFailResult(CommonResult result, int code, String message){
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
    }
}
