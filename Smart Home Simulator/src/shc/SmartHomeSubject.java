package shc;

/**
 *
 * @author Matthew Giancola
 */
public interface SmartHomeSubject {
    
    public void setState(Object state);
    public Object getState();
    public void attach(SmartHomeObserver a);
    
}
