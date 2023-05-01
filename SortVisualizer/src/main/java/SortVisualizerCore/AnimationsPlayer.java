package SortVisualizerCore;

import java.util.ArrayList;
import javafx.animation.Animation;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;

public class AnimationsPlayer {
    SequentialTransition sequentialTranslateAnimations = new SequentialTransition();
    SequentialTransition sequentialPseudocodeAnimations = new SequentialTransition();
    
    public void createSequentialTransitions(ArrayList<Animation> translateAnimations, ArrayList<Animation> pseudocodeAnimations){
        ArrayList<Animation> animations = new ArrayList();
        
        /*
        if(!pseudocodeAnimations.isEmpty()){
            for(int i = 0; i < translateAnimations.size(); i++){
                if(pseudocodeAnimations.get(i) != null){
                    ParallelTransition parallel = new ParallelTransition();
                    parallel.getChildren().addAll(translateAnimations.get(i), pseudocodeAnimations.get(i));  
                    animations.add(parallel);
                } else {
                    animations.add(translateAnimations.get(i));
                }
            }
            sequentialTranslateAnimations.getChildren().addAll(animations);
        } else {
*/
            sequentialTranslateAnimations.getChildren().addAll(translateAnimations);
        //}
    }
    
    public void play(){
        sequentialTranslateAnimations.play();
        //sequentialPseudocodeAnimations.play();
    }
    
    public void pause(){
        sequentialTranslateAnimations.pause();
        //sequentialPseudocodeAnimations.pause();
    }
    
    public void increaseSpeed(){
        if(sequentialTranslateAnimations.getStatus().equals(RUNNING)){
            sequentialTranslateAnimations.setRate(sequentialTranslateAnimations.getRate() * 1.25);
            //sequentialPseudocodeAnimations.setRate(sequentialPseudocodeAnimations.getRate() * 1.25);
        }
    }
    
    public void decreaseSpeed(){
        if(sequentialTranslateAnimations.getStatus().equals(RUNNING)){
            sequentialTranslateAnimations.setRate(sequentialTranslateAnimations.getRate() * 0.8);
            //sequentialPseudocodeAnimations.setRate(sequentialPseudocodeAnimations.getRate() * 0.8);
        }
    }
    
}
