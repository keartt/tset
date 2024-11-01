package boilerPlateEgov.test;


import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

@Controller
public class ProxyController {
    @GetMapping("/map.do")
    public String te() {
        return "/cmm/map";
    }

    @RequestMapping(value = "/proxy.do", method = {RequestMethod.GET, RequestMethod.POST})
    public void proxy(@RequestParam String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpURLConnection httpURLConnection = null;
        InputStream is = null;
        try {
            Enumeration<String> e = request.getParameterNames();

            StringBuffer param = new StringBuffer();

            while (e.hasMoreElements()) {
                String paramKey = e.nextElement();

                String[] values = request.getParameterValues(paramKey);

                for (String value : values) {
                    param.append(paramKey + "=" + value + "&");
                }
            }

            String query = param.toString();
            query = query.substring(0, query.lastIndexOf("&"));

            if (request.getQueryString() != null && !"".equals(request.getQueryString())) {
                url = url + "?" + query;
            }


            URL targetURL = new URL(url);
            httpURLConnection = (HttpURLConnection) targetURL.openConnection();

            Enumeration<String> headerKey = request.getHeaderNames();

            while (headerKey.hasMoreElements()) {
                String key = headerKey.nextElement();
                String value = request.getHeader(key);
                httpURLConnection.addRequestProperty(key, value);
            }

            httpURLConnection.setRequestMethod(request.getMethod().toUpperCase());

            response.setStatus(httpURLConnection.getResponseCode());
            response.setCharacterEncoding("utf-8");
            response.setContentType(httpURLConnection.getContentType());

            if (httpURLConnection.getResponseCode() == 200) {
                IOUtils.copy(httpURLConnection.getInputStream(), response.getOutputStream());
            } else {
                IOUtils.copy(httpURLConnection.getErrorStream(), response.getOutputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) is.close();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

}
