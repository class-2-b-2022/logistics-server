package models;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
//<<<<<<< HEAD
//import java.util.Iterator;
//import java.util.Map;
//=======
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * @author : Mudahemuka Manzi
 */
public class ClientRequest implements Serializable {
    private String route;///users
    private String action;//register
    private Object data;
    public ClientRequest(){}
    public ClientRequest(String route, String action, Iterator<Map.Entry<String, JsonNode>> data){
        this.route = route;
        this.action = action;
        this.data = data;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}