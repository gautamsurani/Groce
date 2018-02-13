package com.getprofitam.android.utils;


import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by theonetech28 on 2/24/2016.
 */
public class RestClient {
    private ArrayList<NameValuePair> params;
    private ArrayList<NameValuePair> headers;

    private String url;

    private int responseCode;
    private String message;

    public Header[] getHeader() {
        return header;
    }

    private Header[] header;

    private String response;

    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public RestClient(String url) {
        this.url = url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    public void AddParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public void AddHeader(String name, String value) {
        headers.add(new BasicNameValuePair(name, value));
    }

    public void Execute(RequestMethod method) throws Exception {
        switch (method) {
            case GET: {
                //add parameters
                String combinedParams = "";
                if (!params.isEmpty()) {
                    combinedParams += "?";
                    for (NameValuePair p : params) {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                        if (combinedParams.length() > 1) {
                            combinedParams += "&" + paramString;
                        } else {
                            combinedParams += paramString;
                        }
                    }
                }

                HttpGet request = new HttpGet(url + combinedParams);

                //add headers
                for (NameValuePair h : headers) {
                    request.addHeader(h.getName(), h.getValue());
                }

                executeRequest(request, url);
                break;
            }
            case POST: {
                HttpPost request = new HttpPost(url);

                if (!params.isEmpty()) {
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }

                //add headers
                for (NameValuePair h : headers) {
                    request.addHeader(h.getName(), h.getValue());
                }

                executeRequest(request, url);
                break;
            }

            case MULTIPART: {
                HttpPost request = new HttpPost(url);

                request.setEntity(entity);
                //add headers
                for (NameValuePair h : headers) {
                    request.addHeader(h.getName(), h.getValue());
                }


                executeRequest(request, url);
                break;
            }

            case DELETE: {
                HttpDelete request = new HttpDelete(url);

                //add headers
                for (NameValuePair h : headers) {
                    request.addHeader(h.getName(), h.getValue());
                }
                executeRequest(request, url);
                break;
            }
        }
    }

    private void executeRequest(HttpUriRequest request, String url) {
        DefaultHttpClient client = new DefaultHttpClient();

        HttpResponse httpResponse;

        try {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();
            Log.i("responseCode","Code:-"+responseCode);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {

                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);
                header = httpResponse.getHeaders("Set-Cookie");
                // Closing the input stream will trigger connection release
                instream.close();
            }

        } catch (ClientProtocolException e) {
            Log.e("hii", "heyyy" + e.getMessage());
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) {
            client.getConnectionManager().shutdown();
            Log.e("hii", "heyyy2" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    MultipartEntity multientity;

    public void AddFiles(MultipartEntity mpEntity) {
        multientity = mpEntity;
    }

    HttpEntity entity;

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }
}
