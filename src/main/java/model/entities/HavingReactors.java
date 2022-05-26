package model.entities;

import java.util.ArrayList;
import java.util.Collections;

public interface HavingReactors{

    public void setReactors(ArrayList<Reactor> reactors);

    public ArrayList<Reactor> getReactors();

    default public void addReactors(Reactor... reactors){
        Collections.addAll(getReactors(), reactors);
    }

}
