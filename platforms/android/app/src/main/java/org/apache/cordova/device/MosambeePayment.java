package org.apache.cordova.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mosambee.lib.MosCallback;
import com.mosambee.lib.ResultData;
import com.mosambee.lib.TransactionResult;
import com.mosambee.test.R;
import com.stanleyidesis.cordova.plugin.MosambeePlugin;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MosambeePayment extends AppCompatActivity implements TransactionResult{

    private FrameLayout container;

    MosCallback tc;
    JSONObject para;
    String customerEmailId;
    Double completionTenderAmount;
    String customerMobileNo;
    String orderId;


    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosambee_payment);
        container =  findViewById(R.id.container);
        para = MosambeePlugin.requestParameters();
        try {
            this.orderId = para.getString("orderId");
            this.completionTenderAmount = para.getDouble("completionTenderAmount");
            this.customerEmailId = para.getString("customerEmailId");
            this.customerMobileNo = para.getString("customerMobileNo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        startMosambeePayment();
    }

    public void startMosambeePayment(){
        String password = "1234";
        tc = new MosCallback(this);
        tc.initialise("", password,this);
        tc.initializeSignatureView(container,"#55004A", "#750F5A");
        tc.initialiseFields("SALE",this.customerMobileNo,"skdjJgGVvcCjh4b5J2kjhjk7i8hjZ2SD0f1ds5z7sDZ6Xuyio315266pj5h",true,this.customerEmailId,"merchantRef1","Bluetooth",new SimpleDateFormat("MM/dd/yyyy").format(new Date()),"0.00");
        tc.processTransaction(this.orderId,this.completionTenderAmount,this.completionTenderAmount, this.orderId);
    }

    @Override
    public void onResult(final ResultData result) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(result.getResult()){
                     Toast.makeText(MosambeePayment.this,"Result Received",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MosambeePayment.this,"Result Not Received",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onCommand(final String command) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 Toast.makeText(MosambeePayment.this,"command",Toast.LENGTH_LONG).show();
            }
        });
    }
}
