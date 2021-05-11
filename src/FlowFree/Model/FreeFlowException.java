package FlowFree.Model;

/**
 * Custom unchecked runtime exception
 *
 * @author Jonathan Auteveld
 * @version 1.0 6/05/2021 23:57
 */
public class FreeFlowException extends RuntimeException {
    FreeFlowException(Throwable fault){
        super(fault);
    }
}
