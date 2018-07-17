/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vdu
 */
public enum TrainModel {
    
    A("A"),
    B("B"),
    C("C");
    
    private String train;
    
    TrainModel(String train) {
        this.train = train;
    }
    
    public String train() {
        return train;
    }
    
}
