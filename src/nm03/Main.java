package nm03;


public class Main {
    public static void main(String[] args){
        int[][] adj =  {{0, 1, 1}, {1, 1, 1}, {0, 1, 0}};
        System.out.println(getTransitiveCount(adj));
    }

    private static int getTransitiveCount(int[][] adj){
        int count = 0;
        int l = adj[0].length;
        if (l<3) return count;
        //we only need to iterate the triangle/pyramid, since (v,u) cannot be part of a triple
        for (int u= 0; u<l-2; u++){
            for (int v= u+1; v<l-1; v++){
                if (adj[u][v]== 1) { //(u,v) exists
                    for (int w = v + 1; w < l; w++) {
                        System.out.println("bla4");
                        if (adj[u][w] == 1 && adj[v][w] == 1){ //(u,w) and (v,w) also exist
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

}
