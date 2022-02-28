package models;

import java.io.Serializable;

public class ClientRequest implements Serializable {
    private String route;
    private String action;
    private Object data;
    public ClientRequest(){}
    public ClientRequest(String route, String action, Object data){
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
