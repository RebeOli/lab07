package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    public static final int  VENTOTTO=28;
    public static final int  TRENTA=30;
    public static final int  TRENTUNO=31;

    public enum Month {
        JANUARY(31), 
        FEBRUARY(28), 
        MARCH(31), 
        APRIL(30), 
        MAY(31), 
        JUNE(30), 
        JULY(31), 
        AUGUST(31), 
        SEPTEMBER(30), 
        OCTOBER(31), 
        NOVEMBER(30), 
        DECEMBER(31);
        
        private final int days;

        private Month(final int days){
            if(days!=VENTOTTO && days!=TRENTA && days!=TRENTUNO){
                throw new IllegalArgumentException("Numero non valido");
            }
            this.days=days;
        }

        public int getDays(){
            return this.days;
        }
        public static Month fromString(String month){
            if(month==null){throw new NullPointerException();}
            List<Month> listMonth= new ArrayList<>();
            String monthLower=month.trim().toLowerCase();
            for (Month m : Month.values()){
                if(m.toString().toLowerCase().startsWith(monthLower)){
                    listMonth.add(m);
                }
            }
            if(listMonth.size()==1){
                return listMonth.get(0);
            }else{
                throw new IllegalArgumentException("Argomento non valido");
            }
        }
    }
        //Nested Class non deve stare dentro l'enum. 
        public static class SortByMonthOrder implements Comparator<String>{
            public int compare(String month1, String month2){
                return Month.fromString(month1).compareTo(Month.fromString(month2));

            }
        }
        //Nested Class
        public static class SortByDate implements Comparator<String>{
            /*public int compare(String month1, String month2){
                if(Month.fromString(month1).getDays()<Month.fromString(month2).getDays()){
                    return -1;
                }else if(Month.fromString(month1).getDays()==Month.fromString(month2).getDays()){
                    return 0;
                }else{
                    return 1;
                }
            }*/
            public int compare(String month1, String month2){
                return Integer.compare(Month.fromString(month1).getDays(),Month.fromString(month2).getDays());
        
            }
        }


        @Override
        public Comparator<String> sortByDays (){ //Mi restituisce un oggetto comparator per ordinare in base alle date
            /* un comparator è un oggetto che sa confrontare due elementi di tipo <String> */
            return new SortByDate();

        }

        @Override
        public Comparator<String> sortByOrder(){
            return new SortByMonthOrder();
        }

       
}
