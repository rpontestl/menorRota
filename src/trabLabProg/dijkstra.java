package trabLabProg;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class dijkstra {
    Map<String,String> father;

    Map<String,Integer> dist;
    PriorityQueue<Node> pqueue;
    Map<String,Map<String,Integer>> adj_list;

    int V;
    public dijkstra( Map<String,aeroporto> lista){
        V = lista.size();;
        father = new HashMap<>();
        dist = new HashMap<>();
        pqueue = new PriorityQueue<>(V,new Node());
        adj_list = new HashMap<>();

        for (aeroporto x: lista.values()){
            Map<String,Integer> temp = new HashMap<>();
            for(aeroporto y : lista.values()){
                temp.put(y.getSigla(),x.distanciaDiretaEntreAeroportos(y));
            }
            adj_list.put(x.getSigla(),temp);
        }
    }

    public String caminhoDijkstra(String origin, String end){
        for(String name:adj_list.keySet()){
            dist.put(name,Integer.MAX_VALUE);
            father.put(name,origin);
        }

        father.replace(origin,origin,"");
        dist.replace(origin,Integer.MAX_VALUE,0);
        adj_list.get(origin).remove(end);
        pqueue.add(new Node(origin,0));

        while(!pqueue.isEmpty()){
            int ud = - pqueue.peek().cost;
            String u = pqueue.peek().name;
            pqueue.remove();
            if(dist.get(u) < ud) continue;
            for(String x : adj_list.get(u).keySet()){
                int w = adj_list.get(u).get(x);
                if(dist.get(x) > dist.get(u) + w){
                    father.replace(x,father.get(x),u);
                    dist.replace(x,dist.get(x),dist.get(u)+w);
                    pqueue.add(new Node(x,dist.get(x)));
                }
            }
        }

        return recoverPath(end);
    }

    public int getDistance(String fim){
        return dist.get(fim);
    }
    private String recoverPath(String end){
        String aux = end;
        String roat=aux;
        aux = father.get(aux);
        while(!aux.equals("")){
            roat = aux+" > "+roat;
            aux = father.get(aux);
        }
        return roat;
    }
}
