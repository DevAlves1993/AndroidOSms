package com.akanza.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.akanza.androidosms.core.OSms;
import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.OrangeSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseError;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSubscription;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    public final static String ID = "wcyuIqWa3Wj9S8kAZf9ALlHMZmVm34YL"; // TODO : Insert your Orange Id
    public final static String SECRET_CODE = "8FCqRkFbPm6uiENY"; // TODO : Insert your Secret code
    private final String ADDRESS = "+22589349055"; // TODO : Insert your number phone
    private final String TAG = MainActivity.class.getName();

    private EditText edtText;
    private EditText edtNumberPhone;
    private EditText edtHost;
    private TextView txtSenderAddress;
    private TextView txtSenderName;
    private TextView txtResourceUrl;
    private TextView txtNotifyUrl;

    private OSms oSms;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initOSms();
    }

    private void initView()
    {
        edtText = (EditText) findViewById(R.id.edtText);
        edtNumberPhone = (EditText) findViewById(R.id.edtNumberPhone);
        edtHost = (EditText) findViewById(R.id.edtHost);
        txtSenderAddress = (TextView) findViewById(R.id.txtSenderAddress);
        txtSenderName = (TextView) findViewById(R.id.txtSenderName);
        txtResourceUrl = (TextView) findViewById(R.id.txtResourceUrl);
        txtNotifyUrl = (TextView) findViewById(R.id.txtNotifyUrl);
    }

    private void initOSms()
    {
        AsyncTask<Void,Void,Void> asyncTask = new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                try
                {
                    oSms = new OSms.BuilderOSms()
                            .id(ID)
                            .secretCode(SECRET_CODE)
                            .build();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (HttpApiOrangeException e)
                {
                    ResponseError error = e.getError();
                    logResponseError(error,error.getCode());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                Toast.makeText(MainActivity.this,"Osms",Toast.LENGTH_SHORT).show();
            }
        }.execute((Void)null);
    }

    public void sendSms(View view)
    {
        String content = edtText.getText().toString();
        String senderNumberPhone = edtNumberPhone.getText().toString();
        if(!senderNumberPhone.startsWith("+225"))
            senderNumberPhone = "+225"+senderNumberPhone;
        final OrangeSMS sms = new OrangeSMS(ADDRESS,senderNumberPhone,content);
        AsyncTask<Void,Void,ResponseSMS> asyncTask = new AsyncTask<Void, Void, ResponseSMS>()
        {
            @Override
            protected ResponseSMS doInBackground(Void... params)
            {
                ResponseSMS responseSMS = null;
                try
                {
                    responseSMS = oSms.sendSms(sms);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (HttpApiOrangeException e)
                {
                    e.printStackTrace();
                    ResponseError error = e.getError();
                    logResponseError(error,error.getCode());
                }
                return responseSMS;
            }

            @Override
            protected void onPostExecute(ResponseSMS responseSMS)
            {
                if(responseSMS != null)
                {
                    ResponseSMS.SMSResponse outBoundSMSMessageRequest = responseSMS.getOutBoundSMSMessageRequest();
                    String senderAddress = outBoundSMSMessageRequest.getSenderAddress();
                    String senderName = outBoundSMSMessageRequest.getSenderName();
                    txtSenderAddress.setText(senderAddress);
                    txtSenderName.setText(senderName);
                }
            }
        }.execute((Void)null);
    }

    public void goViewHistoric(View view)
    {
        Intent intent = new Intent(this,HistoricActivity.class);
        startActivity(intent);
    }

    public void goViewStatistic(View view)
    {
        Intent intent = new Intent(this,StatisticActivity.class);
        startActivity(intent);
    }

    public void goViewContract(View view)
    {
        Intent intent = new Intent(this,ContractActivity.class);
        startActivity(intent);
    }

    private void logResponseError(ResponseError error, int code)
    {
        Log.e(TAG,"Error code : "+ String.valueOf(code));
        Log.i(TAG,"Message error : "+error.getMessage());
        Log.i(TAG,"Description : " +error.getDescription());
    }

    public void sendSubscription(View view) // TODO : delete method later
    {
        String s = edtNumberPhone.getText().toString();
        final String host = edtHost.getText()
                .toString();
        if(!host.isEmpty())
        {
            if(!s.startsWith("+225"))
                s = "+225"+s;
            final String numberPhone = s;
            AsyncTask<Void,Void,ResponseSubscription> asyncTask = new AsyncTask<Void, Void, ResponseSubscription>()
            {
                @Override
                protected ResponseSubscription doInBackground(Void... params)
                {
                    ResponseSubscription subscription = new ResponseSubscription(host);
                    try
                    {
                        subscription = oSms.subscriptionApi(numberPhone,subscription);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (HttpApiOrangeException e)
                    {
                        e.printStackTrace();
                        ResponseError error = e.getError();
                    }
                    return subscription;
                }

                @Override
                protected void onPostExecute(ResponseSubscription responseSubscription)
                {
                    if(responseSubscription != null)
                    {
                        ResponseSubscription.ReceiptSubscription deliveryReceiptSubscription = responseSubscription
                                .getDeliveryReceiptSubscription();
                        ResponseSubscription.ReceiptSubscription.CallbackRef callbackReference = deliveryReceiptSubscription
                                .getCallbackReference();
                        String resourceURL = deliveryReceiptSubscription.getResourceURL();
                        String notifyUrl = callbackReference.getNotifyURL();
                        txtResourceUrl.setText(resourceURL);
                        txtNotifyUrl.setText(notifyUrl);
                    }
                }
            }.execute((Void)null);
        }
    }

}
