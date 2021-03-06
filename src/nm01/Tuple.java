package nm01;

/**
 * Network Modeling, Assignment 01
 * Authors: P. Meschenmoser, L. Shkoza
 * Date: 10.11.2017.
 */

public class Tuple {
    private String[] fields;
    private String s;
    private Integer id;
    private boolean isGood;
    private boolean isFeatured;

    public Tuple(String input){
        s = input.replaceAll(",_", ""); //replace non-value-separating commas in the page title
        fields = s.split(",");
        id = Integer.parseInt(fields[0].replaceAll("\\(", ""));
    }

    public boolean fieldCheck(int[] indexes, String[] values){
        // check for matches
        // return false in case of index overflow
        // case-insensitive
        for (int i= 0; i<indexes.length; i++){
            if (indexes[i] >= fields.length || !fields[indexes[i]].toLowerCase().equals(values[i].toLowerCase())) return false;
        }
        return true;
    }

    public String getFields(int[] indices){
        //projects the fields with i in indices.
        StringBuilder out = new StringBuilder();
        for (int i : indices){
            out.append(fields[i] + ",");
        }
        String tmp = out.toString();
        return tmp.substring(0, tmp.length()-1) + "\n";
    }

    public String getAllFieldsExtended(){
        //returns the complete row + is_fa + is_ga;
        int f = isFeatured ? 1 : 0;
        int g = isGood ? 1 : 0;
        String out = s+ "," + f + "," + g +"\n";
        return out;
    }

    public Integer getID(){
        return id;
    }
    public void setGood(boolean b){
        isGood = b;
    }
    public void setFeatured(boolean b){
        isFeatured = b;
    }
}
