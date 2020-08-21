import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class q3{

    public static void main(String[] args){

        class graph{

            class edge{
                int start, end, weight;
                edge(){
                    start=0;
                    end=0;
                    weight=0;
                }
            };
            int v, e;
            edge edges[];

            graph(int v, int e){
                edges = new edge[e];
                for(int i=0;i <e; i++){
                    edges[i] = new edge();
                }
            }
        };

        Scanner obj = new Scanner(System.in);
        System.out.print("Enter number of nodes and edges: ");
        int v = obj.nextInt(), e = obj.nextInt();
        System.out.print("Enter edges start, end, weight(first edge is edge 0): ");
        graph g = new graph(v, e);

        // 2d list containing paths from start to ith element
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<v;i++){
            paths.add(new ArrayList<Integer>());
            paths.get(i).add(i);
        }

        int s, end, w;
        for(int i=0;i<e; i++){
            g.edges[i].start = obj.nextInt();
            g.edges[i].end = obj.nextInt();
            g.edges[i].weight = obj.nextInt();
        }

        int start, dest;
        System.out.print("Enter start and destination node: ");
        Scanner obj1 = new Scanner(System.in);
        start = obj1.nextInt();
        dest = obj1.nextInt();

        int[] dist = new int[v];
        for(int i=0; i<v; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        for(int j=0; j<v; j++){
            for(int i=0;i<e; i++){
                s = g.edges[i].start;
                end = g.edges[i].end;
                w = g.edges[i].weight;
                if(dist[s]!=Integer.MAX_VALUE && dist[end]>dist[s]+w){
                    dist[end] = dist[s]+w;
                    paths.get(end).clear();
                    for(int k=0; k<paths.get(s).size(); k++){
                        paths.get(end).add(paths.get(s).get(k));
                    }
                    paths.get(end).add(end);
                }

            }
        }

        boolean neg_cycle = false;

        for(int i=0; i<e; i++){
            s = g.edges[i].start;
            end = g.edges[i].end;
            w = g.edges[i].weight;
            if(dist[s]!=Integer.MAX_VALUE && dist[end]>dist[s]+w){
                neg_cycle = true;
                break;
            }
        }
        if(neg_cycle){
            System.out.println("Given graph contains negative cycle");
        }
        else{
            if(dist[dest]!=Integer.MAX_VALUE){
                System.out.print("Minimum dist between given source and destination: ");
                System.out.println(dist[dest]);
                System.out.print("Path: ");
                for(int i=0;i<paths.get(dest).size(); i++){
                    System.out.print(paths.get(dest).get(i));
                    if(i!=paths.get(dest).size()-1){
                        System.out.print("->");
                    }
                }
                System.out.println("");
            }
            else{
                System.out.println("No Path Exist!");
            }
        }

    }
}