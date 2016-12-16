package com.brsanthu.googleanalytics.internal;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

public class MultiUrlEncodedFormEntity extends StringEntity {
    public MultiUrlEncodedFormEntity(final List<List<NameValuePair>> parameters, final Charset charset) {
        super(constructCombinedEntityString(parameters, charset), ContentType.create(URLEncodedUtils.CONTENT_TYPE, charset));
    }

    private static String constructCombinedEntityString(final List<List<NameValuePair>> parameters, final Charset charset) {
        Charset charsetToUse = charset != null ? charset : HTTP.DEF_CONTENT_CHARSET;
        StringBuilder builder = new StringBuilder();
        for (List<? extends NameValuePair> param : parameters) {
            builder.append(URLEncodedUtils.format(param, charsetToUse));
            builder.append("\r\n");
        }
        return builder.toString(); 
    }
}
