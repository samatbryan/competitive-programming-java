package fast-dp;

public class bitmask {
    // calculate all submasks of a mask, exclude 0

    void exclude_zero() {
        for (int submask = mask;; submask = (submask - 1) & mask) {
            // do something
            if (submask == 0)
                break;
            int next_mask = mask ^ submask;
        }
    }

    void include_zero() {
        for (int submask = mask; submask > 0; submask = (submask - 1) & mask) {
            // do something
            int next_mask = mask ^ submask;
        }
    }
    /*
     * void idk(){ // calculate all submasks of a mask, include 0
     * 
     * // Generating all masks and their submasks is O(3^N) for(int masks in all
     * masks){ for(int submask=mask; submask > 0; submask = (submask-1) & mask){ //
     * do something if(submask == 0) break; } } }
     */

}