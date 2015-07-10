package com.createappfaster.marsapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.createappfaster.model.MarsReport;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextTerrestrialDate;
    private TextView mTextMarsDate;
    private TextView mTextMinTemp;
    private TextView mTextMaxTemp;
    private TextView mTextWindSpeed;
    private TextView mTextPressure;
    private TextView mTextWeatherStatus;
    private Button btnMarsWeather;
    private ProgressDialog mProgressDialog;

    private static final String url = "http://marsweather.ingenology.com/v1/latest/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getElementOfView();
        btnMarsWeather.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
    }

    private void getElementOfView() {
        mTextTerrestrialDate = (TextView) findViewById(R.id.tvTerrestrialDate);
        mTextMarsDate = (TextView) findViewById(R.id.tvMarsDate);
        mTextMinTemp = (TextView) findViewById(R.id.tvMinTemp);
        mTextMaxTemp = (TextView) findViewById(R.id.tvMaxTemp);
        mTextWindSpeed = (TextView) findViewById(R.id.tvWindSpeed);
        mTextPressure = (TextView) findViewById(R.id.tvPressure);
        mTextWeatherStatus = (TextView) findViewById(R.id.tvweatherStatus);
        btnMarsWeather = (Button) findViewById(R.id.MarsButton);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.MarsButton:
                showDialog();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                MarsReport marsReport = parseMarsJSONResponse(response);
                                mTextWeatherStatus.setText(marsReport.getWeatherStatus());
                                mTextPressure.setText(marsReport.getPressure());
                                mTextWindSpeed.setText(marsReport.getWindSpeed());
                                mTextMaxTemp.setText(marsReport.getMaxTemp());
                                mTextMinTemp.setText(marsReport.getMinTemp());
                                mTextMarsDate.setText(marsReport.getMarsDate());
                                mTextTerrestrialDate.setText(marsReport.getTerrestrialDate());
                                dismissDialog();
                            }

                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                dismissDialog();
                            }

                        });

                MarsRequest.getInstance(this).addToRequestQueue(jsonObjectRequest);

                break;
        }
    }

    private MarsReport parseMarsJSONResponse(JSONObject response) {
        try {
            response = response.getJSONObject("report");
            MarsReport marsReport = new MarsReport();
            if (response.isNull("terrestrial_date"))
                marsReport.setTerrestrialDate("-");
            else
                marsReport.setTerrestrialDate(response.getString("terrestrial_date"));
            if (response.isNull("sol"))
                marsReport.setMarsDate("-");
            else
                marsReport.setMarsDate(response.getString("sol"));
            if (response.isNull("min_temp"))
                marsReport.setMinTemp("-");
            else
                marsReport.setMinTemp(response.getString("min_temp"));
            if (response.isNull("max_temp"))
                marsReport.setMaxTemp("-");
            else
                marsReport.setMaxTemp(response.getString("max_temp"));
            if (response.isNull("wind_speed"))
                marsReport.setWindSpeed("-");
            else
                marsReport.setWindSpeed(response.getString("wind_speed"));
            if (response.isNull("pressure"))
                marsReport.setPressure("-");
            else
                marsReport.setPressure(response.getString("pressure"));
            if (response.isNull("atmo_opacity"))
                marsReport.setWeatherStatus("-");
            else
                marsReport.setWeatherStatus(response.getString("atmo_opacity"));
            return marsReport;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private void showDialog() {
        if (!(mProgressDialog.isShowing())) {
            mProgressDialog.show();
        }
    }

    private void dismissDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
