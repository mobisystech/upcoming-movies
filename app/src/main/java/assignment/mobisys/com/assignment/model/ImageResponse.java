package assignment.mobisys.com.assignment.model;

import java.util.ArrayList;

/**
 * Created by jain on 4/12/2016.
 */
public class ImageResponse {
    private String id;
    private ArrayList<Image> backdrops;
    private ArrayList<Image> posters;

    public  ImageResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<Image> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Image> posters) {
        this.posters = posters;
    }
}
