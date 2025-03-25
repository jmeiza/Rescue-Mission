package ca.mcmaster.se2aa4.island.team40;

public enum State {
    
    PHASE0,

    PHASE1,     /*Rperesents looking for the island */

    PHASE2,     /*Represents searching the island for the POI */

    PHASE1_ISLAND_SEARCH,   /*A section of PHASE1 where we are still echoing to find GROUND */

    PHASE1_ISLAND_SIGHTED,  /*A section of PHASE1. The direction of GROUND has been seen and the drone is just flying towards it */

    PHASE2_POI_SEARCH,      /*A section of PHASE2. Currently looking for the creek and emergency site */

    PHASE2_START,           /*First part of PHASE2. Figuring out what direction the first turn should be in */

    PHASE2_IN_BOUND,        /*A state describing that drone is still directly above the island */

    PHASE2_ON_EDGE,         /*The drone is on the edge of the island */

    PHASE2_OUT_OF_BOUND,    /*The drone is no longer above the island */

    /*Number of POI found */
    NONE_FOUND,                 

    SITE_FOUND,

    CREEK_FOUND,

    BOTH_FOUND
}
