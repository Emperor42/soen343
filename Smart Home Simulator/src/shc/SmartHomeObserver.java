/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shc;

/**
 *
 * @author Matthew Giancola (40019131)
 */
public interface SmartHomeObserver {

    /**
     *
     */
    public void update();

    /**
     *
     * @param subject
     */
    public  void observe(SmartHomeSubject subject);
}
