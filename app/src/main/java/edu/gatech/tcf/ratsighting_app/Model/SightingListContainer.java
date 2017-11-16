package edu.gatech.tcf.ratsighting_app.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by taint on 10/11/2017.
 */

public class SightingListContainer {

    public static ArrayList<RatSighting> list = new ArrayList<RatSighting>();

    public static ArrayList<RatSighting> filteredList = new ArrayList<>();

    public static int startMonth = 1;

    public static  int startYear = 2000;

    public static int endMonth = 12;

    public static int endYear = 2020;

    public static ArrayList<Calendar> reports = new ArrayList<>();


}
