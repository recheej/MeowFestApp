package com.example.rechee.meowfest.models;

import java.util.List;

/**
 * Created by Rechee on 9/30/2017.
 */

public interface CatListener {
    void catsReceived(List<Cat> catsReceived);
    void catRetrieveFailure(String error);
}
