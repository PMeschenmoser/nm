package nm02;

/**
 * Network Modeling, Assignment 02
 * Authors: P. Meschenmoser, L. Shkoza
 * Date: 18.11.2017.
 */
public class Edge {
    private String article;
    private Integer user;

    public Edge(String a, Integer u){
        article = a;
        user = u;
    }

    public String getArticle(){ return article;}
    public Integer getUser(){ return user;}
}
