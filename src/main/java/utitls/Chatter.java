package utitls;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by 张佳亮 on 2016/2/27.
 */
public class Chatter {

    private Bitmap picture;
    private String type,content,time;

    public Chatter(Bitmap picture, String type, String content, String time) {
        this.picture = picture;
        this.type = type;
        this.content = content;
        this.time = time;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
