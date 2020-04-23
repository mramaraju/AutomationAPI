package com.test.api.helper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

public class RestConnection {
    public static int TIME_OUT = 60000;
    public static ResponseObject sendRestRequest(String method, String url,
                                                 Map<String, String> headers, String requestBodyString) {
        HttpURLConnection dataApiConnection = null;
        int responseCode = -1;
        String responseString = "";

        try {
            URL dataApiUrl = new URL(URLDecoder.decode(url, "UTF-8"));
            dataApiConnection = (HttpURLConnection) dataApiUrl.openConnection();
            dataApiConnection.setConnectTimeout(TIME_OUT);
            dataApiConnection.setReadTimeout(TIME_OUT);
            dataApiConnection.setRequestMethod(method);
            dataApiConnection.setDoOutput(true);

            if (headers != null && headers.size() != 0) {
                for (String headerKey : headers.keySet()) {
                    dataApiConnection.setRequestProperty(headerKey, headers.get(headerKey));
                }
            }
            if (requestBodyString != null && !requestBodyString.equalsIgnoreCase("")) {
                DataOutputStream dataWriter = null;
                try {
                    dataWriter = new DataOutputStream(dataApiConnection.getOutputStream());
                    dataWriter.write(requestBodyString.getBytes("UTF-8"));
                    dataWriter.flush();
                    dataWriter.close();
                } catch (Exception ex) {
                    disconnectWithoutException(dataApiConnection);
                    closeStreamWithoutException(dataWriter);
                    throw ex;
                }
            }

            responseString = getStringFromStream(dataApiConnection.getInputStream());
            responseCode = dataApiConnection.getResponseCode();
            dataApiConnection.disconnect();
            return new ResponseObject(responseCode, responseString);
        } catch (Exception ex) {
            try {
                responseCode = dataApiConnection.getResponseCode();
                responseString = getStringFromStream(dataApiConnection.getErrorStream());
            } catch (Exception responseDataException) {

            }
            disconnectWithoutException(dataApiConnection);
            return new ResponseObject(responseCode, responseString);
        }
    }
    public static void closeStreamWithoutException(Closeable closeObj) {
        if (closeObj != null) {
            try {
                closeObj.close();
            } catch (Exception ex) {

            }
        }
    }
    public static void disconnectWithoutException(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception ex) {

            }
        }
    }

    public static String getStringFromStream(InputStream inputStream) throws IOException {
        BufferedReader reader = null;
        String lines = "";
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                lines += line + "\n";
                line = reader.readLine();
            }
            reader.close();
            return lines;
        } catch (Exception ex) {
            closeStreamWithoutException(reader);
            throw ex;
        }
    }
}
