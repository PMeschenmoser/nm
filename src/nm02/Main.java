package nm02;

import java.util.*;

/**
 * Created by HP on 17.11.2017.
 */
public class Main {
    public static void main(String[] args){
        LinkedList edges = new LinkedList<Edge>();
        edges.add(new Edge("article1", 1));
        edges.add(new Edge("article1", 2));
        edges.add(new Edge("article2", 3));
        edges.add(new Edge("article2", 4));
        edges.add(new Edge("article2", 5));
        edges.add(new Edge("article3", 1));
        edges.add(new Edge("article3", 3));

        LinkedList articles = new LinkedList<String>();
        articles.add("article1");
        articles.add("article2");
        articles.add("article3");
        articles.add("article5");
        algo(edges, articles);
        System.out.println("Manual test for article 2:" + (Math.log(2)+Math.log(2)+Math.log(1))/6);
    }

    private static void algo(List<String> edges, List<String> articles){
        Iterator iter_edges = edges.iterator();
        HashMap<String, List<Integer>> users_per_article = new HashMap<String, List<Integer>>();
        HashMap<String, Integer>  intersection_lookup = new HashMap<String, Integer>(); //for intersections
        HashMap<Integer, Integer>  articles_per_user = new HashMap<Integer, Integer>(); //use inclusion/exclusion principle, store counts

        while (iter_edges.hasNext()) {
            Edge e = (Edge) iter_edges.next();
            articles_per_user.put(e.getUser(), articles_per_user.getOrDefault(e.getUser(),0)+1);
            //user_per_article: if edge exists here, we have intersections
            List<Integer> possible_intersections =  users_per_article.getOrDefault(e.getArticle(), new LinkedList());

            for (Integer u : possible_intersections) {
                Integer val = intersection_lookup.getOrDefault(Math.min(e.getUser(),u) + "-" + Math.max(e.getUser(),u), 0);
                intersection_lookup.put(Math.min(e.getUser(),u) + "-" + Math.max(e.getUser(),u) , val+1);
            }

            List<Integer> tmp = users_per_article.getOrDefault(e.getArticle(), new LinkedList<Integer>());
            tmp.add(e.getUser());
            users_per_article.put(e.getArticle(), tmp);
        }

        for (String article : articles){ //for each article
            if (users_per_article.getOrDefault(article, new LinkedList<Integer>()).size() > 1){
                List<Integer> tmp_list = users_per_article.get(article);
                Integer[] users =  tmp_list.toArray(new Integer[tmp_list.size()]);
                double tmp_log = 1;
                for (int i=0; i<users.length-1; i++){
                    for (int j=i+1; j<users.length; j++){
                        int intersection = intersection_lookup.get(Math.min(users[i],users[j])+ "-" +Math.max(users[i],users[j]));
                        if (intersection >  0){
                            int union = articles_per_user.get(users[i]) +  articles_per_user.get(users[j]) - intersection;
                            tmp_log *= union/intersection;
                        }
                    }
                }
                double team_diversity = Math.log(tmp_log)/(users_per_article.get(article).size()*(users_per_article.get(article).size()-1));
                System.out.println(article+":"+ team_diversity);
            } else {
                System.out.println(article+":undefined team diversity");
            }
        }
    }
}
