package config;

import static enums.Speed.FAST;

import enums.Speed;
import lombok.Data;

@Data
public class SwipeSetting {
    private int   distance           = 75;
    private int   maxSwipeUntilFound = 5;
    private Speed speed              = FAST;

    public static SwipeSetting swipeSetting(){
        return new SwipeSetting ();
    }
}
