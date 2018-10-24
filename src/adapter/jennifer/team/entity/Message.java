package adapter.jennifer.team.entity;

import org.json.JSONObject;

/**
 * Created by minsoo.jun on 2018-10-24.
 */
public class Message {

    private final String text;
    private Prop prop;
    private String eventLevel;


    public Message(Prop prop, String text, String eventLevel) {
        this.prop = prop;
        this.text = text;
        this.eventLevel = eventLevel;
    }

    public Prop getProp() {
        return this.prop;
    }

    public String getText() {
        return this.text;
    }

    public String getEventLevel(){
        return this.eventLevel;
    }
}
