package cc.tech.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HtmlMetadataUtils {
    public static String getPageDomain(JSONObject jsonObject) throws MalformedURLException {
        String pageURI = jsonObject.getJSONObject("Envelope")
                            .getJSONObject("WARC-Header-Metadata")
                            .getString("WARC-Target-URI");

        pageURI = canonicalizeURL(pageURI);

        URL url = new URL(pageURI);
        return url.getHost().replace("www.", "");
    }

    public static List<String> getURLDomainFromScriptTags(JSONObject jsonObject) throws MalformedURLException {
        return getURLDomainFromScriptTags(jsonObject, null);
    }

    public static List<String> getURLDomainFromScriptTags(JSONObject jsonObject,
                                                          String typeFilter) throws MalformedURLException {
        JSONArray scripts = getScriptTags(jsonObject);

        List<String> domains = Lists.newArrayList();

        for (int i = 0; i < scripts.length(); i++) {
            //filter out unwanted script tags
            if (!Strings.isNullOrEmpty(typeFilter)
                    && !scripts.getJSONObject(i)
                        .getString("type")
                        .equalsIgnoreCase(typeFilter)) {
                continue;
            }

            String scriptURL = scripts.getJSONObject(i)
                    .getString("url");

            scriptURL = canonicalizeURL(scriptURL);

            URL url = new URL(scriptURL);
            if (!domains.contains(url.getHost())) {
                domains.add(url.getHost().replace("www.", ""));
            }
        }

        return domains;
    }

    private static JSONArray getScriptTags(JSONObject jsonObject) {
        return jsonObject.getJSONObject("Envelope")
                .getJSONObject("Payload-Metadata")
                .getJSONObject("HTTP-Response-Metadata")
                .getJSONObject("HTML-Metadata")
                .getJSONObject("Head")
                .getJSONArray("Scripts");
    }

    private static String canonicalizeURL(String url) {
        //remove spaces
        url = url.replace(" ", "");

        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        } else {
            return String.format("http://%s", url).replace("////", "//");
        }
    }
}
