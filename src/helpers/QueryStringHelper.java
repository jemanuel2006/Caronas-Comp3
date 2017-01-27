package helpers;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

public class QueryStringHelper {
    public static HashMap<String, String> getQueryMap(String query)
    {
        String[] params = query.split("&");
        HashMap<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {  String [] p=param.split("=");
            String name = p[0];
            if(p.length>1)  {String value = p[1];
                map.put(name, value);
            }
        }
        return map;
    }
}
