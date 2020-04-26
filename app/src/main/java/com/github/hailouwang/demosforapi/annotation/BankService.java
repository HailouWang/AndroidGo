package com.github.hailouwang.demosforapi.annotation;

import android.util.Log;

import java.lang.reflect.Method;

public class BankService {
    /**
     * @param money 转账金额
     */
    @BankTransferMoney(maxMoney = 15000)
    public static void TransferMoney(double money) {
        String content = processAnnotationMoney(money);
        Log.d("hlwang", "BankService TransferMoney content : " + content);
    }

    public static String processAnnotationMoney(double money) {
        try {
            Method transferMoney = BankService.class.getDeclaredMethod("TransferMoney", double.class);
            boolean annotationPresent = transferMoney.isAnnotationPresent(BankTransferMoney.class);
            if (annotationPresent) {
                BankTransferMoney annotation = transferMoney.getAnnotation(BankTransferMoney.class);
                double l = annotation.maxMoney();
                if (money > l) {
                    return "转账金额大于限额，转账失败";
                } else {
                    return "转账金额为:" + money + "，转账成功";
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return "转账处理失败";
    }
}
