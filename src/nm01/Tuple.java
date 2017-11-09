package nm01;

/**
 * Created by P. Meschenmoser on 08.11.2017.
 */
public class Tuple {
    private String[] fields;
    private String s;
    private boolean isGood;
    private boolean isFeatured;

    public Tuple(String input){
        s = input.replaceAll(",\\s", ""); //replace non-value-separating commas in the page title
        fields = s.split(",");
    }


    public boolean fieldCheck(int[] indexes, String[] values){
        for (int i= 0; i<indexes.length; i++){
            if (indexes[i] >= fields.length || !fields[indexes[i]].toLowerCase().equals(values[i].toLowerCase())) return false;
        }
        return true;
    }

    public String getFields(int[] indices){
        StringBuilder out = new StringBuilder();
        for (int i : indices){
            out.append(fields[i] + ",");
        }
        String tmp = out.toString();
        return tmp.substring(0, tmp.length()-1) + "\n";
    }

    public String getFieldsExtended(int[] indices){
        int f = isFeatured ? 1 : 0;
        int g = isGood ? 1 : 0;
        return getFields(indices) + "," + f + "," + g;
    }

    public String getID(){
        return fields[0];
    }
    public void setGood(boolean b){
        isGood = b;
    }
    public void setFeatured(boolean b){
        isFeatured = b;
    }

}
