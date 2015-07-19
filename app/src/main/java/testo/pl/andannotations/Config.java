package testo.pl.andannotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lukasz Marczak on 2015-07-19.
 * global variables
 */
public class Config {
    public static final String[] USERS = {"adas", "anita", "anka", "lukasz"};
    public static String currentUser=null;

    public static Map<String,String> currentDeals = new HashMap<>();
    public static String previousData;
}
