package pru.lukasz.utils;

public class Numeric {

    public static boolean isDouble(String value){
        boolean numeric = true;

        try {
            Double num = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }
    public static Double getDouble(String value){

        Double num=0.0;
        try {
            num = Double.parseDouble(value);
        } catch (NumberFormatException e) {

        }
        return num;
    }

    public static boolean isInt(String value){
        boolean numeric = true;

        try {
            Integer num = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }

    public static Integer getInt(String value){

        Integer num=0;
        try {
            num = Integer.parseInt(value);
        } catch (NumberFormatException e) {

        }
        return num;
    }
}
