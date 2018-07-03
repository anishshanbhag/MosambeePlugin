package com.stanleyidesis.cordova.plugin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.test.mock.MockPackageManager;
import android.util.Log;

import com.mosambee.lib.ResultData;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MosambeePlugin extends CordovaPlugin {
    int requestCode;
    String command;

   String[] mPermission = {
                           Manifest.permission.READ_PHONE_STATE,
                           Manifest.permission.INTERNET,
                           Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                           Manifest.permission.ACCESS_NETWORK_STATE,
                           Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN
                         };
   int[] grantResults = new int[7];
   private static final int REQUEST_CODE_PERMISSION = 2;
    ResultData result;
    String customerEmailId;
    Double completionTenderAmount;
    int customerMobileNo;
    String orderId;
    CallbackContext callbackContext;
   static JSONObject options1;


   @Override
   public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       this.callbackContext = callbackContext;
     if ("initMosambeePayment".equals(action)) {
       initMosambeePayment();
       return true;
     }
     else if("startMosambeePayment".equals(action)) {
         options1 = args.getJSONObject(0);
         JSONObject options = args.getJSONObject(0);
         this.orderId = options.getString("orderId");
         this.completionTenderAmount = options.getDouble("completionTenderAmount");
         this.customerEmailId = options.getString("customerEmailId");
         this.customerMobileNo = options.getInt((String)options.get("customerMobileNo"));
         return true;
     }
     else if("onRequestPermissionsResult".equals(action)){
       JSONObject options = args.getJSONObject(0);
       requestCode  = Integer.parseInt((String) options.get("requestCode"));
       onRequestPermissionsResult(requestCode,mPermission,grantResults);
       return true;
     }
     else{
       callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
     }

   }
   public void initMosambeePayment(){
          try {
              if(android.os.Build.VERSION.SDK_INT >= 23) {
                  cordova.requestPermissions(this, 0, mPermission);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
     }

    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException
    {
        for(int r:grantResults)
        {
            if(r == PackageManager.PERMISSION_DENIED)
            {
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
                return;
            }
        }
    }


     public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         Log.e("Req Code for permission", "" + requestCode);
         if (requestCode == REQUEST_CODE_PERMISSION) {
             if (grantResults.length >0 &&
                     grantResults[0] == MockPackageManager.PERMISSION_GRANTED &&
                     grantResults[1] == MockPackageManager.PERMISSION_GRANTED &&
                     grantResults[2] == MockPackageManager.PERMISSION_GRANTED &&
                     grantResults[3] == MockPackageManager.PERMISSION_GRANTED &&
                     grantResults[4] == MockPackageManager.PERMISSION_GRANTED &&
                     grantResults[5] == MockPackageManager.PERMISSION_GRANTED &&
                     grantResults[6] == MockPackageManager.PERMISSION_GRANTED )
             {
//                 Toast.makeText(MosambeePlugin.this, "Permissions granted!!!",Toast.LENGTH_LONG).show();
             }else{
//                 Toast.makeText(this, "Permission not granted!!!",Toast.LENGTH_LONG).show();
             }
         }
     }

     public static JSONObject requestParameters(){
            return options1;
     }
}
