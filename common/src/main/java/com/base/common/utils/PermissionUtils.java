package com.base.common.utils;

import android.content.Context;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * @author anhuang
 * @date 2022/3/24
 * Description :
 */
public class PermissionUtils {
    /**
     * 监测xx权限，如果没有就申请该权限，并等待返回值
     * @param context
     * @param permissions
     * @param permissionCallback
     */
    public static void checkPermission(Context context, List<String> permissions,
                                       PermissionCallback permissionCallback){
        if (!XXPermissions.isGranted(context, permissions)){
            XXPermissions.with(context).permission(permissions).request(new OnPermissionCallback() {
                @Override
                public void onGranted(List<String> permissions, boolean all) {
                    permissionCallback.onCallback(all);
                }

                @Override
                public void onDenied(List<String> permissions, boolean never) {
                    permissionCallback.onCallback(false);
                }
            });
        }else {
            permissionCallback.onCallback(true);
        }
    }


   public interface PermissionCallback{
        /**
         * 申请成功或者失败的回调
         * @param onGranted
         */
        void onCallback(boolean onGranted);

    }
}
