package models;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author : Mudahemuka Manzi
 */
public class ClientRequest implements Serializable {
    private String route;///users
    private String action;//register
    private Iterator<Map.Entry<String, JsonNode>> data;
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

    public Iterator<Map.Entry<String, JsonNode>> getData() {
        return data;
    }

    public void setData(Iterator<Map.Entry<String, JsonNode>> data) {
        this.data = data;
    }
}
