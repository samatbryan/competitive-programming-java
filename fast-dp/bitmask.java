package fast-dp;

public class bitmask {
    // calculate all submasks of a mask, include 0
    for(
    int submask = mask;;submask=(submask-1)&mask)
    {
        // do something
        if (submask == 0)
            break;
    }

    // calculate all submasks of a mask, exclude 0
    for(
    int submask = mask;submask>0;submask=(submask-1)&mask)
    {
        // do something
    }

    // Generating all masks and their submasks is O(3^N)
    for(int masks
    in all masks){
        for(int submask=mask; submask > 0; submask = (submask-1) & mask){
            // do something
            if(submask == 0) break;
        }
    }

}